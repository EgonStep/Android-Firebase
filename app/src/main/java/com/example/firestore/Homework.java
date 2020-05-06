package com.example.firestore;

public class Homework {

    private String title;
    private String type;
    private String date;
    private String priority;

    public Homework(String title, String type, String date, String priority) {
        this.title = title;
        this.type = type;
        this.date = date;
        this.priority = priority;
    }

    public Homework() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
