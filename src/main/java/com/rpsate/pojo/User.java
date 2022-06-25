package com.rpsate.pojo;

import com.baomidou.mybatisplus.annotation.TableField;

public class User {
    private Long id;
    private String username;
    @TableField(value = "password", select = false)
    private String password;
    private Integer grade;

    public User() {}

    public User(String username, String password, Integer grade) {
        this.username = username;
        this.password = password;
        this.grade = grade;
    }

    public User(Long id, String username, String password, Integer grade) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Boolean isEmptyExcludeId() {
        if (username == null && password == null && grade == null)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", grade=" + grade +
                '}';
    }
}
