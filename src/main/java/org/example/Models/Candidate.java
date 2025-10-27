package org.example.Models;

public record Candidate(String name, int age, String trade, int yearsOfWorkExperience) {
    @Override
    public String toString() {
        return "Candidate {" +
                "Name: '" + name + '\'' +
                ", age: " + age +
                ", trade: '" + trade + '\'' +
                ", yearsOfWorkExperience: " + yearsOfWorkExperience +
                '}';
    }
}
