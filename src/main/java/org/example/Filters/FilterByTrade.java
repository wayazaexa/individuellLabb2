package org.example.Filters;

import org.example.Models.Candidate;
import org.example.Repositories.CandidateRepository;

import java.util.List;

public class FilterByTrade implements IFilter<String> {
    @Override
    public List<Candidate> filter(CandidateRepository candidateRepository, String filterBy) {
        return candidateRepository.getCandidates().stream()
                .filter(candidate -> candidate.trade().equals(filterBy))
                .toList();
    }
}
