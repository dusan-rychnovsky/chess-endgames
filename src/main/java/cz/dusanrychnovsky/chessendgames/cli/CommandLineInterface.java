package cz.dusanrychnovsky.chessendgames.cli;

import cz.dusanrychnovsky.chessendgames.core.*;
import cz.dusanrychnovsky.chessendgames.util.UnexpectedException;

import java.io.*;
import java.util.List;

import static cz.dusanrychnovsky.chessendgames.util.EnumExtensions.parseEnum;
import static cz.dusanrychnovsky.chessendgames.core.Move.parseMove;
import static java.nio.charset.StandardCharsets.UTF_8;

public class CommandLineInterface implements UserInterface {

  private final BufferedReader in;
  private final BufferedWriter out;

  public CommandLineInterface(InputStream in, OutputStream out) {
    this.in = new BufferedReader(new InputStreamReader(in, UTF_8));
    this.out = new BufferedWriter(new OutputStreamWriter(out, UTF_8));
  }

  @Override
  public void showTitle(String title) {
    print(title + "\n\n");
  }

  @Override
  public Board queryInitialSituation(List<Piece> pieces) {
    print("Enter positions of all pieces on the board.\n");
    var builder = new Board.Builder();
    for (var piece : pieces) {
      print(piece.print() + ":\n");
      var line = readLine();
      var pos = parseEnum(line, Position.class);
      builder.add(piece, pos);
    }
    print("\n");
    return builder.build();
  }

  @Override
  public Move queryMove(Situation situation) {
    while (true) {
      print("Enter " + situation.color() + " move:\n");

      var line = readLine();
      var move = parseMove(line);

      if (situation.isValid(move)) {
        print("\n");
        return move;
      }

      print("Move is not valid.\n");
    }
  }

  @Override
  public void showSituation(Situation situation) {
    print(situation.print() + "\n");
  }

  @Override
  public void showMove(Color color, Move move) {
    print(color + " move: " + move.print() + "\n\n");
  }

  @Override
  public void showResult(String result) {
    print(result + "\n");
  }

  private void print(String text) {
    try {
      out.write(text);
      out.flush();
    }
    catch (IOException ex) {
      throw new UnexpectedException(ex);
    }
  }

  private String readLine() {
    try {
      return in.readLine();
    }
    catch (IOException ex) {
      throw new UnexpectedException(ex);
    }
  }
}
