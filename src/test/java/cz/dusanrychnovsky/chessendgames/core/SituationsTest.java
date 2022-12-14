package cz.dusanrychnovsky.chessendgames.core;

import org.junit.Test;

import java.util.Set;

import static cz.dusanrychnovsky.chessendgames.core.Piece.*;
import static cz.dusanrychnovsky.chessendgames.core.Position.*;
import static cz.dusanrychnovsky.chessendgames.core.Color.*;
import static org.junit.Assert.*;

public class SituationsTest {

  // ==========================================================================
  // All
  // ==========================================================================

  private static final Set<Situation> ALL_SITUATIONS = Situations.all();

  @Test
  public void allShouldGenerateAllValidSituations() {
    assertEquals(406_336, ALL_SITUATIONS.size());
  }

  @Test
  public void allShouldContainASampleValidSituationWithTwoFigures() {
    assertTrue(ALL_SITUATIONS.contains(
      new Situation(WHITE, Board.builder()
        .add(WHITE_KING, C3)
        .add(BLACK_KING, E5)
        .build()))
    );
  }

  @Test
  public void allShouldContainASampleValidSituationWithThreeFigures() {
    assertTrue(ALL_SITUATIONS.contains(
      new Situation(WHITE, Board.builder()
        .add(WHITE_KING, C3)
        .add(WHITE_ROOK, F1)
        .add(BLACK_KING, E5)
        .build()))
    );
  }

  @Test
  public void allShouldNotContainInvalidSituationWithTwoWhiteKings() {
    assertFalse(ALL_SITUATIONS.contains(
      new Situation(WHITE, Board.builder()
        .add(WHITE_KING, C3)
        .add(WHITE_KING, E5)
        .build()))
    );
  }

  @Test
  public void allShouldNotContainInvalidSituationWithSingleKing() {
    assertFalse(ALL_SITUATIONS.contains(
      new Situation(WHITE, Board.builder()
        .add(WHITE_KING, C3)
        .build()))
    );
  }

  @Test
  public void allSHouldNotNotContainInvalidSituationWithAdjacentKings() {
    assertFalse(ALL_SITUATIONS.contains(
      new Situation(WHITE, Board.builder()
        .add(WHITE_KING, C3)
        .add(BLACK_KING, D3)
        .build()))
    );
  }
}
