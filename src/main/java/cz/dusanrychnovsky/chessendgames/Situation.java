package cz.dusanrychnovsky.chessendgames;

public class Situation {

  private final Color color;
  private final Board board;

  public Situation(Color color, Board board) {
    this.color = color;
    this.board = board;
  }

  public Board board() {
    return null;
  }

  public Status status() {
    return Status.InProgress;
  }

  public Situation next(Move move) {
    return this;
  }

  public String print() {
    return this.color + "\n" + this.board.print();
  }
}
