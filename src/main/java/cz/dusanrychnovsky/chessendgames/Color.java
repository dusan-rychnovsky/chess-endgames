package cz.dusanrychnovsky.chessendgames;

public enum Color {
  WHITE, BLACK;

  public static Color parse(String color) {
    return valueOf(color.toUpperCase());
  }

  public Color opposite() {
    if (this == WHITE) {
      return BLACK;
    }
    else {
      return WHITE;
    }
  }
}
