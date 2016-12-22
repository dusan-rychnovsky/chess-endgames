package cz.dusanrychnovsky.chessendgames.yaat;

public enum Color {
  WHITE, BLACK;

  public Color getOpponentColor() {
    if (this == WHITE) {
      return BLACK;
    }
    else {
      return WHITE;
    }
  }
}
