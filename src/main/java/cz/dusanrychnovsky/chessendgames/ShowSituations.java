package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.core.EventListener;
import cz.dusanrychnovsky.chessendgames.core.Situation;
import cz.dusanrychnovsky.chessendgames.core.SituationPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class ShowSituations implements EventListener {
  
  private final SituationPrinter printer = new SituationPrinter();
  private final DisplaySituation displaySituation;
  
  public ShowSituations(DisplaySituation displaySituation) {
    this.displaySituation = displaySituation;
  }
  
  @Override
  public void onNewSituation(Situation situation) {
    displaySituation.displaySituation(situation);
  }
}
