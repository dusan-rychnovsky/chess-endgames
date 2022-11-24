package cz.dusanrychnovsky.chessendgames;

import java.util.List;

public interface UserInterface {

  void printTitle(String title);

  Board queryInitialSituation(List<Piece> pieces);

  Move queryMove(Situation situation);

  void printSituation(Situation situation);

  void printResult(String result);
}
