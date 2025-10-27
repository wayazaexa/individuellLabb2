package org.example.Filters;

import org.example.Models.Candidate;
import org.example.Repositories.CandidateRepository;

import java.util.List;

public interface IFilter<T> {
    List<Candidate> filter(CandidateRepository candidateRepository, T filterBy);
}
