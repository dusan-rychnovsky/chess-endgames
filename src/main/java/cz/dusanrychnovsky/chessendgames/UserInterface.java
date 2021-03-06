package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.core.Color;
import cz.dusanrychnovsky.chessendgames.core.Move;
import cz.dusanrychnovsky.chessendgames.core.Result;
import cz.dusanrychnovsky.chessendgames.core.Situation;

public interface UserInterface {
  void displayPrompt(String message);
  void displayChosenMove(Color color, Move move);
  void displayResult(Result result);
  void displaySituation(Situation situation);
  Move requestMove(Situation situation);
  Situation requestInitialSituation();
}
