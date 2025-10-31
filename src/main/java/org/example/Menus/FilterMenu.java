package org.example.Menus;

import org.example.Filtering.FilterByExperience;
import org.example.Filtering.FilterByTrade;
import org.example.Filtering.IFilter;
import org.example.Models.Candidate;
import org.example.Repositories.CandidateRepository;
import org.example.Sorting.ISort;
import org.example.Sorting.SortByFirstName;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class FilterMenu extends Menu {
    CandidateRepository candidateRepository = CandidateRepository.getInstance();

    public void start() {
        while (true) {
            showMenu();
            int userChoice = handleIntInput("Enter your choice: ");
            switch (userChoice) {
                case 1 -> filterByTrade();
                case 2 -> filterByYearsOfWorkExperience();
                case 3 -> sortByFirstName();
                case 4 -> { return; }
                default -> System.out.println(userChoice + " is not a valid choice");
            }
        }
    }

    private void showMenu() {
        System.out.println("\nIn which way do you want to filter the candidates?");
        System.out.println("1. Filter by trade");
        System.out.println("2. Filter by years of work experience");
        System.out.println("3. Sort by first name");
        System.out.println("4. Return to main menu");
    }

    private void filterByTrade() {
        List<String> tradesWithDupes = candidateRepository.getCandidates().values().stream()
                .map(Candidate::getTrade)
                .toList();
        List<String> trades = new ArrayList<>(new HashSet<>(tradesWithDupes));

        System.out.println("\nTrades currently in the system:");
        IntStream.range(0, trades.size())
                .forEach(i -> System.out.println((i + 1) + ". " + trades.get(i)));
        int userChoice;
        System.out.println("Which trade do you want to see?");
        while (true) {
            userChoice = handleIntInput("Enter your choice: ");
            if (userChoice < 1 || userChoice > trades.size()) {
                System.out.println("Invalid choice");
            }
            else {
                break;
            }
        }

        IFilter<String> filtering = new FilterByTrade();
        System.out.println("\nCandidates filtered by " + trades.get(userChoice - 1) + ":");
        filtering.filter(trades.get(userChoice - 1)).forEach(System.out::println);
    }

    private void filterByYearsOfWorkExperience() {
        int userChoice = handleIntInput("\nEnter the minimum amount of years of work experience you want to see: ");
        IFilter<Integer> filtering = new FilterByExperience();
        System.out.println("Candidates filtered by " + userChoice + " or more years of work experience");
        filtering.filter(userChoice).forEach(System.out::println);
    }

    private void sortByFirstName() {
        ISort sorting = new SortByFirstName();
        System.out.println("Candidates sorted by first name:");
        sorting.sort().forEach(System.out::println);
    }
}
