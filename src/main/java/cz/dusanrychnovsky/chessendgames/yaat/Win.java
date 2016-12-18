package cz.dusanrychnovsky.chessendgames.yaat;

import static cz.dusanrychnovsky.chessendgames.yaat.Result.Status.WIN;

public class Win implements Result {

  private final Color winnerColor;

  public Win(Color winnerColor) {
    this.winnerColor = winnerColor;
  }

  @Override
  public Status getStatus() {
    return WIN;
  }

  public Color getWinnerColor() {
    return winnerColor;
  }
}
