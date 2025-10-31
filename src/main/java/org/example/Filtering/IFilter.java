package org.example.Filtering;

import org.example.Models.Candidate;

import java.util.List;

public interface IFilter<T> {
    List<Candidate> filter(T filterBy);
}
