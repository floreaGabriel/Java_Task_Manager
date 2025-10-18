package com.flo.lab1;

import java.util.ArrayList;

public class Task {
    private String name;
    private boolean completed;
    private static int counter = 0;
    private int id;

    public Task(String name) {
        this.name = name;
        this.completed = false;
        this.counter++;
        this.id = counter;
    }

    public String getName() {
        return name;
    }
    public boolean isCompleted() {
        return completed;
    }
    public int getId() { return id; }
    public void setCompleted() { this.completed = true; }
}
