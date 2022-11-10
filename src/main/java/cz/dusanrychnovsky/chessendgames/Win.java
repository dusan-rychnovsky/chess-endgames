package cz.dusanrychnovsky.chessendgames;

public record Win (Color color) implements Status {

  @Override
  public String print() {
    return "Mate. " + color + " wins.";
  }

  @Override
  public boolean isFinal() {
    return true;
  }

  @Override
  public boolean isWin(Color color) {
    return color.equals(this.color);
  }
}
