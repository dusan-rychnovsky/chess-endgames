package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.StringReader;
import java.io.StringWriter;

import static cz.dusanrychnovsky.chessendgames.Piece.*;
import static cz.dusanrychnovsky.chessendgames.Position.*;
import static org.junit.Assert.assertEquals;

public class StdInPlayerTest {

  @Test
  public void getMove_requestsValidMove() {
    var in = new BufferedReader(new StringReader("B1 B2\n"));
    var writer = new StringWriter();
    var out = new BufferedWriter(writer);

    var player = new StdInPlayer(Color.White, in, out);
    var result = player.getMove(
      new Situation(
        Color.White,
        Board.builder()
          .add(WhiteKing, B1)
          .add(WhiteRook, A1)
          .add(BlackKing, D1)
          .build()
      )
    );

    assertEquals(new Move(B1, B2), result);
    assertEquals(
      "Enter White move:\n",
      writer.toString()
    );
  }

  @Test
  public void getMove_givenInvalidMove_requestsAgain() {
    var in = new BufferedReader(new StringReader("B1 A1\nB1 B2\n"));
    var writer = new StringWriter();
    var out = new BufferedWriter(writer);

    var player = new StdInPlayer(Color.White, in, out);
    var result = player.getMove(
      new Situation(
        Color.White,
        Board.builder()
          .add(WhiteKing, B1)
          .add(WhiteRook, A1)
          .add(BlackKing, D1)
          .build()
      )
    );

    assertEquals(new Move(B1, B2), result);
    assertEquals(
      "Enter White move:\n" +
        "Move is not valid.\n" +
        "Enter White move:\n",
      writer.toString()
    );
  }
}
