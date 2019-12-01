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
}
