package org.example.Menus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class MainMenu {
    private final Logger log = LoggerFactory.getLogger(MainMenu.class);
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        String userInput;
        int userChoice;
        while (true) {
            showMenu();
            userInput = scanner.nextLine().trim();
            try {
                userChoice = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                log.error("{}, where user should have entered a number", e.getMessage());
                userChoice = -1;
            }
            switch (userChoice) {
                case 5 -> { return; }
                default -> System.out.println("Ogiltigt val\n");
            }
        }
    }

    private void showMenu() {
        System.out.println("Welcome to our Candidate Tracking System");
    }
}
