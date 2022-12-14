package cz.dusanrychnovsky.chessendgames.core;

public record PiecePosition (Color color, PieceType type, Position position) {
  public Piece piece() {
    return new Piece(color, type);
  }
}

