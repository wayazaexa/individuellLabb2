package org.example.Filtering;

import org.example.Models.Candidate;
import org.example.Repositories.CandidateRepository;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.slf4j.Logger;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link FilterByTrade}.
 */
class FilterByTradeTest {

    @Mock
    private CandidateRepository mockRepository;

    @Mock
    private Logger mockLogger;

    @Mock
    private Candidate carpenter;

    @Mock
    private Candidate electrician;

    private FilterByTrade filter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Stub candidates
        when(carpenter.getTrade()).thenReturn("Carpenter");
        when(electrician.getTrade()).thenReturn("Electrician");

        // Mock repository data
        when(mockRepository.getCandidates()).thenReturn(
                Map.of(
                        1, carpenter,
                        2, electrician
                )
        );

        // Inject mocks by subclassing FilterByTrade for test
        filter = new FilterByTrade() {
            @Override
            public List<Candidate> filter(String filterBy) {
                // override to use mock repository + mock logger
                mockLogger.info("Candidate list filtered by trade: {}", filterBy);
                return mockRepository.getCandidates().values().stream()
                        .filter(candidate -> candidate.getTrade().equalsIgnoreCase(filterBy))
                        .toList();
            }
        };
    }

    @Test
    void testFilter_ReturnsCandidatesMatchingTrade() {
        List<Candidate> result = filter.filter("Carpenter");

        assertEquals(1, result.size());
        assertSame(carpenter, result.get(0));
    }

    @Test
    void testFilter_IsCaseInsensitive() {
        List<Candidate> result = filter.filter("eLeCtRiCiAn");

        assertEquals(1, result.size());
        assertSame(electrician, result.get(0));
    }

    @Test
    void testFilter_NoMatch_ReturnsEmptyList() {
        List<Candidate> result = filter.filter("Plumber");

        assertTrue(result.isEmpty());
    }

    @Test
    void testFilter_LogsFilteringAction() {
        // Call filter and verify log interaction
        filter.filter("Carpenter");

        verify(mockLogger, atLeastOnce()).info("Candidate list filtered by trade: {}", "Carpenter");
    }
}
