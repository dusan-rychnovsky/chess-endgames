package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static cz.dusanrychnovsky.chessendgames.Color.*;
import static cz.dusanrychnovsky.chessendgames.PieceType.*;
import static org.junit.Assert.assertNotEquals;

public class PieceTest {

  // ==========================================================================
  // Equality
  // ==========================================================================

  @Test
  public void samePiecesAreEqual() {
    assertEquals(new Piece(White, Rook), new Piece(White, Rook));
    assertEquals(new Piece(White, Rook).hashCode(), new Piece(White, Rook).hashCode());
  }

  @Test
  public void piecesWithDifferentColorsAreNotEqual() {
    assertNotEquals(new Piece(White, Rook), new Piece(Black, Rook));
    assertNotEquals(new Piece(White, Rook).hashCode(), new Piece(Black, Rook).hashCode());
  }

  @Test
  public void piecesWithDifferentTypesAreNotEqual() {
    assertNotEquals(new Piece(White, Rook), new Piece(White, King));
    assertNotEquals(new Piece(White, Rook).hashCode(), new Piece(White, King).hashCode());
  }
}
