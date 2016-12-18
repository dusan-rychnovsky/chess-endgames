package cz.dusanrychnovsky.chessendgames.yaat;

public interface Player {

  Color getColor();
  Move pickMove(Situation situation);
}
