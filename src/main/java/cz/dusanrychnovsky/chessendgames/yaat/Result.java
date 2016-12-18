package cz.dusanrychnovsky.chessendgames.yaat;

public interface Result {

  public enum Status {
    IN_PROGRESS, DRAW, WIN
  }

  Status getStatus();
}
