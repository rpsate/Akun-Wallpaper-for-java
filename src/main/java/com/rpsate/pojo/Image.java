package com.rpsate.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class Image {
    private Long id;
    private String name;
    private String path;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Timestamp time;

    public Image(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public Image(Long id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }

    public Image(Long id, String name, String path, Timestamp time) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Boolean isEmptyExcludeId() {
        if (name == null && path == null)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
