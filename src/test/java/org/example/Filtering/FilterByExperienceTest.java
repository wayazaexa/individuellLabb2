package org.example.Filtering;

import org.example.Models.Candidate;
import org.example.Repositories.CandidateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for FilterByExperience (dependency-injected version).
 */
class FilterByExperienceTest {

    private CandidateRepository mockRepo;
    private Logger mockLogger;
    private FilterByExperience filter;

    private Candidate junior;
    private Candidate senior;

    @BeforeEach
    void setUp() {
        mockRepo = mock(CandidateRepository.class);
        mockLogger = mock(Logger.class);

        junior = mock(Candidate.class);
        senior = mock(Candidate.class);

        when(junior.getYearsOfWorkExperience()).thenReturn(2);
        when(senior.getYearsOfWorkExperience()).thenReturn(10);

        when(mockRepo.getCandidates()).thenReturn(Map.of(1, junior, 2, senior));

        filter = new FilterByExperience(mockRepo, mockLogger);
    }

    @Test
    void testFilter_ReturnsMatchingCandidates() {
        List<Candidate> result = filter.filter(5);

        assertEquals(1, result.size());
        assertSame(senior, result.get(0));
        verify(mockLogger).info("Candidate list filtered by years of work experience >= {}", 5);
    }

    @Test
    void testFilter_NoMatches_ReturnsEmptyList() {
        List<Candidate> result = filter.filter(15);

        assertTrue(result.isEmpty());
        verify(mockLogger).info("Candidate list filtered by years of work experience >= {}", 15);
    }

    @Test
    void testFilter_AllMeetThreshold() {
        List<Candidate> result = filter.filter(1);

        assertEquals(2, result.size());
        verify(mockLogger).info("Candidate list filtered by years of work experience >= {}", 1);
    }
}
