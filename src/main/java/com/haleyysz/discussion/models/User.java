package com.haleyysz.discussion.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class User {

    public static final int TYPE_INSTRUCTOR = 0;
    public static final int TYPE_BEGINNER = 1;
    public static final int TYPE_INTERMEDIATE = 2;
    public static final int TYPE_EXPERT = 3;

    @Id
    private String id;
    private String username;
    private String password;
    private int type;
    private List<Course> course;

    public User(){
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Course> getCourse() {
        return course;
    }

    public void setCourse(List<Course> course) {
        this.course = course;
    }
}