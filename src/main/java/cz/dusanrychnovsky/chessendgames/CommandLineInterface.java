package cz.dusanrychnovsky.chessendgames;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import static cz.dusanrychnovsky.chessendgames.EnumExtensions.parseEnum;
import static cz.dusanrychnovsky.chessendgames.Move.parseMove;

public class CommandLineInterface implements UserInterface {

  private final BufferedReader in;
  private final BufferedWriter out;

  public CommandLineInterface(BufferedReader in, BufferedWriter out) {
    this.in = in;
    this.out = out;
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
      throw new RuntimeException(ex);
    }
  }

  private String readLine() {
    try {
      return in.readLine();
    }
    catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}
