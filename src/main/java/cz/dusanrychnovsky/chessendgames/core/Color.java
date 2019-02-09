package cz.dusanrychnovsky.chessendgames.core;

public enum Color {
  WHITE, BLACK;

  public Color getOpponent() {
    switch (this) {
      case WHITE: return BLACK;
      case BLACK: return WHITE;
      default:
        throw new IllegalStateException("Unsupported Color: " + this);
    }
  }
}
