package cz.dusanrychnovsky.chessendgames.core;

/**
 * Represents color of a chess player and of chess pieces - white or black.
 */
public enum Color {
  WHITE, BLACK;

  /**
   * @return The color of the opponent.
   */
  public Color opposite() {
    if (this == WHITE) {
      return BLACK;
    }
    else {
      return WHITE;
    }
  }
}
