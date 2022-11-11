package cz.dusanrychnovsky.chessendgames;

public interface Status {

  Status IN_PROGRESS = new InProgress();
  Status DRAW = new Draw();

  static Status WIN(Color color) {
    return new Win(color);
  }

  String print();

  boolean isFinal();

  boolean isWin(Color color);
}
