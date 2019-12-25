package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.Color.*;
import static cz.dusanrychnovsky.chessendgames.PieceType.*;

public class Piece {

  public static final Piece WhiteKing = new Piece(White, King);
  public static final Piece WhiteRook = new Piece(White, Rook);
  public static final Piece BlackKing = new Piece(Black, King);
  public static final Piece BlackRook = new Piece(Black, Rook);

  private final Color color;
  private final PieceType type;

  public Piece(Color color, PieceType type) {
    this.color = color;
    this.type = type;
  }

  public Color color() {
    return this.color;
  }

  public PieceType type() {
    return this.type;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Piece)) {
      return false;
    }

    var other = (Piece) obj;
    return
      this.color == other.color &&
      this.type == other.type;
  }

  @Override
  public int hashCode() {
    return this.color.hashCode() + this.type.hashCode();
  }
}
