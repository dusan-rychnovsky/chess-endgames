package cz.dusanrychnovsky.chessendgames.yaat;

import static cz.dusanrychnovsky.chessendgames.yaat.Result.Status.DRAW;

public class Draw implements Result {
  @Override
  public Status getStatus() {
    return DRAW;
  }
}
