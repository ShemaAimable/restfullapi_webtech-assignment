package com.example.question5_task_api.controller;

import com.example.question5_task_api.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();

    public TaskController() {
        // Sample tasks
        tasks.add(new Task(1L, "Finish assignment", "Complete Spring Boot API assignment", false, "HIGH", "2026-02-15"));
        tasks.add(new Task(2L, "Grocery shopping", "Buy fruits and vegetables", false, "MEDIUM", "2026-02-12"));
        tasks.add(new Task(3L, "Call Mom", "Weekly call", true, "LOW", "2026-02-10"));
        tasks.add(new Task(4L, "Workout", "Gym session", false, "MEDIUM", "2026-02-11"));
    }

    // GET all tasks
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(tasks);
    }

    // GET task by ID
    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        for (Task t : tasks) {
            if (t.getTaskId().equals(taskId)) {
                return ResponseEntity.ok(t);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // GET tasks by completion status
    @GetMapping("/status")
    public ResponseEntity<List<Task>> getTasksByStatus(@RequestParam boolean completed) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.isCompleted() == completed) {
                result.add(t);
            }
        }
        return ResponseEntity.ok(result);
    }

    // GET tasks by priority
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Task>> getTasksByPriority(@PathVariable String priority) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getPriority().equalsIgnoreCase(priority)) {
                result.add(t);
            }
        }
        return ResponseEntity.ok(result);
    }

    // POST - create new task
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        tasks.add(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    // PUT - update task
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        for (Task t : tasks) {
            if (t.getTaskId().equals(taskId)) {
                t.setTitle(updatedTask.getTitle());
                t.setDescription(updatedTask.getDescription());
                t.setCompleted(updatedTask.isCompleted());
                t.setPriority(updatedTask.getPriority());
                t.setDueDate(updatedTask.getDueDate());
                return ResponseEntity.ok(t);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // PATCH - mark as completed
    @PatchMapping("/{taskId}/complete")
    public ResponseEntity<Task> markAsCompleted(@PathVariable Long taskId) {
        for (Task t : tasks) {
            if (t.getTaskId().equals(taskId)) {
                t.setCompleted(true);
                return ResponseEntity.ok(t);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // DELETE - delete task
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        for (Task t : tasks) {
            if (t.getTaskId().equals(taskId)) {
                tasks.remove(t);
                return ResponseEntity.noContent().build(); // 204
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
