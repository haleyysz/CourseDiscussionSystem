package com.haleyysz.discussion.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Course {
    public Course(String courseName) {
        this.courseName = courseName;
        this.normalizedName = courseName.substring(0,6);
    }

    @Id
    private String id;
    private String courseName;

    public String getNormalizedName() {
        return normalizedName;
    }

    public void setNormalizedName(String normalizedName) {
        this.normalizedName = normalizedName;
    }

    private String normalizedName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Course() {

    }

}
