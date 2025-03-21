package org.example.Implement;
import org.example.entity.Task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class TaskService {
    public static Task createTask(String description, long nextId, String status) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Task task = new Task();
        task.setTaskId(nextId++);
        task.setDescription(description);
        task.setStatus(status);
        task.setCreatedAt(LocalDateTime.now().format(formatter));
        return task;
    }


    public static void listTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No hay tareas registradas.");
        } else {
            System.out.println("Tareas:");
            tasks.forEach(System.out::println);
        }
    }
    public static void updateTask(List<Task> tasks, Long id, Scanner scanner) {
        Task taskToUpdate = findTaskById(tasks, id);
        if (taskToUpdate != null) {
            System.out.print("Nueva descripción: ");
            String newDescription = scanner.nextLine().trim();
            if (!newDescription.isEmpty()) {
                taskToUpdate.setDescription(newDescription);
            }

            System.out.print("Estado actualizado: ");
            String newStatus = scanner.nextLine().trim();
            if (!newStatus.isEmpty()) {
                taskToUpdate.setStatus(newStatus);
            }
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            taskToUpdate.setUpdatedAt(LocalDateTime.now().format(formatter));
            System.out.println("Tarea actualizada.");
        } else {
            System.out.println("No se encontró una tarea con el ID " + id + ".");
        }
    }
    public static Task findTaskById(List<Task> tasks, Long id) {
        for (Task task : tasks) {
            if (Objects.equals(task.getTaskId(), id)) {
                return task;
            }
        }
        return null;
    }
    public static void findTaskByStatus(List<Task> tasks, String status) {
        for (Task task : tasks) {
            if (task.getStatus().equalsIgnoreCase(status)) {
                System.out.println(task);
            }
        }

    }
    public static void deleteTask(List<Task> tasks, Long id) {
        Task taskToDelete = findTaskById(tasks, id);
        if (taskToDelete != null) {
            tasks.remove(taskToDelete);
            System.out.println("Tarea eliminada.");
        } else {
            System.out.println("No se encontró una tarea con el ID " + id + ".");
        }
    }
}
