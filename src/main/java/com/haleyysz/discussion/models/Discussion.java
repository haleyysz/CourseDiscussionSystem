package com.haleyysz.discussion.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Discussion {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
   private String id;
    private User user;
    private String course;
    private String topic;
    private String content;
    private String img;
    private String date;
    public Discussion() {

    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public  String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
