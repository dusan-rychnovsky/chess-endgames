package cz.dusanrychnovsky.chessendgames.core;

public interface Status {
  Status IN_PROGRESS = new InProgress();
  Status DRAW = new Draw();
}
