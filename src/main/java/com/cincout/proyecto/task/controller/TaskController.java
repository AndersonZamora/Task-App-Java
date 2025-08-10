package com.cincout.proyecto.task.controller;

import com.cincout.proyecto.task.excepciones.TaskException;
import com.cincout.proyecto.task.excepciones.TaskValidationException;
import com.cincout.proyecto.task.model.Task;
import com.cincout.proyecto.task.model.TaskRepository;

import java.util.List;

public class TaskController {

    private final TaskRepository _taskRepository;

    public TaskController(TaskRepository taskRepository) {
        _taskRepository = taskRepository;
    }

    public void addTask(String id, String title, String description, Boolean completed) throws TaskValidationException, TaskException {
        validateTaskData(id, title, description, completed);
        Task task = new Task(id, title, description, completed);
        _taskRepository.save(task);
    }

    public void removeTask(String id) throws TaskValidationException, TaskException {
        if (id == null || id.trim().isEmpty()) {
            throw new TaskValidationException("El id no puede estas vacio");
        }
        _taskRepository.remove(id);

    }

    public void showTask() throws TaskValidationException, TaskException {
        List<Task> tasks = _taskRepository.findAll();
        if (tasks.isEmpty()) {
            throw new TaskValidationException("La lista no puede estar vacio");
        }
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void showCompletedTask() throws TaskValidationException, TaskException {
        List<Task> tasks = _taskRepository.findCompletedTask();
        if (tasks.isEmpty()) {
            throw new TaskValidationException("La lista no puede estar vacio");
        }
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void showPendingTask() throws TaskValidationException, TaskException {
        List<Task> tasks = _taskRepository.findPendingTask();
        if (tasks.isEmpty()) {
            throw new TaskValidationException("La lista no puede estar vacio");
        }
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void updateTask(String id, String title, String description, Boolean completed) throws TaskValidationException, TaskException {
        validateTaskData(id, title, description, completed);
        Task updateTask = new Task(id, title, description, completed);
        _taskRepository.updateTask(updateTask);
    }

    public void updateTask(String id, Boolean completed) throws TaskValidationException, TaskException {
        validateTaskData(id, completed);
        _taskRepository.updateTaskCompleted(id,completed);
    }

    private void validateTaskData(String id, String title, String description, Boolean completed) throws TaskValidationException {
        if (id == null || id.trim().isEmpty()) {
            throw new TaskValidationException("El id no puede estas vacio");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new TaskValidationException("El title no puede estas vacio");
        }

        if (description == null || description.trim().isEmpty()) {
            throw new TaskValidationException("La descripción no puede estas vacio");
        }

        if (completed == null) {
            throw new TaskValidationException("El completed no puede ser nulo");
        }
    }

    private void validateTaskData(String id,Boolean completed) throws TaskValidationException {
        if (id == null || id.trim().isEmpty()) {
            throw new TaskValidationException("El id no puede estas vacio");
        }

        if (completed == null) {
            throw new TaskValidationException("El completed no puede ser nulo");
        }
    }
}
