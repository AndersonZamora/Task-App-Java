package com.cincout.proyecto.task.model;

import com.cincout.proyecto.task.excepciones.TaskException;
import com.cincout.proyecto.task.persistence.TaskPersistence;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    List<Task> tasks;

    public TaskRepository() {
        tasks = TaskPersistence.loadTask();
    }

    public void save(Task task) throws TaskException {
        if (tasks.contains(task)) {
            throw new TaskException("La tarea ya registrada");
        }
        tasks.add(task);
        TaskPersistence.saveTask(tasks);
        printMessage("La tarea fue agregada exitosamente");
    }

    public Task findById(String id) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public List<Task> findCompletedTask() throws TaskException {
        List<Task> completedTask = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getCompleted()) {
                completedTask.add(task);
            }
        }
        if (completedTask.isEmpty()){
            throw  new TaskException("No hay tareas completadas");
        }

        return completedTask;
    }

    public List<Task> findPendingTask() throws TaskException {
        List<Task> pendingTask = new ArrayList<>();

        for (Task task : tasks) {
            if (!task.getCompleted()) {
                pendingTask.add(task);
            }
        }
        if (pendingTask.isEmpty()){
            throw  new TaskException("No hay tareas pendientes");
        }

        return pendingTask;
    }

    public void remove(String id) throws TaskException {
        Task task = findById(id);
        if (task == null) {
            throw new TaskException("La tarea no existe en la lista");
        }
        tasks.remove(task);
        printMessage("La tarea fue eliminada exitosamente");
        TaskPersistence.saveTask(tasks);
    }

    public void remove(Task task) throws TaskException {
        if (task == null) {
            throw new TaskException("La tarea no puede ser nula");
        }
        if (!tasks.contains(task)) {
            throw new TaskException("La tarea no existe en lista");
        }

        tasks.remove(task);
        TaskPersistence.saveTask(tasks);
    }

    public List<Task> findAll() throws TaskException {
        if (tasks.isEmpty()) {
            throw new TaskException("La lista esta vacia");
        }
        return tasks;
    }

    public int findIndexById(String id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public void updateTask(Task updaeTask) throws TaskException {
        if (updaeTask == null) {
            throw new TaskException("La tarea no puede ser nula");
        }
        int index = findIndexById(updaeTask.getId());
        if (index == -1) {
            throw new TaskException("El indice no es válido");
        }
        tasks.set(index, updaeTask);
        printMessage("La tarea fue actualizada exitosamente");
        TaskPersistence.saveTask(tasks);
    }

    public void updateTaskCompleted(String id, Boolean completed) throws TaskException {

        int index = findIndexById(id);
        if (index == -1) {
            throw new TaskException("El indice no es válido");
        }
        tasks.get(index).setCompleted(completed);
        printMessage("La tarea fue actualizada exitosamente");
        TaskPersistence.saveTask(tasks);
    }

    public void printMessage(String message){
        System.out.println(message);
    }
}
