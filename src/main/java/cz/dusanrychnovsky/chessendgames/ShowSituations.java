package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.core.EventListener;
import cz.dusanrychnovsky.chessendgames.core.Situation;
import cz.dusanrychnovsky.chessendgames.core.SituationPrinter;

public class ShowSituations implements EventListener {
  
  private final SituationPrinter printer = new SituationPrinter();
  private final UserInterface ui;
  
  public ShowSituations(UserInterface ui) {
    this.ui = ui;
  }
  
  @Override
  public void onNewSituation(Situation situation) {
    ui.displaySituation(situation);
  }
}
