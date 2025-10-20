package com.flo.task_app;

import com.flo.task_app.service.TaskService;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManagerApp {

    private final TaskService taskService = new TaskService();

    public static void main(String[] args) {

        System.out.println("\n=== Task Manager ===");

        TaskManagerApp app = new TaskManagerApp();

        String filename = "tasks.txt";
        if (app.taskService.loadTaskFromFile(filename))
            System.out.println("[LOADED] " + filename);

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
                    String nume = sc.nextLine();
                    app.taskService.addTask(nume);
                    System.out.println("[ADDED] " + nume);
                }
                case 2 -> {
                    System.out.println("[DELETE] Specifica nume: ");
                    String nume = sc.nextLine();
                    if (app.taskService.deleteTask(nume))
                        System.out.println("[DELETED] " + nume);
                    else
                        System.out.println("[ERROR] Stergere task.");
                }
                case 3 -> {
                    System.out.println("[COMPLETE] Specifica nume: ");
                    String nume = sc.nextLine();
                    if (app.taskService.completeTask(nume))
                        System.out.println("[COMPLETED] " + nume);
                    else
                        System.out.println("[ERROR] Completare task.");
                }
                case 4 -> {
                    System.out.println("[PRINT] All Tasks");
                    app.taskService.printTasks();
                }
                case 5 -> {
                    running = false;
                    app.taskService.saveTaskToFile("tasks.txt");
                }
                default -> {
                    System.out.println("Optiune invalida");
                }

            }
        }

    }
}
