package cz.dusanrychnovsky.chessendgames;

import lombok.Value;

@Value
public class Win implements Status {
  Color color;

  @Override
  public String print() {
    return "Mate. " + color + " wins.";
  }

  @Override
  public boolean IsFinal() {
    return true;
  }
}
