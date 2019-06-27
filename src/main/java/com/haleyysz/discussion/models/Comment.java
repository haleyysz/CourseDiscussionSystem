package com.haleyysz.discussion.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Comment {
    @Id
    private String id;
    private User user;
    private long date;
    private String content;

    public String getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(String discussionId) {
        this.discussionId = discussionId;
    }

    private String discussionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Comment(){

    }



}
