package cz.dusanrychnovsky.chessendgames;

import lombok.Value;
import lombok.experimental.Accessors;

@Value
@Accessors(fluent = true)
public class Situation {

  Color color;
  Board board;

  public Status status() {
    return Status.InProgress;
  }

  public Situation next(Move move) {
    return new Situation(color.opposite(), board);
  }

  public String print() {
    return color + "\n" + board.print();
  }
}
