package com.flo.task_app.service;

import com.flo.task_app.model.Priority;
import com.flo.task_app.model.Task;

import java.io.*;
import java.util.ArrayList;

public class TaskService {

    private ArrayList<Task> tasks = new  ArrayList<>();


    public void addTask(String name, Priority priority) {
        Task task = new Task(name, priority);
        tasks.add(task);
    }
    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Nu exista taskuri");
            return;
        }

        for (Task task : tasks) {
            System.out.println(task.getId() + ". [" + task.getPriority() + "] " + task.getName() + " | Finalizat: " + task.isCompleted());
        }
    }
    public boolean completeTask(String name) {
        Task task = getTaskByName(name);
        if (task == null) {
            return false;
        }
        task.setCompleted();
        return true;
    }
    public boolean deleteTask(String nume) {
        Task task = getTaskByName(nume);
        if (task == null) {
            return false;
        }
        tasks.remove(task);
        return true;
    }
    public Task getTaskByName(String name) {
        for (Task task : tasks) {
            if (task.getName().equals(name)) {
                return task;
            }
        }
        System.out.println("Nu exista taskul {" + name + "}");
        return null;
    }
    public void saveTaskToFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Task task : tasks) {
                bw.write(task.getId() + ";" + task.getName() + ";" + task.isCompleted() + ";" + task.getPriority() +  "\n");
                bw.newLine();
            }
            System.out.println("[SAVED] " + filename);
        } catch (IOException e) {
            System.out.println("[ERROR] Eroare la scrierea in fisier: " + e.getMessage());
        }
    }
    public boolean loadTaskFromFile(String filename) {
        tasks.clear(); // ne asiguram ca nu avem nimic in tasks inainte sa citim
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 4) {
                    String name = data[1];
                    boolean isCompleted = Boolean.parseBoolean(data[2]);
                    Priority priority = Priority.valueOf(data[3]);
                    Task task = new Task(name,priority);
                    if (isCompleted) {
                        task.setCompleted();
                    }
                    tasks.add(task);
                }

            }
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] Fisierul nu exista. Se va crea la inchiderea programului.");
        } catch (IOException e) {
            System.out.println("[ERROR] Eroare la citirea din fisier: " + e.getMessage());
        }
        return false;
    }
}
