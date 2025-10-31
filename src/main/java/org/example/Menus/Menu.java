package org.example.Menus;

import java.util.Scanner;

public class Menu {
    final Scanner scanner = new Scanner(System.in);

    public void start() {}

    int handleIntInput(String text) {
        String userInput;
        int number;
        while (true) {
            System.out.print(text);
            userInput = scanner.nextLine().trim();
            try {
                number = Integer.parseInt(userInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println(userInput + " is not a number");
            }
        }
        return number;
    }
}
