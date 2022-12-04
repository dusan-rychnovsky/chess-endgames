package cz.dusanrychnovsky.chessendgames;

import java.util.List;

public interface UserInterface {

  void showTitle(String title);

  Board queryInitialSituation(List<Piece> pieces);

  Move queryMove(Situation situation);

  void showSituation(Situation situation);

  void showMove(Color color, Move move);

  void showResult(String result);
}
