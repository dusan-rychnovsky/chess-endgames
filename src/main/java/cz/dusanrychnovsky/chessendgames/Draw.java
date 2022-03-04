package cz.dusanrychnovsky.chessendgames;

import lombok.Value;

@Value
public class Draw implements Status {
  @Override
  public String print() {
    return "Draw.";
  }

  @Override
  public boolean isFinal() {
    return true;
  }

  @Override
  public boolean isWin(Color color) {
    return false;
  }
}
