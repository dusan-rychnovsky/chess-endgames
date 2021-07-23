package cz.dusanrychnovsky.chessendgames;

public interface Status {

  Status InProgress = new InProgress();
  Status Draw = new Draw();

  static Status win(Color color) {
    return new Win(color);
  }

  String print();

  boolean IsFinal();

  boolean IsWin(Color color);
}
