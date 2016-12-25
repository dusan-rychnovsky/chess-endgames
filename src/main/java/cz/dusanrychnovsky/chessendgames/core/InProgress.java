package cz.dusanrychnovsky.chessendgames.core;

import static cz.dusanrychnovsky.chessendgames.core.Result.Status.IN_PROGRESS;

public class InProgress implements Result {
  @Override
  public Status getStatus() {
    return IN_PROGRESS;
  }
}
