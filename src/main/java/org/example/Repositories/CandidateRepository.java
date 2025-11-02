package org.example.Repositories;

import org.example.Models.Candidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * This relates to most if not all classes, but I had to pick one to write about so here we are.
 * This class's sole responsibility is storing the list of candidates and a couple of methods to present said list,
 * add to it, and remove from it. This is in accordance with the Single Responsibility principle of SOLID, and ensures
 * that the code base is organized, easy to test, and not tightly coupled.
 */
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
