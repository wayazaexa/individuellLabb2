package org.example.Filtering;

import org.example.Models.Candidate;

import java.util.List;

/**
 * Since I felt like the filtering would want to accept a parameter of what to search for while sorting wouldn't need
 * that, I chose to split them up into separate classes to conform with the Interface Segregation principle of SOLID.
 * This makes it easy to implement new filters and sorting methods in the future.
 */
public interface IFilter<T> {
    List<Candidate> filter(T filterBy);
}
