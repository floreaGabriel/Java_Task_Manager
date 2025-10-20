package com.flo.task_app.model;

public class Task {
    private String name;
    private boolean completed;
    private static int counter = 0;
    private int id;

    private Priority priority;

    public Task(String name) {
        this(name, Priority.MEDIUM);
    }

    public Task(String name, Priority priority) {
        this.name = name;
        this.completed = false;
        this.counter++;
        this.id = counter;
        this.priority = priority;
    }

    public String getName() { return name; }
    public boolean isCompleted() { return completed; }
    public int getId() { return id; }
    public void setCompleted() { this.completed = true; }
    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
}
