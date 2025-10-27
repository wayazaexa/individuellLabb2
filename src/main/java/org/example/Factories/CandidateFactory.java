package org.example.Factories;

import org.example.Exceptions.InvalidCandidateAgeException;
import org.example.Exceptions.InvalidCandidateYearsExperienceException;
import org.example.Exceptions.MissingCandidateNameException;
import org.example.Exceptions.MissingCandidateTradeException;
import org.example.Models.Candidate;

public class CandidateFactory {
    public Candidate createCandidate(String name, int age, String trade, int yearsOfWorkExperience) {
        if (name == null || name.isBlank()) {
            throw new MissingCandidateNameException("Candidate must have a name");
        }
        if (age < 18) {
            throw new InvalidCandidateAgeException("Candidate can't be below 18 years old");
        }
        if (trade == null || trade.isBlank()) {
            throw new MissingCandidateTradeException("Candidate must have a trade");
        }
        if (yearsOfWorkExperience < 0) {
            throw new InvalidCandidateYearsExperienceException("Candidate can't have negative years of work experience");
        }
        return new Candidate(name, age, trade, yearsOfWorkExperience);
    }
}
