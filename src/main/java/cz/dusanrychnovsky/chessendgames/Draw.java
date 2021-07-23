package cz.dusanrychnovsky.chessendgames;

import lombok.Value;

@Value
public class Draw implements Status {
  @Override
  public String print() {
    return "Draw.";
  }

  @Override
  public boolean IsFinal() {
    return true;
  }

  @Override
  public boolean IsWin(Color color) {
    return false;
  }
}
