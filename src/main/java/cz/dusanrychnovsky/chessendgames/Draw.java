package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.Result.Status.DRAW;

public class Draw implements Result {
  @Override
  public Status getStatus() {
    return DRAW;
  }
}
