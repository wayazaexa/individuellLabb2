package org.example.Filtering;

import org.example.Models.Candidate;
import org.example.Repositories.CandidateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Filters candidates by trade name.
 * Uses dependency injection for easier testing.
 */
public record FilterByTrade(CandidateRepository candidateRepository, Logger log) implements IFilter<String> {
    public FilterByTrade() {
        this(CandidateRepository.getInstance(), LoggerFactory.getLogger(FilterByTrade.class));
    }

    @Override
    public List<Candidate> filter(String filterBy) {
        log.info("Candidate list filtered by trade: {}", filterBy);
        return candidateRepository.getCandidates().values().stream()
                .filter(candidate -> candidate.getTrade().equalsIgnoreCase(filterBy))
                .toList();
    }
}
