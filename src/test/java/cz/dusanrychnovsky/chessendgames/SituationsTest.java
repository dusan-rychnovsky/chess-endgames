package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;

import java.util.Set;

import static cz.dusanrychnovsky.chessendgames.Piece.*;
import static cz.dusanrychnovsky.chessendgames.Position.*;
import static cz.dusanrychnovsky.chessendgames.Color.*;
import static org.junit.Assert.*;

public class SituationsTest {

  // ==========================================================================
  // All
  // ==========================================================================

  private static Set<Situation> ALL_SITUATIONS = Situations.all();

  @Test
  public void all_generatesAllValidSituations() {
    assertEquals(406_336, ALL_SITUATIONS.size());
  }

  @Test
  public void all_containsASampleValidSituation_twoFigures() {
    assertTrue(ALL_SITUATIONS.contains(
      new Situation(WHITE, Board.builder()
        .add(WhiteKing, C3)
        .add(BlackKing, E5)
        .build()))
    );
  }

  @Test
  public void all_containsASampleValidSituation_threeFigures() {
    assertTrue(ALL_SITUATIONS.contains(
      new Situation(WHITE, Board.builder()
        .add(WhiteKing, C3)
        .add(WhiteRook, F1)
        .add(BlackKing, E5)
        .build()))
    );
  }

  @Test
  public void all_doesNotContainInvalidSituation_twoWhiteKings() {
    assertFalse(ALL_SITUATIONS.contains(
      new Situation(WHITE, Board.builder()
        .add(WhiteKing, C3)
        .add(WhiteKing, E5)
        .build()))
    );
  }

  @Test
  public void all_doesNotContainInvalidSituation_aSingleKing() {
    assertFalse(ALL_SITUATIONS.contains(
      new Situation(WHITE, Board.builder()
        .add(WhiteKing, C3)
        .build()))
    );
  }

  @Test
  public void all_doesNotContainInvalidSituation_adjacentKings() {
    assertFalse(ALL_SITUATIONS.contains(
      new Situation(WHITE, Board.builder()
        .add(WhiteKing, C3)
        .add(BlackKing, D3)
        .build()))
    );
  }
}
