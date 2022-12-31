# Chess Endgames

Simluator of **king-rook chess endgame**. Launch the JAR file via double-click.

Supports two UI modes:

* Swing UI (the default).
* CMD line UI (enabled via `-CLI` argument).

Computer moves are precomputed using [Minimax algorithm](https://en.wikipedia.org/wiki/Minimax).

## Commit validation

[![Java CI](https://github.com/dusan-rychnovsky/chess-endgames/actions/workflows/verify-commit.yml/badge.svg?branch=master)](https://github.com/dusan-rychnovsky/chess-endgames/actions/workflows/verify-commit.yml)

* [PMD](https://pmd.github.io/) Source code analyzer
* [SpotBugs](https://spotbugs.github.io/) Find bugs in java programs
* [JaCoCo](https://www.eclemma.org/jacoco/) Java code coverage library
