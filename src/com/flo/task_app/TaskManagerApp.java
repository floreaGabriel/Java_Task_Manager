package com.flo.task_app;

import com.flo.task_app.model.Priority;
import com.flo.task_app.model.Task;
import com.flo.task_app.service.TaskService;

import java.util.List;
import java.util.Scanner;

public class TaskManagerApp {
    private final TaskService taskService = new TaskService();

    public static void main(String[] args) {
        new TaskManagerApp().run();
    }


    private void run() {
        System.out.println("\n=== Task Manager ===");
        String filename = "tasks.txt";

        if (taskService.loadTaskFromFile(filename))
            System.out.println("[LOADED] " + filename);

        Scanner sc = new Scanner(System.in);
        boolean running = true;
        int opt;

        while (running) {
            printMenu();
            opt = sc.nextInt();
            sc.nextLine();

            switch (opt) {
                case 1 -> handleAdd(sc);
                case 2 -> handleDelete(sc);
                case 3 -> handleComplete(sc);
                case 4 -> handlePrintAll(sc);
                case 5 -> taskService.saveTaskToFile(filename);
                case 6 -> running = false;
                default -> {
                    System.out.println("Optiune invalida");
                }

            }
        }

    }
    private void printMenu() {
        System.out.println("\n\n1. Adauga Task");
        System.out.println("2. Delete Task");
        System.out.println("3. Complete Task");
        System.out.println("4. Print All Tasks");
        System.out.println("5. Salvare");
        System.out.println("6. Iesire");
        System.out.println("Alege optiunea: ");
    }
    private void handleAdd(Scanner sc) {
        System.out.println("[ADD] Specifica nume: ");
        String nume = sc.nextLine();
        Priority priority = readPrio(sc, "[PRIORITY] L(LOW) M(MEDIUM) H(HIGH)");
        taskService.addTask(nume, priority);
        System.out.println("[ADDED] " + nume + " cu prioritatea " + priority);
    }

    private Priority readPrio(Scanner sc, String prompt) {
        System.out.println(prompt);

        String s = sc.nextLine().trim().toUpperCase();

        return switch (s) {
            case "L" -> Priority.LOW;
            case "M" -> Priority.MEDIUM;
            case "H" -> Priority.HIGH;
            default -> null;
        };
    }
    private void handleDelete(Scanner sc) {
        System.out.println("[DELETE] Specifica nume: ");
        String nume = sc.nextLine();
        if (taskService.deleteTask(nume))
            System.out.println("[DELETED] " + nume);
        else
            System.out.println("[ERROR] Stergere task.");
    }
    private void handleComplete(Scanner sc) {
        System.out.println("[COMPLETE] Specifica nume: ");
        String nume = sc.nextLine();
        if (taskService.completeTask(nume))
            System.out.println("[COMPLETED] " + nume);
        else
            System.out.println("[ERROR] Completare task.");
    }
    private void handlePrintAll(Scanner sc) {
        List<Task> all = taskService.getAll();
        taskService.printTasks();
    }
}

