package org.example.Factories;

import org.example.Models.Candidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CandidateFactory {
    Logger log = LoggerFactory.getLogger(CandidateFactory.class);
    public Candidate createCandidate(String name, int age, String trade, int yearsOfWorkExperience) {
        try {
            return new Candidate(name, age, trade, yearsOfWorkExperience);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
