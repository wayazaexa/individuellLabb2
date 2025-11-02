package org.example.Filtering;

import org.example.Models.Candidate;
import org.example.Repositories.CandidateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link FilterByExperience}.
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
    }

    @Test
    void testFilter_ReturnsCandidatesMeetingExperienceThreshold() {
        try (MockedStatic<CandidateRepository> repoStatic = mockStatic(CandidateRepository.class);
             MockedStatic<LoggerFactory> loggerStatic = mockStatic(LoggerFactory.class)) {

            // mock static singletons
            repoStatic.when(CandidateRepository::getInstance).thenReturn(mockRepo);
            loggerStatic.when(() -> LoggerFactory.getLogger(FilterByExperience.class)).thenReturn(mockLogger);

            when(mockRepo.getCandidates()).thenReturn(Map.of(
                    1, junior,
                    2, senior
            ));

            filter = new FilterByExperience();
            List<Candidate> result = filter.filter(5);

            assertEquals(1, result.size());
            assertSame(senior, result.get(0));

            verify(mockLogger).info("Candidate list filtered by years of work experience >= {}", 5);
        }
    }

    @Test
    void testFilter_AllCandidatesMeetThreshold() {
        try (MockedStatic<CandidateRepository> repoStatic = mockStatic(CandidateRepository.class);
             MockedStatic<LoggerFactory> loggerStatic = mockStatic(LoggerFactory.class)) {

            repoStatic.when(CandidateRepository::getInstance).thenReturn(mockRepo);
            loggerStatic.when(() -> LoggerFactory.getLogger(FilterByExperience.class)).thenReturn(mockLogger);

            when(mockRepo.getCandidates()).thenReturn(Map.of(
                    1, senior
            ));

            filter = new FilterByExperience();
            List<Candidate> result = filter.filter(5);

            assertEquals(1, result.size());
            assertSame(senior, result.get(0));
            verify(mockLogger).info("Candidate list filtered by years of work experience >= {}", 5);
        }
    }

    @Test
    void testFilter_NoCandidatesMeetThreshold_ReturnsEmptyList() {
        try (MockedStatic<CandidateRepository> repoStatic = mockStatic(CandidateRepository.class);
             MockedStatic<LoggerFactory> loggerStatic = mockStatic(LoggerFactory.class)) {

            repoStatic.when(CandidateRepository::getInstance).thenReturn(mockRepo);
            loggerStatic.when(() -> LoggerFactory.getLogger(FilterByExperience.class)).thenReturn(mockLogger);

            when(mockRepo.getCandidates()).thenReturn(Map.of(
                    1, junior
            ));

            filter = new FilterByExperience();
            List<Candidate> result = filter.filter(5);

            assertTrue(result.isEmpty());
            verify(mockLogger).info("Candidate list filtered by years of work experience >= {}", 5);
        }
    }
}
