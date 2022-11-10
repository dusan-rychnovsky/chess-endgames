package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.Color.*;
import static cz.dusanrychnovsky.chessendgames.PieceType.*;

public record Piece (Color color, PieceType type) {

  public static final Piece WhiteKing = new Piece(WHITE, KING);
  public static final Piece WhiteRook = new Piece(WHITE, ROOK);
  public static final Piece BlackKing = new Piece(BLACK, KING);
  public static final Piece BlackRook = new Piece(BLACK, ROOK);
}
