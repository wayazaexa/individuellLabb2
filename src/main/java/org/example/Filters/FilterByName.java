package org.example.Filters;

import org.example.Models.Candidate;
import org.example.Repositories.CandidateRepository;

import java.util.Comparator;
import java.util.List;

// Should this really filter by name as the assignment says, or does he mean sort by name?
public class FilterByName implements IFilter<String> {
    @Override
    public List<Candidate> filter(CandidateRepository candidateRepository, String filterBy) {
        return candidateRepository.getCandidates().stream()
                .sorted(Comparator.comparing(Candidate::name))
                .toList();
    }
}
