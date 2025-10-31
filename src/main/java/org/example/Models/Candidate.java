package org.example.Models;

import org.example.Exceptions.InvalidCandidateAgeException;
import org.example.Exceptions.InvalidCandidateYearsExperienceException;
import org.example.Exceptions.MissingCandidateNameException;
import org.example.Exceptions.MissingCandidateTradeException;

public class Candidate {
    private final int id;
    private static int idGenerator;
    private final String name;
    private final int age;
    private final String trade;
    private final int yearsOfWorkExperience;

    public Candidate(String name, int age, String trade, int yearsOfWorkExperience){
        this.id = ++idGenerator;
        if (name == null || name.isBlank()) {
            throw new MissingCandidateNameException("Candidate must have a name");
        }
        this.name = name;
        if (age < 18) {
            throw new InvalidCandidateAgeException("Candidate can't be below 18 years old");
        }
        this.age = age;
        if (trade == null || trade.isBlank()) {
            throw new MissingCandidateTradeException("Candidate must have a trade");
        }
        this.trade = trade;
        if (yearsOfWorkExperience < 0) {
            throw new InvalidCandidateYearsExperienceException("Candidate can't have negative years of work experience");
        }
        this.yearsOfWorkExperience = yearsOfWorkExperience;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getTrade() {
        return trade;
    }

    public int getYearsOfWorkExperience() {
        return yearsOfWorkExperience;
    }

    @Override
    public String toString() {
        return "Id: " + id +
                ", name: '" + name + '\'' +
                ", age: " + age +
                ", trade: '" + trade + '\'' +
                ", yearsOfWorkExperience: " + yearsOfWorkExperience;
    }
}
