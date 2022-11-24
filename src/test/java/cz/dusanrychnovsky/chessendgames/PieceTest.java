package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;

import static cz.dusanrychnovsky.chessendgames.Piece.WHITE_KING;
import static org.junit.Assert.assertEquals;

import static cz.dusanrychnovsky.chessendgames.Color.*;
import static cz.dusanrychnovsky.chessendgames.PieceType.*;
import static org.junit.Assert.assertNotEquals;

public class PieceTest {

  // ==========================================================================
  // Print
  // ==========================================================================

  @Test
  public void printShouldSerializePieceInHumanReadableForm() {
    assertEquals("WHITE KING", WHITE_KING.print());
  }

  // ==========================================================================
  // Equality
  // ==========================================================================

  @Test
  public void samePiecesAreEqual() {
    assertEquals(new Piece(WHITE, ROOK), new Piece(WHITE, ROOK));
    assertEquals(new Piece(WHITE, ROOK).hashCode(), new Piece(WHITE, ROOK).hashCode());
  }

  @Test
  public void piecesWithDifferentColorsAreNotEqual() {
    assertNotEquals(new Piece(WHITE, ROOK), new Piece(BLACK, ROOK));
    assertNotEquals(new Piece(WHITE, ROOK).hashCode(), new Piece(BLACK, ROOK).hashCode());
  }

  @Test
  public void piecesWithDifferentTypesAreNotEqual() {
    assertNotEquals(new Piece(WHITE, ROOK), new Piece(WHITE, KING));
    assertNotEquals(new Piece(WHITE, ROOK).hashCode(), new Piece(WHITE, KING).hashCode());
  }
}
