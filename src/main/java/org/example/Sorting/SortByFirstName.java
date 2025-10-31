package org.example.Sorting;

import org.example.Models.Candidate;
import org.example.Repositories.CandidateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;

public class SortByFirstName implements ISort {
    private final CandidateRepository candidateRepository = CandidateRepository.getInstance();
    private final Logger log = LoggerFactory.getLogger(SortByFirstName.class);

    @Override
    public List<Candidate> sort() {
        log.info("Candidate list sorted by first name");
        return candidateRepository.getCandidates().values().stream()
                .sorted(Comparator.comparing(Candidate::getName))
                .toList();
    }
}
