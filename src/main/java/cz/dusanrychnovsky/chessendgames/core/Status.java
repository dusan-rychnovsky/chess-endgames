package cz.dusanrychnovsky.chessendgames.core;

/**
 * Represents status of a chess game situation. Can be either
 * {@link #IN_PROGRESS} if the game isn't finished or one of {@link #DRAW}
 * or {@link Win} if it is.
 */
public interface Status {

  Status IN_PROGRESS = new InProgress();
  Status DRAW = new Draw();

  static Status win(Color color) {
    return new Win(color);
  }

  String print();

  boolean isFinal();

  boolean isWin(Color color);
}
