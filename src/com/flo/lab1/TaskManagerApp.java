package com.flo.lab1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManagerApp {

    private ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("\n=== Task Manager ===");

        TaskManagerApp app = new TaskManagerApp();
        app.loadTaskFromFile("tasks.txt");

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
                    app.addTask(nume);
                    System.out.println("[ADDED] " + nume);
                }
                case 2 -> {
                    System.out.println("[DELETE] Specifica nume: ");
                    String nume = sc.nextLine();
                    app.deleteTask(nume);
                    System.out.println("[DELETED] " + nume);
                }
                case 3 -> {
                    System.out.println("[COMPLETE] Specifica nume: ");
                    String nume = sc.nextLine();
                    app.completeTask(nume);
                    System.out.println("[COMPLETED] " + nume);
                }
                case 4 -> {
                    System.out.println("[PRINT] All Tasks");
                    app.printTasks();
                }
                case 5 -> {
                    running = false;
                    app.saveTaskToFile("tasks.txt");
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
    public void completeTask(String name) {
        Task task = getTaskByName(name);

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
    public void saveTaskToFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Task task : tasks) {
                bw.write(task.getId() + ";" + task.getName() + ";" + task.isCompleted() + "\n");
                bw.newLine();
            }
            System.out.println("[SAVED] " + filename);
        } catch (IOException e) {
            System.out.println("[ERROR] Eroare la scrierea in fisier: " + e.getMessage());
        }
    }
    public void loadTaskFromFile(String filename) {
        tasks.clear(); // ne asiguram ca nu avem nimic in tasks inainte sa citim
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 3) {
                    String name = data[1];
                    boolean isCompleted = Boolean.parseBoolean(data[2]);
                    Task task = new Task(name);
                    if (isCompleted) {
                        task.setCompleted();
                    }
                    tasks.add(task);
                }

            }
            System.out.println("[LOADED] " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] Fisierul nu exista. Se va crea la inchiderea programului.");
        } catch (IOException e) {
            System.out.println("[ERROR] Eroare la citirea din fisier: " + e.getMessage());
        }
    }

}
