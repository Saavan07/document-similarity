# Document Similarity

I worked on this Java project for my Data Structures course to compare text files using shared word sequences. It ranks document pairs by overlap so someone can review the matches. Similarity alone does not establish plagiarism.

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

There are D(D-1)/2 document pairs for D documents. The set-based path stores phrases in memory and computes overlap through union sizes; larger collections require more memory and pairwise comparisons.

## Scope and attribution

This repository contains my coursework implementation. When I revisited it in October 2026, I added command-line arguments and runnable examples, fixed the hardcoded phrase length, excluded unrelated directories from the interactive menu, handled short documents and small corpora, and preserved word boundaries when constructing phrases.

The Java files are in the repository root, and `examples/` contains the small documents used in the instructions above. This was coursework with assignment-provided material; I haven't fully documented the original starter-code attribution yet.

## Limitations

The tool counts exact normalized overlap. It does not detect semantic paraphrases, distinguish quoted text, or establish intent. ASCII-only normalization drops non-English letters; punctuation and numeric text may collapse. All phrases are retained in memory, and every pair is compared. Invalid nonnumeric command-line arguments produce a Java error.
