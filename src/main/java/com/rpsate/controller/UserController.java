package com.rpsate.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rpsate.pojo.User;
import com.rpsate.response.RespCodeMsg;
import com.rpsate.response.RespData;
import com.rpsate.service.UserService;
import com.rpsate.utils.TokenProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private UserService userService;

    @GetMapping("/{page}/{size}")
    public RespData getAll(@PathVariable Integer page, @PathVariable Integer size) {
        IPage res = userService.getAll(page, size);
        if (res == null)
            return new RespData(RespCodeMsg.FAIL, null);
        return new RespData(RespCodeMsg.SUCCESS, res);
    }

    @PutMapping
    public RespData update(@RequestBody User user) {
        System.out.println(user);
        if (user.getId() == null)
            return new RespData(RespCodeMsg.ID_NOT_NULL, null);
        if (user.isEmptyExcludeId())
            return new RespData(RespCodeMsg.PARAMETER_ERR, null);
        Integer code = userService.update(user);
        return new RespData(code, null);
    }

    @DeleteMapping("/{id}")
    public RespData delete(@PathVariable Long id) {
        Integer code = userService.delete(id);
        return new RespData(code, null);
    }

    @DeleteMapping
    public RespData deletes(@RequestBody List<Long> ids) {
        if (ids == null || ids.size() == 0)
            return new RespData(RespCodeMsg.ID_NOT_NULL, null);
        Integer res = userService.deletes(ids);
        if (res > 0)
            return new RespData(RespCodeMsg.DELETE_USERS_SUCCESS, null, String.valueOf(res));
        return new RespData(RespCodeMsg.DELETE_USERS_FAIL, null);
    }

    @PostMapping
    public RespData register(@RequestBody User user) {
        if (user.getUsername() == null)
            return new RespData(RespCodeMsg.USERNAME_NOT_NULL, null);
        if (user.getPassword() == null)
            return new RespData(RespCodeMsg.PASSWORD_NOT_NULL, null);
        if (user.getGrade() == null)
            return new RespData(RespCodeMsg.GRADE_NOT_NULL, null);
        Integer code = userService.register(user);
        return new RespData(code, null);
    }

    @PostMapping("/login")
    public RespData login(@RequestBody User user) {
        if (user.getUsername() == null)
            return new RespData(RespCodeMsg.USERNAME_NOT_NULL, null);
        if (user.getPassword() == null)
            return new RespData(RespCodeMsg.PASSWORD_NOT_NULL, null);
        User getUser = userService.login(user);
        if (getUser == null)
            return new RespData(RespCodeMsg.LOGIN_FAIL, null);
        String token = TokenProcessor.generateToken(getUser.getId(), getUser.getUsername(), getUser.getGrade(), secret);
        Map<String, String> data = new HashMap<String, String>();
        data.put("token", token);
        return new RespData(RespCodeMsg.LOGIN_SUCCESS, data);
    }
}
