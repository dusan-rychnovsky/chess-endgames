package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.Result.Status.IN_PROGRESS;

public class InProgress implements Result {
  @Override
  public Status getStatus() {
    return IN_PROGRESS;
  }
}
