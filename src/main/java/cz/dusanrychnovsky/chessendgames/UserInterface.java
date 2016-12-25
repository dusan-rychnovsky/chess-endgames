package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.core.Move;
import cz.dusanrychnovsky.chessendgames.core.Situation;

public interface UserInterface {
  void displayMessage(String message);
  void displaySituation(Situation situation);
  Move requestMove(Situation situation);
}
