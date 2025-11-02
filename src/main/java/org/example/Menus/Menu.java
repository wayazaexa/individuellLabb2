package org.example.Menus;

import java.util.Scanner;

/**
 * Splitting up the menus into a main class with two classes that extends from it to implement their functionality was
 * my attempt at fulfilling the Open-Closed Principle of SOLID. The more I wrote it the less it felt like it made sense
 * to do it this way though, so I'm not very happy about how this ended up.
 */
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
