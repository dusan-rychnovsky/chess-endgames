package cz.dusanrychnovsky.chessendgames;

import lombok.Value;

@Value
public class InProgress implements Status {
  @Override
  public String print() {
    return "In progress.";
  }
}
