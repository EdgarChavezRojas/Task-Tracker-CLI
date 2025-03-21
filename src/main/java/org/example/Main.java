import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.entity.Task;


import java.util.List;
import java.util.Scanner;

import static org.example.Implement.TaskService.*;
import static org.example.TaskCLI.*;

public static void main(String[] args) {
        System.out.println("Hello, World!");
        Scanner scanner = new Scanner(System.in);
        System.out.println("CLI de Gestión de Tareas. Escribe 'help' para ver los comandos disponibles.");

        List<Task> tasks = loadTasks();

        while (true) {

            System.out.print("> ");
            String command = scanner.nextLine().trim().toLowerCase();


            switch (command) {
                case "add":
                    System.out.print("Ingresa la descripción de la tarea: ");
                    String description = scanner.nextLine().trim();
                    System.out.print("Estado de la tarea: ");
                    String status = scanner.nextLine().trim();
                    tasks.add(createTask(description, nextId, status));
                    System.out.println("Tarea agregada.");
                    break;
                case "list":
                    listTasks(tasks);
                    break;
                case "list filter":
                    System.out.print("Ingresa el estado de la tarea: ");
                    String Status = scanner.nextLine().trim();
                    findTaskByStatus(tasks, Status);
                    break;
                case "help":
                    helpCommand();
                    break;
                case "update":
                    System.out.print("Ingresa el ID de la tarea a actualizar: ");
                    long updateId = Integer.parseInt(scanner.nextLine().trim());
                    updateTask(tasks, updateId, scanner);
                    break;
                case "delete":
                    System.out.print("Ingresa el ID de la tarea a eliminar: ");
                    long deleteId = Integer.parseInt(scanner.nextLine().trim());
                    deleteTask(tasks, deleteId);
                    break;
                case "exit":
                    exitCLI();
                    saveTasks(tasks);
                    scanner.close();
                    return;
                default:
                    System.out.println("Comando no reconocido: " + command + ". Escribe 'help' para ver los comandos disponibles.");
                    break;
            }
        }

    }

public static void helpCommand() {
    System.out.println("Comandos disponibles:");
    System.out.println("  add - Agrega una nueva tarea.");
    System.out.println("  list - Lista todas las tareas.");
    System.out.println("  list filter - Lista las tareas por filtro.");
    System.out.println("  help - Muestra esta ayuda.");
    System.out.println("  update - Actualiza una tarea existente.");
    System.out.println("  delete - Elimina una tarea existente.");
    System.out.println("  exit - Sale del programa.");
}
public static void exitCLI() {
    System.out.println("Saliendo del CLI. ¡Hasta luego!");
}
private static List<Task> loadTasks() {
    File file = new File(FILE_PATH);
    if (file.exists()) {
        try {
            List<Task> tasks = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, Task.class));

            if (!tasks.isEmpty()) {
                nextId = tasks.stream().mapToLong(Task::getTaskId).max().orElse(0) + 1;
            }
            return tasks;
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo JSON: " + e.getMessage());
        }
    }
    return new ArrayList<>();
}

private static void saveTasks(List<Task> tasks) {
    try {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File(FILE_PATH), tasks);
        System.out.println("Tareas guardadas en " + FILE_PATH);
    } catch (IOException e) {
        System.err.println("Error al guardar el archivo JSON: " + e.getMessage());
    }
}
