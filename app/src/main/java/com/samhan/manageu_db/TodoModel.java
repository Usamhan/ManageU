package com.samhan.manageu_db;


public class TodoModel {
    public String task,priority;

    public TodoModel() {
    }

    public TodoModel(String task, String priority) {
        this.task = task;
        this.priority = priority;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}

