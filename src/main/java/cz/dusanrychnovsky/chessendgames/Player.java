package cz.dusanrychnovsky.chessendgames;

public interface Player {

  Color getColor();
  Move pickMove(Situation situation);
}
