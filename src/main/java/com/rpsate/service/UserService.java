package com.rpsate.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rpsate.pojo.User;

import java.util.List;

public interface UserService {
    public Boolean isExist(String username);

    public Integer register(User user);

    public User login(User user);

    public Integer update(User user);

    public Integer delete(Long id);

    public Integer deletes(List<Long> ids);

    public IPage getAll(Integer page, Integer size);
}
