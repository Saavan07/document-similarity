# Document overlap detector

A Java data structures course project that ranks pairs of text files by shared word sequences. This is a similarity signal for human review, not proof of plagiarism.

## Run

Requires a JDK. Verified with OpenJDK 17.

```bash
javac -d out *.java
java -cp out PlagiaristCatcher examples 2 1
```

Arguments are the folder, phrase length, and minimum shared phrases. Running without arguments lists document folders in the current directory. The example uses small synthetic documents and includes a one-word file to exercise short input handling.

For the example above, `alpha.txt` and `beta.txt` share two distinct two-word phrases: `red green` and `green blue`. With phrase length 3, they share one: `red green blue`.

## Implementation

- `FileReader.java` loads text, normalizes ASCII letters and case, generates word sequences, and sorts filenames for stable traversal.
- `HitList.java` creates a set of distinct phrases for each document, compares every document pair, and ranks qualifying results by overlap count.
- `HitCounter.java` retains a separate sorted-list comparison implementation.
- `PlagiaristCatcher.java` handles folder and parameter selection.

There are D(D-1)/2 document pairs for D documents. The set-based path stores phrases in memory and computes overlap through union sizes. No corpus-scale performance claim is made without a saved reproducible benchmark.

## Scope and attribution

Saavan Kiran's coursework implementation is preserved in the repository history. The October 2026 maintenance update adds explicit command-line arguments and reproducible examples, fixes the hardcoded phrase length, excludes unrelated directories from the interactive menu, handles short documents and small corpora, and preserves word boundaries when constructing phrases.

The repository originally had no `src/` directory or bundled document corpus; the instructions above match the actual layout. Original assignment and starter-code attribution are not fully documented, so this README does not claim that every provided interface or scaffold was authored from scratch.

## Limitations

The tool counts exact normalized overlap. It does not detect semantic paraphrases, distinguish quoted text, or establish intent. ASCII-only normalization drops non-English letters; punctuation and numeric text may collapse. All phrases are retained in memory, and every pair is compared. Invalid nonnumeric command-line arguments produce a Java error.

No new license is assigned by this documentation update.
