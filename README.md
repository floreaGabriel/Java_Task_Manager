# Java Task Manager

A simple console-based Task Manager application built in Java for learning core language features, OOP, file persistence, and modular software architecture. You can add, delete, complete, display, and save tasks, each with an assigned priority.

## Features

- Add tasks with name and priority (LOW, MEDIUM, HIGH)
- Mark tasks as completed
- Delete tasks by name
- View all tasks in a readable format
- Save and load tasks from a file (CSV-style)
- Modular structure: logic and data models are separated for maintainability

## Technologies Used

- Java 21+ 
- Plain Java (no frameworks required for console version)
- Ready for extension to Spring Boot (for REST API) and frontend JS integrations in the future

## Project Structure

```
src/
├── com/flo/task_app/
│ ├── model/
│ │ ├── Task.java
│ │ └── Priority.java
│ ├── service/
│ │ └── TaskService.java
│ └── TaskManagerApp.java
└── tasks.txt # Persistence file
```

## Getting Started

### 1. Clone the repository

```
git clone https://github.com/floreaGabriel/Java_Task_Manager.git
cd Java_Task_Manager
```

### 2. Compile and Run

Compile from the project root (where `src/` is located):

```
javac -d out src/com/flo/task_app/model/.java src/com/flo/task_app/service/.java src/com/flo/task_app/TaskManagerApp.java
```

Run the program:

```
java -cp out com.flo.task_app.TaskManagerApp
```

### 3. How to Use

- Choose an option from the menu (add, delete, complete, etc).
- When adding a task, specify its name and priority (L/M/H).
- Tasks are automatically saved to `tasks.txt` on exit or manually.

## Example Menu

```
Adauga Task

Delete Task

Complete Task

Print All Tasks

Salvare

Iesire
```


## Future Improvements

- Add filtering and sorting (by status and priority)
- Switch persistence to JSON
- Add a REST API backend using Spring Boot
- Connect with a JavaScript (React/Vue) frontend for a web interface

## License

This project is licensed under the MIT License.

---

**Educational project !**
