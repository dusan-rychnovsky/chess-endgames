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
  public void getMoveShouldRequestValidMove() {
    var in = new BufferedReader(new StringReader("B1 B2\n"));
    var writer = new StringWriter();
    var out = new BufferedWriter(writer);

    var player = new StdInPlayer(in, out);
    var result = player.getMove(
      new Situation(
        Color.WHITE,
        Board.builder()
          .add(WHITE_KING, B1)
          .add(WHITE_ROOK, A1)
          .add(BLACK_KING, D1)
          .build()
      )
    );

    assertEquals(new Move(B1, B2), result);
    assertEquals(
      "Enter WHITE move:\n",
      writer.toString()
    );
  }

  @Test
  public void getMoveShouldRequestAgainWhenGivenInvalidMove() {
    var in = new BufferedReader(new StringReader("B1 A1\nB1 B2\n"));
    var writer = new StringWriter();
    var out = new BufferedWriter(writer);

    var player = new StdInPlayer(in, out);
    var result = player.getMove(
      new Situation(
        Color.WHITE,
        Board.builder()
          .add(WHITE_KING, B1)
          .add(WHITE_ROOK, A1)
          .add(BLACK_KING, D1)
          .build()
      )
    );

    assertEquals(new Move(B1, B2), result);
    assertEquals(
      """
        Enter WHITE move:
        Move is not valid.
        Enter WHITE move:
        """,
      writer.toString()
    );
  }
}
