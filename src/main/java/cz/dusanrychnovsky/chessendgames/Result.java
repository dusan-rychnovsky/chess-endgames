package cz.dusanrychnovsky.chessendgames;

public interface Result {

  public enum Status {
    IN_PROGRESS, DRAW, WIN
  }

  Status getStatus();
}
