package cz.dusanrychnovsky.chessendgames.core;

public interface Player {

  Color getColor();
  Move pickMove(Situation situation);
}
