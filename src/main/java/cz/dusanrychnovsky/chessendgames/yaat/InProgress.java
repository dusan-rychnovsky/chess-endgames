package cz.dusanrychnovsky.chessendgames.yaat;

import static cz.dusanrychnovsky.chessendgames.yaat.Result.Status.IN_PROGRESS;

public class InProgress implements Result {
  @Override
  public Status getStatus() {
    return IN_PROGRESS;
  }
}
