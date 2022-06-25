package com.rpsate.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rpsate.dao.UserDao;
import com.rpsate.pojo.User;
import com.rpsate.response.RespCodeMsg;
import com.rpsate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Value("${md5.salt}")
    private String salt;

    @Override
    public Boolean isExist(String username) {
        User user = userDao.getByUsername(username);
        if (user == null)
            return false;
        return true;
    }

    @Override
    public Integer register(User user) {
        user.setPassword(password2md5(user.getPassword()));
        if (isExist(user.getUsername()))
            return RespCodeMsg.USER_EXIST;
        var res = userDao.insert(user);
        if (res > 0)
            return RespCodeMsg.REGISTER_SUCCESS;
        return RespCodeMsg.REGISTER_FAIL;
    }

    @Override
    public User login(User user) {
        user.setPassword(password2md5(user.getPassword()));
        return userDao.getUserByPassword(user);
    }

    @Override
    public Integer update(User user) {
        user.setPassword(password2md5(user.getPassword()));
        Integer res =  userDao.updateById(user);
        if (res > 0)
            return RespCodeMsg.UPDATE_USER_SUCCESS;
        return RespCodeMsg.UPDATE_USER_FAIL;
    }

    @Override
    public Integer delete(Long id) {
        Integer res = userDao.deleteById(id);
        if (res > 0)
            return RespCodeMsg.DELETE_USER_SUCCESS;
        return RespCodeMsg.DELETE_USER_FAIL;
    }

    @Override
    public Integer deletes(List<Long> ids) {
        return userDao.deleteBatchIds(ids);
    }

    @Override
    public IPage getAll(Integer page, Integer size) {
        IPage selectPage = new Page(page, size);
        return userDao.selectPage(selectPage, null);
    }

    private String password2md5(String passwd) {
        if (passwd == null)
            return null;
        passwd = DigestUtils.md5DigestAsHex(passwd.getBytes(StandardCharsets.UTF_8));
        passwd = salt + passwd + salt;
        passwd = DigestUtils.md5DigestAsHex(passwd.getBytes(StandardCharsets.UTF_8));
        return passwd;
    }
}
