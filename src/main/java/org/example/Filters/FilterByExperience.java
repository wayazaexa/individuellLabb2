package org.example.Filters;

import org.example.Models.Candidate;
import org.example.Repositories.CandidateRepository;

import java.util.List;

public class FilterByExperience implements IFilter<Integer> {
    @Override
    public List<Candidate> filter(CandidateRepository candidateRepository, Integer filterBy) {
        return candidateRepository.getCandidates().stream()
                .filter(candidate -> candidate.yearsOfWorkExperience() >= filterBy)
                .toList();
    }
}
