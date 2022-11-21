package cz.dusanrychnovsky.chessendgames;

import java.io.*;

import static cz.dusanrychnovsky.chessendgames.Move.parseMove;

public class StdInPlayer implements Player {

  private final BufferedReader reader;
  private final BufferedWriter writer;

  public StdInPlayer(BufferedReader reader, BufferedWriter writer) {
    this.reader = reader;
    this.writer = writer;
  }

  @Override
  public Move getMove(Situation situation) {
    try {
      while (true) {
        writer.write("Enter " + situation.color() + " move:\n");
        writer.flush();

        var line = reader.readLine();
        var move = parseMove(line);

        if (situation.isValid(move)) {
          return move;
        }

        writer.write("Move is not valid.\n");
      }
    }
    catch (IOException ex) {
      throw new RuntimeException("Unexpected IO exception.", ex);
    }
  }
}
