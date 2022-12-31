# Chess Endgames

Simluator of **king-rook chess endgame**. Launch the JAR file via double-click.

Supports two UI modes:

* Swing UI (the default).
* CMD line UI (enabled via `-CLI` argument).

Computer moves are precomputed using [Minimax algorithm](https://en.wikipedia.org/wiki/Minimax).

## Release

New releases are created by pushing GIT tags with `vX` naming convention (where `X` is a new version number). E.g.

```
git tag v1.0
git push origin v0.1
```

For more details, check the [Create Release](https://github.com/dusan-rychnovsky/chess-endgames/blob/master/.github/workflows/create-release.yml) Github Action definition.

## Commit validation

[![Java CI](https://github.com/dusan-rychnovsky/chess-endgames/actions/workflows/verify-commit.yml/badge.svg?branch=master)](https://github.com/dusan-rychnovsky/chess-endgames/actions/workflows/verify-commit.yml)

* [PMD](https://pmd.github.io/) Source code analyzer
* [SpotBugs](https://spotbugs.github.io/) Find bugs in java programs
* [JaCoCo](https://www.eclemma.org/jacoco/) Java code coverage library
