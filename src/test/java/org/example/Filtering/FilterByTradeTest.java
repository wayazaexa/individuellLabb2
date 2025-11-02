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
 * Unit tests for {@link FilterByTrade} (dependency-injected version).
 */
class FilterByTradeTest {

    private CandidateRepository mockRepo;
    private Logger mockLogger;
    private FilterByTrade filter;

    private Candidate carpenter;
    private Candidate electrician;

    @BeforeEach
    void setUp() {
        mockRepo = mock(CandidateRepository.class);
        mockLogger = mock(Logger.class);

        carpenter = mock(Candidate.class);
        electrician = mock(Candidate.class);

        when(carpenter.getTrade()).thenReturn("Carpenter");
        when(electrician.getTrade()).thenReturn("Electrician");

        when(mockRepo.getCandidates()).thenReturn(Map.of(
                1, carpenter,
                2, electrician
        ));

        filter = new FilterByTrade(mockRepo, mockLogger);
    }

    @Test
    void testFilter_ReturnsMatchingTrade() {
        List<Candidate> result = filter.filter("Carpenter");

        assertEquals(1, result.size());
        assertSame(carpenter, result.get(0));

        verify(mockLogger).info("Candidate list filtered by trade: {}", "Carpenter");
    }

    @Test
    void testFilter_IsCaseInsensitive() {
        List<Candidate> result = filter.filter("eLeCtRiCiAn");

        assertEquals(1, result.size());
        assertSame(electrician, result.get(0));

        verify(mockLogger).info("Candidate list filtered by trade: {}", "eLeCtRiCiAn");
    }

    @Test
    void testFilter_NoMatches_ReturnsEmptyList() {
        List<Candidate> result = filter.filter("Plumber");

        assertTrue(result.isEmpty());

        verify(mockLogger).info("Candidate list filtered by trade: {}", "Plumber");
    }
}
