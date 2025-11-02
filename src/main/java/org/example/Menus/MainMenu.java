package org.example.Menus;

import org.example.Factories.CandidateFactory;
import org.example.Repositories.CandidateRepository;

public class MainMenu extends Menu {
    CandidateRepository candidateRepository = CandidateRepository.getInstance();

    public void start() {
        while (true) {
            showMenu();
            int userChoice = handleIntInput("Enter your choice: ");
            switch (userChoice) {
                case 1 -> addCandidate();
                case 2 -> removeCandidate();
                case 3 -> viewCandidates();
                case 4 -> filterCandidates();
                case 5 -> { return; }
                default -> System.out.println(userChoice + " is not a valid choice");
            }
        }
    }

    private void showMenu() {
        System.out.println("\nWelcome to our Candidate Tracking System");
        System.out.println("1. Add candidate");
        System.out.println("2. Remove candidate");
        System.out.println("3. View candidates");
        System.out.println("4. Filter candidates");
        System.out.println("5. Exit program");
    }

    private void addCandidate() {
        System.out.print("\nEnter candidate's name: ");
        String name = scanner.nextLine().trim();

        int age = handleIntInput("Enter candidate's age: ");

        System.out.print("Enter candidate's trade: ");
        String trade = scanner.nextLine().trim();

        int yearsOfWorkExperience = handleIntInput("Enter candidate's years of work experience: ");

        CandidateFactory candidateFactory = new CandidateFactory();
        candidateRepository.addCandidate(candidateFactory.createCandidate(name, age, trade, yearsOfWorkExperience));
    }

    private void removeCandidate() {
        viewCandidates();
        int userChoice = handleIntInput("Enter the id of the candidate you want to remove: ");
        candidateRepository.removeCandidate(userChoice);
    }

    private void viewCandidates() {
        System.out.println("List of candidates:");
        candidateRepository.getCandidates()
                .forEach((key, value) -> System.out.println(value));
    }

    private void filterCandidates() {
        FilterMenu menu = new FilterMenu();
        menu.start();
    }
}
