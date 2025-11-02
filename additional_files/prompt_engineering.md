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
