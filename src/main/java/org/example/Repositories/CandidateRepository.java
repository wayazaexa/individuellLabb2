package org.example.Repositories;

import org.example.Models.Candidate;

import java.util.ArrayList;
import java.util.List;

public class CandidateRepository {
    private final List<Candidate> candidates = new ArrayList<>();
    private static CandidateRepository instance;

    public static CandidateRepository getInstance() {
        if (instance == null) {
            instance = new CandidateRepository();
        }
        return instance;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void addCandidate(Candidate candidate) {
        candidates.add(candidate);
    }
}
