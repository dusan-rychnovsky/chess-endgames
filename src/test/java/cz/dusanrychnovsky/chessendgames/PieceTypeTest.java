package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static cz.dusanrychnovsky.chessendgames.Position.*;
import static cz.dusanrychnovsky.chessendgames.PieceType.*;

import java.util.Set;

public class PieceTypeTest {

  // ==========================================================================
  // Parsing
  // ==========================================================================

  @Test
  public void parse_supportsLowerCase() {
    assertEquals(KING, PieceType.parse("king"));
    assertEquals(ROOK, PieceType.parse("rook"));
  }

  @Test
  public void parse_supportsUpperCase() {
    assertEquals(KING, PieceType.parse("KING"));
    assertEquals(ROOK, PieceType.parse("ROOK"));
  }

  @Test
  public void parse_supportsCamelCase() {
    assertEquals(KING, PieceType.parse("King"));
    assertEquals(ROOK, PieceType.parse("Rook"));
  }

  // ==========================================================================
  // Rook Moving
  // ==========================================================================

  @Test
  public void ROOK_moves_returnsRowAndColumn() {
    assertSetEquals(
      Set.of(
        new Move(D3, D1),
        new Move(D3, D2),
        new Move(D3, D4),
        new Move(D3, D5),
        new Move(D3, D6),
        new Move(D3, D7),
        new Move(D3, D8),
        new Move(D3, A3),
        new Move(D3, B3),
        new Move(D3, C3),
        new Move(D3, E3),
        new Move(D3, F3),
        new Move(D3, G3),
        new Move(D3, H3)),
      ROOK.movesFrom(D3));
  }

  // ==========================================================================
  // King Moving
  // ==========================================================================

  @Test
  public void KING_moves_returnsAdjacent() {
    assertSetEquals(
      Set.of(
        new Move(D3, E2),
        new Move(D3, E3),
        new Move(D3, E4),
        new Move(D3, D2),
        new Move(D3, D4),
        new Move(D3, C2),
        new Move(D3, C3),
        new Move(D3, C4)),
      KING.movesFrom(D3)
    );
  }

  private <T> void assertSetEquals(Set<T> expected, Set<T> actual) {
    assertEquals(expected.size(), actual.size());
    for (var item : actual) {
      assertTrue("Should contain: " + item, expected.contains(item));
    }
  }
}
