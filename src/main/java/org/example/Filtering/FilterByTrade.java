package org.example.Filtering;

import org.example.Models.Candidate;
import org.example.Repositories.CandidateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FilterByTrade implements IFilter<String> {
    private final CandidateRepository candidateRepository = CandidateRepository.getInstance();
    private final Logger log = LoggerFactory.getLogger(FilterByTrade.class);

    @Override
    public List<Candidate> filter(String filterBy) {
        log.info("Candidate list filtered by trade: {}", filterBy);
        return candidateRepository.getCandidates().values().stream()
                .filter(candidate -> candidate.getTrade().equalsIgnoreCase(filterBy))
                .toList();
    }
}
