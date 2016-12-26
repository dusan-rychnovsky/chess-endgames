package cz.dusanrychnovsky.chessendgames.core;

import static cz.dusanrychnovsky.chessendgames.core.Color.*;
import static cz.dusanrychnovsky.chessendgames.core.PieceType.*;

public enum Piece {

  WHITE_KING(WHITE, KING),
  WHITE_ROOK(WHITE, ROOK),
  BLACK_KING(BLACK, KING);
  
  public static Piece get(Color color, PieceType type) {
    if (color == WHITE) {
      if (type == KING) {
        return WHITE_KING;
      }
      else {
        return WHITE_ROOK;
      }
    }
    else {
      return BLACK_KING;
    }
  }
  
  private final Color color;
  private final PieceType type;
  
  Piece(Color color, PieceType type) {
    this.color = color;
    this.type = type;
  }
  
  public Color getColor() {
    return color;
  }
  
  public PieceType getType() {
    return type;
  }
}