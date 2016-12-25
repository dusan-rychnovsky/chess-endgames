package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.core.*;

import java.io.*;

public class CommandLineInterface implements UserInterface {
  
  private final SituationPrinter printer = new SituationPrinter();
  
  private final BufferedReader reader =
    new BufferedReader(new InputStreamReader(System.in));
  
  private final BufferedWriter writer =
    new BufferedWriter(new OutputStreamWriter(System.out));
  
  @Override
  public void displayMessage(String message) {
    writeLine(message);
  }
  
  @Override
  public void displaySituation(Situation situation) {
    newLine();
    writeLine(printer.printSituation(situation));
    newLine();
  }
  
  @Override
  public Move requestMove(Situation situation) {
    Color color = situation.getCurrentColor();
  
    write(color + " Enter move: ");
    Move move = parseMove(readLine());
  
    while (move == null || !situation.isValidMove(move)) {
      writeLine("Invalid move!");
      write(color + " Enter move: ");
      move = parseMove(readLine());
    }
  
    return move;
  }
  
  private Move parseMove(String line) {
    try {
      return Move.parseFrom(line);
    }
    catch (IllegalArgumentException ex) {
      return null;
    }
  }
  
  private String readLine() {
    try {
      return reader.readLine();
    }
    catch (IOException ex) {
      throw new RuntimeException();
    }
  }
  
  private void write(String message) {
    try {
      writer.write(message);
      writer.flush();
    }
    catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
  
  private void writeLine(String message) {
    try {
      writer.write(message);
      writer.newLine();
      writer.flush();
    }
    catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
  
  private void newLine() {
    try {
      writer.newLine();
      writer.flush();
    }
    catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}
