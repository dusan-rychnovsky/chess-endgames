package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import static cz.dusanrychnovsky.chessendgames.Color.WHITE;
import static cz.dusanrychnovsky.chessendgames.Piece.*;
import static cz.dusanrychnovsky.chessendgames.Position.*;
import static org.junit.Assert.assertEquals;

public class CommandLineInterfaceTest {

  // ==========================================================================
  // Print Title
  // ==========================================================================

  @Test
  public void printTitleShouldPrintGivenTitle() {
    var in = new BufferedReader(new StringReader(""));
    var writer = new StringWriter();
    var out = new BufferedWriter(writer);

    var ui = new CommandLineInterface(in, out);
    var title = "CHESS END GAMES v. 0.1";
    ui.printTitle(title);

    assertEquals(title + "\n\n", writer.toString());
  }

  // ==========================================================================
  // Print Result
  // ==========================================================================

  @Test
  public void printResultShouldPrintGivenResult() {
    var in = new BufferedReader(new StringReader(""));
    var writer = new StringWriter();
    var out = new BufferedWriter(writer);

    var ui = new CommandLineInterface(in, out);
    var result = "Mate. BLACK wins.";
    ui.printResult(result);

    assertEquals(result + "\n", writer.toString());
  }


  // ==========================================================================
  // Print Result
  // ==========================================================================

  @Test
  public void printSituationShouldPrintGivenSituation() {
    var in = new BufferedReader(new StringReader(""));
    var writer = new StringWriter();
    var out = new BufferedWriter(writer);

    var ui = new CommandLineInterface(in, out);
    var situation = new Situation(
      WHITE,
      Board.builder()
        .add(WHITE_KING, C3)
        .add(BLACK_KING, C5)
        .add(WHITE_ROOK, A1)
        .build()
    );
    ui.printSituation(situation);

    assertEquals(situation.print() + "\n", writer.toString());
  }

  // ==========================================================================
  // Query Move
  // ==========================================================================

  @Test
  public void queryMoveShouldRequestValidMove() {
    var in = new BufferedReader(new StringReader("B1 B2\n"));
    var writer = new StringWriter();
    var out = new BufferedWriter(writer);

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
      writer.toString()
    );
  }

  @Test
  public void queryMoveShouldRequestAgainWhenGivenInvalidMove() {
    var in = new BufferedReader(new StringReader("B1 A1\nB1 B2\n"));
    var writer = new StringWriter();
    var out = new BufferedWriter(writer);

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
      writer.toString()
    );
  }

  // ==========================================================================
  // Query Initial Situation
  // ==========================================================================

  @Test
  public void queryInitialSituationShouldQueryInitialPositionsOfAllPieces() {
    var in = new BufferedReader(new StringReader(
      """
      A1
      A2
      C3
      """));
    var writer = new StringWriter();
    var out = new BufferedWriter(writer);

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
      writer.toString()
    );
  }
}
