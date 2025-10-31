package org.example.Repositories;

import org.example.Models.Candidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class CandidateRepository {
    private final Map<Integer, Candidate> candidates = new HashMap<>();
    private static CandidateRepository instance;
    private final Logger log = LoggerFactory.getLogger(CandidateRepository.class);

    public static CandidateRepository getInstance() {
        if (instance == null) {
            instance = new CandidateRepository();
        }
        return instance;
    }

    public Map<Integer, Candidate> getCandidates() {
        return candidates;
    }

    public void addCandidate(Candidate candidate) {
        if (candidate != null) {
            candidates.put(candidate.getId(), candidate);
            log.info("Candidate {} added", candidate);
        }
    }

    public void removeCandidate(int id) {
        Candidate removed = candidates.remove(id);
        if (removed == null) {
            log.warn("No candidate with id {} found in the system", id);
        }
        else {
            log.info("Candidate {} removed from the system", removed);
        }
    }
}
