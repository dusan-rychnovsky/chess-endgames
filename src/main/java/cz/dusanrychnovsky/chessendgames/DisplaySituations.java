package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.core.EventListener;
import cz.dusanrychnovsky.chessendgames.core.Situation;
import cz.dusanrychnovsky.chessendgames.core.SituationPrinter;

public class DisplaySituations implements EventListener {

  private final UserInterface ui;
  
  public DisplaySituations(UserInterface ui) {
    this.ui = ui;
  }
  
  @Override
  public void onNewSituation(Situation situation) {
    ui.displaySituation(situation);
  }
}
