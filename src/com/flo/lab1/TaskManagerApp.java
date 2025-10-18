package com.flo.lab1;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskManagerApp {

    private ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("\n=== Task Manager ===");

        TaskManagerApp app = new TaskManagerApp();

        Scanner sc = new Scanner(System.in);
        boolean running = true;
        int opt;

        while (running) {
            System.out.println("1. Adauga Task");
            System.out.println("2. Delete Task");
            System.out.println("3. Complete Task");
            System.out.println("4. Print All Tasks");
            System.out.println("5. Iesire");
            System.out.println("Alege optiunea: ");

            opt = sc.nextInt();
            sc.nextLine();

            switch (opt) {
                case 1 -> {
                    System.out.println("[ADD] Specifica nume: ");
                    String nume = sc.next();
                    app.addTask(nume);
                    System.out.println("[ADDED] " + nume);
                }
                case 2 -> {
                    System.out.println("[DELETE] Specifica nume: ");
                    String nume = sc.next();
                    Task task = app.getTaskByName(nume);
                    if (task == null) {
                        break;
                    }
                    app.deleteTask(nume);
                    System.out.println("[DELETED] " + nume);
                }
                case 3 -> {
                    System.out.println("[COMPLETE] Specifica nume: ");
                    String nume = sc.next();
                    Task task = app.getTaskByName(nume);
                    if (task == null) {
                        break;
                    }
                    task.setCompleted();
                    System.out.println("[COMPLETED] " + nume);
                }
                case 4 -> {
                    System.out.println("[PRINT] All Tasks");
                    app.printTasks();
                }
                case 5 -> {
                    running = false;
                }
                default -> {
                    System.out.println("Optiune invalida");
                }

            }
        }

    }

    public void addTask(String name) {
        Task task = new Task(name);
        tasks.add(task);
        System.out.println("Task adaugat: " + task.getName());
    }
    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Nu exista taskuri");
            return;
        }

        for (Task task : tasks) {
            System.out.println(task.getId() + "." + task.getName() + " | Finalizat: " + task.isCompleted());
        }
    }
    public void completeTask(int index) {
        if (index < 1 || index >= tasks.size()) {
            System.out.println("Nu exista taskuri");
            return;
        }

        Task task = tasks.get(index - 1);
        task.setCompleted();
        System.out.println("Task finalizat: " + task.getName());
    }
    public void deleteTask(String nume) {
        Task task = getTaskByName(nume);
        tasks.remove(task);
        System.out.println("Task sters: " + task.getName());
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
}
