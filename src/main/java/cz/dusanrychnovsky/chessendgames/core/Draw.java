package cz.dusanrychnovsky.chessendgames.core;

import static cz.dusanrychnovsky.chessendgames.core.Result.Status.DRAW;

public class Draw implements Result {
  @Override
  public Status getStatus() {
    return DRAW;
  }
}
