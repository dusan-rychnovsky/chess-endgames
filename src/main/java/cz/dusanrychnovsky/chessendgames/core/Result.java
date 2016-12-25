package cz.dusanrychnovsky.chessendgames.core;

public interface Result {

  public enum Status {
    IN_PROGRESS, DRAW, WIN
  }

  Status getStatus();
}
