package com.cincout.proyecto.task.helper;

import java.util.Scanner;

public class HelperInputs {

    private final Scanner _scanner;

    public HelperInputs(Scanner scanner) {
        _scanner = scanner;
    }

    public String validInputText(String text) {
        String input;
        do {
            System.out.println("Ingresar " + text);
            input = _scanner.nextLine();
            if (input.trim().isEmpty()) {
                String message = "se requiere " + text;
                System.out.println(message.toUpperCase());
            }
        } while (input.isEmpty());

        return input;
    }

    public Boolean validInputBoolean(String text) {
        Boolean value = null;
        while (value == null) {
            System.out.println(text);
            String input = _scanner.nextLine().trim().toLowerCase();
            if (input.equals("si")) {
                value = true;
            } else if (input.equals("no")) {
                value = false;
            } else {
                System.out.println("El valor ingresado no es correcto");
            }
        }

        return value;
    }
}
