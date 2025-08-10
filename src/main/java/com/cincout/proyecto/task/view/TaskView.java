package com.cincout.proyecto.task.view;

import com.cincout.proyecto.task.controller.TaskController;
import com.cincout.proyecto.task.excepciones.TaskException;
import com.cincout.proyecto.task.excepciones.TaskValidationException;
import com.cincout.proyecto.task.helper.HelperInputs;
import com.cincout.proyecto.task.model.Task;

import java.util.Scanner;

public class TaskView {
    private final TaskController _TaskController;
    private final Scanner _scanner;

    public TaskView(TaskController TaskController) {
        _TaskController = TaskController;
        _scanner = new Scanner(System.in);

    }

    HelperInputs _helperInputs = new HelperInputs(new Scanner(System.in));

    public void showMenu() {
        while (true) {
            System.out.println("\n Gestión de Tareas");
            System.out.println("[1] Agregar Tarea");
            System.out.println("[2] Eliminar Tarea");
            System.out.println("[3] Actualizar Tarea");
            System.out.println("[4] Mostrar Tareas");
            System.out.println("[5] Actualizar estado de tarea");
            System.out.println("[6] Mostrar Tareas Pendientes");
            System.out.println("[7] Mostrar Tareas Completadas");
            System.out.println("[8] Salir");
            System.out.println("Seleccione una opcción");

            String option = _scanner.nextLine();

            switch (option) {
                case "1":
                    addTaskView();
                    break;
                case "2":
                    removeTasView();
                    break;
                case "3":
                    updateTaskView();
                    break;
                case "4":
                    showTaskView();
                    break;
                case "5":
                    updateTaskCompletedView();
                    break;
                case "6":
                    showTaskPendingView();
                    break;
                case "7":
                    showTaskCompletedView();
                    break;
                case "8":
                    System.out.println("Saliendo del sistema");
                    return;
                default:
                    System.out.println("Opcion invalida");
            }
        }
    }

    public void addTaskView() {
        try {
            Task task = getTaskInput();

            _TaskController.addTask(task.getId(), task.getTitle(), task.getDescription(), task.getCompleted());
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado");
            e.printStackTrace();
        }
    }

    public void removeTasView() {
        try {
            System.out.println("Ingrese el id a eliminar");
            String id = _scanner.nextLine();
            _TaskController.removeTask(id);
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado");
            e.printStackTrace();
        }
    }

    public void showTaskView() {
        try {
            System.out.println("\nLa lista de tarea");
            _TaskController.showTask();
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado");
            e.printStackTrace();
        }
    }

    public void showTaskPendingView() {
        try {
            System.out.println("\nLa lista de tareas pendientes");
            _TaskController.showPendingTask();
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado");
            e.printStackTrace();
        }
    }

    public void showTaskCompletedView() {
        try {
            System.out.println("\nLa lista de tareas completas");
            _TaskController.showCompletedTask();
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado");
            e.printStackTrace();
        }
    }

    public void updateTaskView() {
        try {
            Task task = getTaskInput();
            _TaskController.updateTask(task.getId(), task.getTitle(), task.getDescription(), task.getCompleted());
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado");
            e.printStackTrace();
        }
    }

    public void updateTaskCompletedView() {
        try {
            String id = _helperInputs.validInputText("el id");
            Boolean completed = _helperInputs.validInputBoolean("¿Está completada? si/no");
            _TaskController.updateTask(id, completed);
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado");
            e.printStackTrace();
        }
    }

    private Task getTaskInput() {

        String id = _helperInputs.validInputText("el id");
        String title = _helperInputs.validInputText("el titulo");
        String description = _helperInputs.validInputText("la  descripcion");
        Boolean completed = _helperInputs.validInputBoolean("¿Está completada? si/no");

        return new Task(id, title, description, completed);
    }
}
