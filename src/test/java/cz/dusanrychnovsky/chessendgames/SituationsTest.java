package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;

import static cz.dusanrychnovsky.chessendgames.Piece.*;
import static cz.dusanrychnovsky.chessendgames.Position.*;
import static cz.dusanrychnovsky.chessendgames.Color.*;
import static cz.dusanrychnovsky.chessendgames.IterableExtensions.contains;
import static cz.dusanrychnovsky.chessendgames.IterableExtensions.size;
import static org.junit.Assert.*;

public class SituationsTest {

  // ==========================================================================
  // All
  // ==========================================================================

  private static Iterable<Situation> ALL_SITUATIONS = Situations.all();

  @Test
  public void all_generatesAllValidSituations() {
    assertEquals(406_336, size(ALL_SITUATIONS));
  }

  @Test
  public void all_containsASampleValidSituation_twoFigures() {
    assertTrue(contains(ALL_SITUATIONS, new Situation(WHITE, Board.builder()
      .add(WhiteKing, C3)
      .add(BlackKing, E5)
      .build())));
  }

  @Test
  public void all_containsASampleValidSituation_threeFigures() {
    assertTrue(contains(ALL_SITUATIONS, new Situation(WHITE, Board.builder()
      .add(WhiteKing, C3)
      .add(WhiteRook, F1)
      .add(BlackKing, E5)
      .build())));
  }

  @Test
  public void all_doesNotContainInvalidSituation_twoWhiteKings() {
    assertFalse(contains(ALL_SITUATIONS, new Situation(WHITE, Board.builder()
      .add(WhiteKing, C3)
      .add(WhiteKing, E5)
      .build())));
  }

  @Test
  public void all_doesNotContainInvalidSituation_aSingleKing() {
    assertFalse(contains(ALL_SITUATIONS, new Situation(WHITE, Board.builder()
      .add(WhiteKing, C3)
      .build())));
  }

  @Test
  public void all_doesNotContainInvalidSituation_adjacentKings() {
    assertFalse(contains(ALL_SITUATIONS, new Situation(WHITE, Board.builder()
      .add(WhiteKing, C3)
      .add(BlackKing, D3)
      .build())));
  }
}
