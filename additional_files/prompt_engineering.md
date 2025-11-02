## Using ChatGPT as a coding assistant
Since most of what I've used chatGPT for during this class has been generating complex stream()-functions in order to
get a grasp on what needs to be done and how one can achieve that, but this assignment contained rather basic stream()-
functions, I didn't have much use for chatGPT and chose to try it out for generating tests.

### Test generation
* using junit and mockito, can you generate tests for this class?

```
*and then I copy-pasted the entirety of my CandidateRepository-class*
```
Excellent — this is a clean, well-scoped repository class
Because CandidateRepository uses an internal Map and an SLF4J Logger, we can write unit tests using JUnit 5 and Mockito to:

Verify adding/removing behavior,

Ensure correct interaction with the logger, and

Confirm singleton behavior.

Here’s a complete example test suite
```
*and it gave me exactly what I've used in CandidateRepositoryTests, along with the code needed to include the JUnit,
Mockito, and SLF4J dependencies in my pom.xml. That part I had already added though, but it was a nice touch.
Lastly it included explanations of some key points:*
```


Key points explained:

| Concern                                                                 | How handled                                                   |
|-------------------------------------------------------------------------|---------------------------------------------------------------|
| Singleton	                                                              | Verified via assertSame() on two calls to getInstance()       |
| Logger	                                                                 | Mocked using Mockito and injected via subclass override       |
| Map behavior	| Checked using assertions on repository.getCandidates()        |
| Mockito verification| 	Used verify() to confirm correct log messages                |
| Isolation	| @BeforeEach creates a new repository with its own mock logger |

### Coding syntax
Funnily enough, my next question was about markdown:
* what's the keyword for a code block in markdown?

Great question

In Markdown, the keyword (or syntax) to start and end a code block is three backticks:
```
your code here
```


That’s the simplest form — it tells Markdown “this is a code block.”

✅ With language highlighting

If you specify a language right after the opening backticks, you get syntax highlighting:
```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
```
### More test generation

using junit and mockito, can you generate tests for this class?
```
*and then I copy-pasted the code for my FilterByTrade-class*
```
Perfect — this is a nice, focused class

It depends on two collaborators:

a CandidateRepository (singleton dependency), and

an SLF4J Logger (for logging).

We'll write unit tests using JUnit 5 and Mockito, verifying that:

Filtering returns the expected candidates,

Logging occurs correctly, and

Filtering is case-insensitive.

```
*after spitting out a test suite and reminders about the pom.xml, it also gave me a suggestion about refactoring
which I didn't notice until I gave it my FilterByExperience-class in the same manner*
```
Optional refactor suggestion

To make this class easier to test (without subclassing):

Inject CandidateRepository and Logger through the constructor.
Then your tests can pass mocks directly instead of subclassing.

Would you like me to show what that small refactor would look like (it keeps the same production behavior but makes it much cleaner to test)?

* Yes, please.

Excellent — refactoring for dependency injection is one of the best moves you can make for testability ✅

Your current FilterByExperience class directly calls:
```java
CandidateRepository.getInstance();
LoggerFactory.getLogger(FilterByExperience.class);
```

That makes it hard to test because it’s tied to static singletons.
Let’s make it cleaner, without changing its behavior for production code.

✅ Refactored FilterByExperience (dependency-injected version)
```java
package org.example.Filtering;

import org.example.Models.Candidate;
import org.example.Repositories.CandidateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Filters candidates by minimum years of work experience.
 * Uses dependency injection for easier testing.
 */
public class FilterByExperience implements IFilter<Integer> {

    private final CandidateRepository candidateRepository;
    private final Logger log;

    // Default constructor for normal app use
    public FilterByExperience() {
        this(CandidateRepository.getInstance(), LoggerFactory.getLogger(FilterByExperience.class));
    }

    // Constructor for testing / DI frameworks
    public FilterByExperience(CandidateRepository candidateRepository, Logger log) {
        this.candidateRepository = candidateRepository;
        this.log = log;
    }

    @Override
    public List<Candidate> filter(Integer filterBy) {
        log.info("Candidate list filtered by years of work experience >= {}", filterBy);
        return candidateRepository.getCandidates().values().stream()
                .filter(candidate -> candidate.getYearsOfWorkExperience() >= filterBy)
                .toList();
    }
}
```
What changed

| Before                                     | 	After                   |
|--------------------------------------------|--------------------------|
| CandidateRepository created internally	    | Injected via constructor |
| LoggerFactory.getLogger(...) inside class	 | Injected via constructor |
|Needed static mocking in tests|	No static mocking needed — pass mocks directly|
|Harder to reuse / test	|Fully testable and dependency-injection–friendly|

```
*It then gave me the updated test class, a short explanation about why this approach is better,
and asked if I wanted it to refactor FilterByTrade in the same DI-style and show matching tests.
I answered yes, please yet again and was given the updated FilterByTrade and tests for it*
```

## Final Thoughts

While using AI can give more of a headache than it solves, as long as you give it clear instructions and double-check
its proposed solution, it can greatly cut time spent generating boilerplate code. It can even be helpful with coding
style for junior developers, making sure our code is more up to general coding standards (at the time it was trained,
but for a beginners course, that's plenty enough).
