package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.core.EventListener;
import cz.dusanrychnovsky.chessendgames.core.Situation;
import cz.dusanrychnovsky.chessendgames.core.SituationPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class PrintSituations implements EventListener {
  
  private final SituationPrinter printer = new SituationPrinter();
  private final BufferedWriter writer;
  
  public PrintSituations(OutputStream out) {
    writer = new BufferedWriter(new OutputStreamWriter(out));
  }
  
  @Override
  public void onNewSituation(Situation situation) {
    try {
      writer.write("\n");
      writer.write(printer.printSituation(situation) + "\n");
      writer.write("\n");
      writer.flush();
    }
    catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}
