package cz.dusanrychnovsky.chessendgames;

public enum Color {
  WHITE, BLACK;

  public Color opposite() {
    if (this == WHITE) {
      return BLACK;
    }
    else {
      return WHITE;
    }
  }
}
