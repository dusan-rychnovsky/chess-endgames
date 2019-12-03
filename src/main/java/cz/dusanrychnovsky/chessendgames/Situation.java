package cz.dusanrychnovsky.chessendgames;

public class Situation {

  public Situation(Player player, Player opponent, Board situation) {
  }

  public Board board() {
    return null;
  }

  public Status status() {
    return Status.InProgress;
  }

  public Situation next() {
    return this;
  }
}
