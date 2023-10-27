package com.example.todo.service;

import com.example.todo.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private long nextId = 1;

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task addTask(Task task) {
        task.setId(nextId++);
        tasks.add(task);
        return task;
    }

    public Task editTask(Long taskId, Task updatedTask) {
        // Implement the logic to edit a task by its ID
        for (Task task : tasks) {
            if (task.getId().equals(taskId)) {
                // Update the task details
                // You may need to implement the update logic here
                return task;
            }
        }
        return null; // Task not found
    }

    public void deleteTask(Long taskId) {
        tasks.removeIf(task -> task.getId().equals(taskId));
    }
}
