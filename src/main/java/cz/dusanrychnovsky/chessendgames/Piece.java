package cz.dusanrychnovsky.chessendgames;

import lombok.Value;
import lombok.experimental.Accessors;

import static cz.dusanrychnovsky.chessendgames.Color.*;
import static cz.dusanrychnovsky.chessendgames.PieceType.*;

@Value
@Accessors(fluent = true)
public class Piece {

  public static final Piece WhiteKing = new Piece(White, King);
  public static final Piece WhiteRook = new Piece(White, Rook);
  public static final Piece BlackKing = new Piece(Black, King);
  public static final Piece BlackRook = new Piece(Black, Rook);

  Color color;
  PieceType type;
}
