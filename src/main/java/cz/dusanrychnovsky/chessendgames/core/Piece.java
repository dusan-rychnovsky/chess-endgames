package cz.dusanrychnovsky.chessendgames.core;

import static cz.dusanrychnovsky.chessendgames.core.Color.*;
import static cz.dusanrychnovsky.chessendgames.core.PieceType.*;

/**
 * Represents a chess piece with a certain color and a certain type.
 */
public record Piece (Color color, PieceType type) {

  public static final Piece WHITE_KING = new Piece(WHITE, KING);
  public static final Piece WHITE_ROOK = new Piece(WHITE, ROOK);
  public static final Piece BLACK_KING = new Piece(BLACK, KING);
  public static final Piece BLACK_ROOK = new Piece(BLACK, ROOK);

  public String print() {
    return color + " " + type;
  }
}
