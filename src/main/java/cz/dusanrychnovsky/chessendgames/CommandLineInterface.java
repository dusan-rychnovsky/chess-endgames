package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.core.*;

import java.io.*;

import static cz.dusanrychnovsky.chessendgames.core.Color.WHITE;
import static cz.dusanrychnovsky.chessendgames.core.Piece.BLACK_KING;
import static cz.dusanrychnovsky.chessendgames.core.Piece.WHITE_KING;
import static cz.dusanrychnovsky.chessendgames.core.Piece.WHITE_ROOK;
import static cz.dusanrychnovsky.chessendgames.core.Position.A7;
import static cz.dusanrychnovsky.chessendgames.core.Position.A8;
import static cz.dusanrychnovsky.chessendgames.core.Position.F3;

public class CommandLineInterface implements UserInterface {
  
  private final SituationPrinter printer = new SituationPrinter();
  
  private final BufferedReader reader =
    new BufferedReader(new InputStreamReader(System.in));
  
  private final BufferedWriter writer =
    new BufferedWriter(new OutputStreamWriter(System.out));

  public CommandLineInterface() {
    writeLine("CHESS ENDGAMES"); // TODO: display version here
  }

  @Override
  public void displayPrompt(String message) {
    writeLine(message);
  }

  @Override
  public void displayChosenMove(Color color, Move move) {
    writeLine(color + " " + move);
  }

  @Override
  public void displayResult(Result result) {
    writeLine("RESULT: " + result);
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

  @Override
  public Situation requestInitialSituation() {

    Situation situation = requestSituation();
    while (situation == null) {
      writeLine("Invalid situation!");
      situation = requestSituation();
    }

    return situation;
  }

  private Situation requestSituation() {

    Position whiteKingPos = requestPosition(WHITE_KING);
    Position whiteRookPos = requestPosition(WHITE_ROOK);
    Position blackKingPos = requestPosition(BLACK_KING);

    Situation situation = null;
    try {
      situation = Situation.builder(WHITE)
          .addPiece(WHITE_KING, whiteKingPos)
          .addPiece(WHITE_ROOK, whiteRookPos)
          .addPiece(BLACK_KING, blackKingPos)
          .build();
    }
    catch (IllegalArgumentException | IllegalStateException ex) {
      // noop - leave situation null
    }

    return situation;
  }

  private Position requestPosition(Piece piece) {

    newLine();
    write("Place " + piece + ": ");
    Position pos = parsePosition(readLine());
    while (pos == null) {
      writeLine("Invalid position: ");
      write("Place " + piece + ": ");
      pos = parsePosition(readLine());
    }

    writeLine(piece + " " + pos);
    return pos;
  }

  private Position parsePosition(String line) {
    try {
      return Position.parseFrom(line);
    }
    catch (IllegalArgumentException ex) {
      return null;
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
