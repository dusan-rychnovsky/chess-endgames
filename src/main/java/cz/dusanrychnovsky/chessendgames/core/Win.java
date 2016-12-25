package cz.dusanrychnovsky.chessendgames.core;

import static cz.dusanrychnovsky.chessendgames.core.Result.Status.WIN;

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
  
  @Override
  public String toString() {
    return winnerColor + " WINS";
  }
}
