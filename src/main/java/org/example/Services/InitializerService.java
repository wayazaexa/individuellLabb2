package org.example.Services;

import org.example.Factories.CandidateFactory;
import org.example.Menus.MainMenu;
import org.example.Repositories.CandidateRepository;

public class InitializerService {
    private final CandidateRepository candidateRepository = CandidateRepository.getInstance();

    public InitializerService() {
        initializeCandidates();
    }

    private void initializeCandidates() {
        CandidateFactory candidateFactory = new CandidateFactory();
        candidateRepository.addCandidate(candidateFactory.createCandidate("Kalle Karlsson", 24, "Front-end", 2));
        candidateRepository.addCandidate(candidateFactory.createCandidate("Pelle Andersson", 27, "Fullstack", 2));
        candidateRepository.addCandidate(candidateFactory.createCandidate("Stina Larsson", 26, "Front-end", 8));
        candidateRepository.addCandidate(candidateFactory.createCandidate("Lisa Svensson", 29, "Fullstack", 6));
        candidateRepository.addCandidate(candidateFactory.createCandidate("Kalle Svensson", 34, "Back-end", 6));
        candidateRepository.addCandidate(candidateFactory.createCandidate("Lisa Larsson", 33, "Back-end", 5));
    }

    public void run() {
        MainMenu menu = new MainMenu();
        menu.start();
    }
}
