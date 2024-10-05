package com.example.smdassignment2;

public class Task {
    private String name;
    private String description;
    private boolean isComplete;

    public Task() {
        this.isComplete = false;
    }

    public Task(String name, String description, boolean isComplete) {
        this.name = name;
        this.description = description;
        this.isComplete = isComplete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {return isComplete;}

    public void setCompleted(boolean isComplete) {this.isComplete = isComplete;}

}

