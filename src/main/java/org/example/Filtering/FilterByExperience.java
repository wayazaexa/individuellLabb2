package org.example.Filtering;

import org.example.Models.Candidate;
import org.example.Repositories.CandidateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Filters candidates by minimum years of work experience.
 * Uses dependency injection for easier testing.
 */
public record FilterByExperience(CandidateRepository candidateRepository, Logger log) implements IFilter<Integer> {
    public FilterByExperience() {
        this(CandidateRepository.getInstance(), LoggerFactory.getLogger(FilterByExperience.class));
    }

    @Override
    public List<Candidate> filter(Integer filterBy) {
        log.info("Candidate list filtered by years of work experience >= {}", filterBy);
        return candidateRepository.getCandidates().values().stream()
                .filter(candidate -> candidate.getYearsOfWorkExperience() >= filterBy)
                .toList();
    }
}
