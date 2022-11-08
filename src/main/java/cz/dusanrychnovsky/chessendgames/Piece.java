package cz.dusanrychnovsky.chessendgames;

import lombok.Value;
import lombok.experimental.Accessors;

import static cz.dusanrychnovsky.chessendgames.Color.*;
import static cz.dusanrychnovsky.chessendgames.PieceType.*;

@Value
@Accessors(fluent = true)
public class Piece {

  public static final Piece WhiteKing = new Piece(WHITE, KING);
  public static final Piece WhiteRook = new Piece(WHITE, ROOK);
  public static final Piece BlackKing = new Piece(BLACK, KING);
  public static final Piece BlackRook = new Piece(BLACK, ROOK);

  Color color;
  PieceType type;
}
