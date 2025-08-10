package com.cincout.proyecto;

import com.cincout.proyecto.task.controller.TaskController;
import com.cincout.proyecto.task.model.TaskRepository;
import com.cincout.proyecto.task.view.TaskView;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TaskRepository repository = new TaskRepository();
        TaskController controller = new TaskController(repository);
        TaskView view = new TaskView(controller);

        view.showMenu();
    }
}