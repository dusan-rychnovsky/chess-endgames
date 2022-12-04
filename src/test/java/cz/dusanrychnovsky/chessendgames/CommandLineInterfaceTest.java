package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;

import java.io.*;
import java.util.List;

import static cz.dusanrychnovsky.chessendgames.Color.WHITE;
import static cz.dusanrychnovsky.chessendgames.Piece.*;
import static cz.dusanrychnovsky.chessendgames.Position.*;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertEquals;

public class CommandLineInterfaceTest {

  // ==========================================================================
  // Print Title
  // ==========================================================================

  @Test
  public void printTitleShouldPrintGivenTitle() {
    var in = new ByteArrayInputStream("".getBytes(UTF_8));
    var out = new ByteArrayOutputStream();

    var ui = new CommandLineInterface(in, out);
    var title = "CHESS END GAMES v. 0.1";
    ui.showTitle(title);

    assertEquals(title + "\n\n", out.toString(UTF_8));
  }

  // ==========================================================================
  // Print Result
  // ==========================================================================

  @Test
  public void printResultShouldPrintGivenResult() {
    var in = new ByteArrayInputStream("".getBytes(UTF_8));
    var out = new ByteArrayOutputStream();

    var ui = new CommandLineInterface(in, out);
    var result = "Mate. BLACK wins.";
    ui.showResult(result);

    assertEquals(result + "\n", out.toString(UTF_8));
  }


  // ==========================================================================
  // Print Result
  // ==========================================================================

  @Test
  public void printSituationShouldPrintGivenSituation() {
    var in = new ByteArrayInputStream("".getBytes(UTF_8));
    var out = new ByteArrayOutputStream();

    var ui = new CommandLineInterface(in, out);
    var situation = new Situation(
      WHITE,
      Board.builder()
        .add(WHITE_KING, C3)
        .add(BLACK_KING, C5)
        .add(WHITE_ROOK, A1)
        .build()
    );
    ui.showSituation(situation);

    assertEquals(situation.print() + "\n", out.toString(UTF_8));
  }

  // ==========================================================================
  // Query Move
  // ==========================================================================

  @Test
  public void queryMoveShouldRequestValidMove() {
    var in = new ByteArrayInputStream("B1 B2".getBytes(UTF_8));
    var out = new ByteArrayOutputStream();

    var ui = new CommandLineInterface(in, out);
    var result = ui.queryMove(
      new Situation(
        WHITE,
        Board.builder()
          .add(WHITE_KING, B1)
          .add(WHITE_ROOK, A1)
          .add(BLACK_KING, D1)
          .build()
      )
    );

    assertEquals(new Move(B1, B2), result);
    assertEquals(
      "Enter WHITE move:\n\n",
      out.toString(UTF_8)
    );
  }

  @Test
  public void queryMoveShouldRequestAgainWhenGivenInvalidMove() {
    var in = new ByteArrayInputStream("B1 A1\nB1 B2\n".getBytes(UTF_8));
    var out = new ByteArrayOutputStream();

    var ui = new CommandLineInterface(in, out);
    var result = ui.queryMove(
      new Situation(
        WHITE,
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
      out.toString(UTF_8)
    );
  }

  // ==========================================================================
  // Query Initial Situation
  // ==========================================================================

  @Test
  public void queryInitialSituationShouldQueryInitialPositionsOfAllPieces() {
    var in = new ByteArrayInputStream("""
      A1
      A2
      C3
      """.getBytes(UTF_8)
    );
    var out = new ByteArrayOutputStream();

    var ui = new CommandLineInterface(in, out);
    var result = ui.queryInitialSituation(List.of(WHITE_KING, WHITE_ROOK, BLACK_KING));

    assertEquals(
      Board.builder()
        .add(WHITE_KING, A1)
        .add(WHITE_ROOK, A2)
        .add(BLACK_KING, C3)
        .build(),
      result
    );
    assertEquals(
      """
      Enter positions of all pieces on the board.
      WHITE KING:
      WHITE ROOK:
      BLACK KING:
      
      """,
      out.toString(UTF_8)
    );
  }
}
