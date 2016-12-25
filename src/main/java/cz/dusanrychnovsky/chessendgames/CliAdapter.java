package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.core.Color;
import cz.dusanrychnovsky.chessendgames.core.Move;
import cz.dusanrychnovsky.chessendgames.core.Situation;
import cz.dusanrychnovsky.chessendgames.core.SituationPrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CliAdapter {
  
  public static class PrintMessageToConsole implements DisplayMessage {
    @Override
    public void displayMessage(String message) {
      System.out.println(message);
    }
  }
  
  public static class PrintSituationToConsole implements DisplaySituation {
    private SituationPrinter printer = new SituationPrinter();
    @Override
    public void displaySituation(Situation situation) {
      System.out.println();
      System.out.println(printer.printSituation(situation));
      System.out.println();
    }
  }
  
  public static class ParseMoveFromConsole implements GatherMove {
    
    private final BufferedReader reader =
      new BufferedReader(new InputStreamReader(System.in));
    
    @Override
    public Move gatherMove(Situation situation) {
      Color color = situation.getCurrentColor();
      
      System.out.print(color + " Enter move: ");
      Move move = parseMove(readLine());
  
      while (move == null || !situation.isValidMove(move)) {
        System.out.println("Invalid move!");
        System.out.print(color + " Enter move: ");
        move = parseMove(readLine());
      }
  
      return move;
    }
  
    private String readLine() {
      try {
        return reader.readLine();
      }
      catch (IOException ex) {
        throw new RuntimeException();
      }
    }
  
    private Move parseMove(String line) {
      try {
        return Move.parseFrom(line);
      }
      catch (IllegalArgumentException ex) {
        return null;
      }
    }
  }
}
