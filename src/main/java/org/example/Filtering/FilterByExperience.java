package org.example.Filtering;

import org.example.Models.Candidate;
import org.example.Repositories.CandidateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FilterByExperience implements IFilter<Integer> {
    private final CandidateRepository candidateRepository = CandidateRepository.getInstance();
    private final Logger log = LoggerFactory.getLogger(FilterByExperience.class);

    @Override
    public List<Candidate> filter(Integer filterBy) {
        log.info("Candidate list filtered by years of work experience >= {}", filterBy);
        return candidateRepository.getCandidates().values().stream()
                .filter(candidate -> candidate.getYearsOfWorkExperience() >= filterBy)
                .toList();
    }
}
