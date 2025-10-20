package com.flo.task_app.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Task {
    //
    private static final AtomicInteger COUNTER = new AtomicInteger(0);
    private String name;
    private boolean completed;
    private static int counter = 0;
    private int id;

    private Priority priority;

    // Constructor pentru creare normala a unui Task (auto-id)
    public Task(String name, Priority priority) {
        this.id = COUNTER.incrementAndGet();
        this.name = name;
        this.priority = priority == null ? Priority.MEDIUM : priority;
        this.completed = false;
    }


    // Constructor pentru load din fisier (cu id din fisier)
    public Task(int id, String name, boolean completed, Priority priority) {
        this.id = id;
        this.name = name;
        this.completed = false;
        this.priority = priority;
    }

    public static void reseedCounter(int lastId) {
        COUNTER.set(lastId);
    }
    public String getName() { return name; }
    public boolean isCompleted() { return completed; }
    public int getId() { return id; }
    public void setCompleted() { this.completed = true; }
    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }


    @Override
    public String toString() {
        return id + ". [" + priority + "] " + name + " | Finalizat: " + completed;
    }
}
