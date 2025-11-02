package org.example.Sorting;

import org.example.Models.Candidate;
import org.example.Repositories.CandidateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;

public record SortByFirstName(CandidateRepository candidateRepository, Logger log) implements ISort {
    public SortByFirstName() {
        this(CandidateRepository.getInstance(), LoggerFactory.getLogger(SortByFirstName.class));
    }

    @Override
    public List<Candidate> sort() {
        log.info("Candidate list sorted by first name");
        return candidateRepository.getCandidates().values().stream()
                .sorted(Comparator.comparing(Candidate::getName))
                .toList();
    }
}
