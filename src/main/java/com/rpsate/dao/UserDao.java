package com.rpsate.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rpsate.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao extends BaseMapper<User> {
    public default User getByUsername(String username) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(User::getUsername, username);
        return this.selectOne(lambdaQueryWrapper);
    }

    @Select("select * from user where username=#{username} and password=#{password}")
    public User getUserByPassword(User user);
}
