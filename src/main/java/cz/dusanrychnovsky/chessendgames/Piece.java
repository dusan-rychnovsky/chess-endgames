package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.Color.*;
import static cz.dusanrychnovsky.chessendgames.PieceType.*;

public record Piece (Color color, PieceType type) {

  public static final Piece WHITE_KING = new Piece(WHITE, KING);
  public static final Piece WHITE_ROOK = new Piece(WHITE, ROOK);
  public static final Piece BLACK_KING = new Piece(BLACK, KING);
  public static final Piece BLACK_ROOK = new Piece(BLACK, ROOK);
}
