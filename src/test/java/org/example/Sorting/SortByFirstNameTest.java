package org.example.Sorting;

import org.example.Models.Candidate;
import org.example.Repositories.CandidateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link SortByFirstName}.
 */
class SortByFirstNameTest {

    private CandidateRepository mockRepo;
    private Logger mockLogger;

    private Candidate alice;
    private Candidate bob;
    private Candidate charlie;

    private SortByFirstName sorter;

    @BeforeEach
    void setUp() {
        mockRepo = mock(CandidateRepository.class);
        mockLogger = mock(Logger.class);

        alice = mock(Candidate.class);
        bob = mock(Candidate.class);
        charlie = mock(Candidate.class);

        when(alice.getName()).thenReturn("Alice");
        when(bob.getName()).thenReturn("Bob");
        when(charlie.getName()).thenReturn("Charlie");

        sorter = new SortByFirstName(mockRepo, mockLogger);
    }

    @Test
    void testSort_SortsCandidatesByNameAscending() {
        Map<Integer, Candidate> unordered = new LinkedHashMap<>();
        unordered.put(2, charlie);
        unordered.put(3, alice);
        unordered.put(1, bob);

        when(mockRepo.getCandidates()).thenReturn(unordered);

        List<Candidate> result = sorter.sort();

        assertEquals(List.of(alice, bob, charlie), result);
        verify(mockLogger).info("Candidate list sorted by first name");
        verify(mockRepo).getCandidates();
    }

    @Test
    void testSort_HandlesAlreadySortedList() {
        Map<Integer, Candidate> ordered = Map.of(
                1, alice,
                2, bob,
                3, charlie
        );
        when(mockRepo.getCandidates()).thenReturn(ordered);

        List<Candidate> result = sorter.sort();

        assertEquals(List.of(alice, bob, charlie), result);
        verify(mockLogger).info("Candidate list sorted by first name");
    }

    @Test
    void testSort_SameNames_PreservesStableOrder() {
        Candidate alice2 = mock(Candidate.class);
        when(alice2.getName()).thenReturn("Alice");

        Map<Integer, Candidate> unordered = new LinkedHashMap<>();
        unordered.put(1, alice);
        unordered.put(2, alice2);
        unordered.put(3, bob);

        when(mockRepo.getCandidates()).thenReturn(unordered);

        List<Candidate> result = sorter.sort();

        // Both "Alice" entries come before "Bob"
        assertEquals("Alice", result.get(0).getName());
        assertEquals("Alice", result.get(1).getName());
        assertEquals("Bob", result.get(2).getName());
        verify(mockLogger).info("Candidate list sorted by first name");
    }

    @Test
    void testSort_EmptyList_ReturnsEmpty() {
        when(mockRepo.getCandidates()).thenReturn(Map.of());

        List<Candidate> result = sorter.sort();

        assertTrue(result.isEmpty());
        verify(mockLogger).info("Candidate list sorted by first name");
    }
}
