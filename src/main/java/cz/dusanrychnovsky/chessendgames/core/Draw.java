package cz.dusanrychnovsky.chessendgames.core;

public record Draw () implements Status {

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
