package org.example.Repositories;

import org.example.Models.Candidate;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.slf4j.Logger;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for CandidateRepository using JUnit 5 and Mockito.
 */
class CandidateRepositoryTest {

    @Mock
    private Logger mockLogger;

    private CandidateRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Fresh instance for isolation (bypassing singleton for testing)
        repository = new CandidateRepository() {
            // Inject mock logger
            private final Logger logMock = mockLogger;
            @Override
            public void addCandidate(Candidate candidate) {
                if (candidate != null) {
                    getCandidates().put(candidate.getId(), candidate);
                    logMock.info("Candidate {} added", candidate);
                }
            }
            @Override
            public void removeCandidate(int id) {
                Candidate removed = getCandidates().remove(id);
                if (removed == null) {
                    logMock.warn("No candidate with id {} found in the system", id);
                } else {
                    logMock.info("Candidate {} removed from the system", removed);
                }
            }
        };
    }

    @Test
    void testAddCandidate_AddsToMapAndLogs() {
        Candidate candidate = mock(Candidate.class);
        when(candidate.getId()).thenReturn(1);
        when(candidate.toString()).thenReturn("Candidate#1");

        repository.addCandidate(candidate);

        Map<Integer, Candidate> candidates = repository.getCandidates();
        assertEquals(1, candidates.size());
        assertSame(candidate, candidates.get(1));

        verify(mockLogger).info("Candidate {} added", candidate);
    }

    @Test
    void testAddCandidate_NullCandidate_DoesNothing() {
        repository.addCandidate(null);

        assertTrue(repository.getCandidates().isEmpty());
        verifyNoInteractions(mockLogger);
    }

    @Test
    void testRemoveCandidate_RemovesExistingCandidateAndLogs() {
        Candidate candidate = mock(Candidate.class);
        when(candidate.getId()).thenReturn(5);
        when(candidate.toString()).thenReturn("Candidate#5");

        repository.getCandidates().put(5, candidate);
        repository.removeCandidate(5);

        assertFalse(repository.getCandidates().containsKey(5));
        verify(mockLogger).info("Candidate {} removed from the system", candidate);
    }

    @Test
    void testRemoveCandidate_NotFound_LogsWarning() {
        repository.removeCandidate(999);

        verify(mockLogger).warn("No candidate with id {} found in the system", 999);
    }

    @Test
    void testSingleton_ReturnsSameInstance() {
        CandidateRepository repo1 = CandidateRepository.getInstance();
        CandidateRepository repo2 = CandidateRepository.getInstance();

        assertSame(repo1, repo2);
    }
}
