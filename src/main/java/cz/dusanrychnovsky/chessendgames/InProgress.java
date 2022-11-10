package cz.dusanrychnovsky.chessendgames;

public record InProgress () implements Status {

  @Override
  public String print() {
    return "In progress.";
  }

  @Override
  public boolean isFinal() {
    return false;
  }

  @Override
  public boolean isWin(Color color) {
    return false;
  }
}
