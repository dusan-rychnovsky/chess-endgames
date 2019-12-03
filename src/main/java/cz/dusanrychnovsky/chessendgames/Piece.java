package cz.dusanrychnovsky.chessendgames;

public class Piece {

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

    Piece other = (Piece) obj;
    return
      this.color == other.color &&
      this.type == other.type;
  }

  @Override
  public int hashCode() {
    return this.color.hashCode() + this.type.hashCode();
  }
}
