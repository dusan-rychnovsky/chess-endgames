package cz.dusanrychnovsky.chessendgames;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;

public class AppTest {
  @Test
  @Ignore
  public void run_executesScenarioWithConsoleInOut() throws IOException {
    var in = new BufferedReader(new StringReader(
      "black\n" +
        "white king d3\n" +
        "black king f5\n" +
        "black rook e6\n" +
        "\n" +
        "e6 e4\n" +
        "d3 c3\n" +
        "f5 e5\n" +
        "c3 d3\n" +
        "e5 d5\n" +
        "d3 d2\n" +
        "d5 d4\n" +
        "d2 c2\n" +
        "e4 e3\n" +
        "c2 c1\n" +
        "e3 e2\n" +
        "c1 d1\n" +
        "d4 d3\n" +
        "d1 c1\n" +
        "e2 d2\n" +
        "c1 b1\n" +
        "d2 c2\n" +
        "b1 a1\n" +
        "d3 c3\n" +
        "a1 b1\n" +
        "c3 b3\n" +
        "b1 a1\n" +
        "c2 c1\n"
    ));

    var writer = new StringWriter();
    var out = new BufferedWriter(writer);

    App.run(in, out);

    Assert.assertEquals(
      "Chess End Games v. 0.1\n" +
      "\n" +
      "Enter Initial Situation:\n" +
      "Black\n" +
      "8 | . . . . . . . .\n" +
      "7 | . . . . . . . .\n" +
      "6 | . . . . R . . .\n" +
      "5 | . . . . . K . .\n" +
      "4 | . . . . . . . .\n" +
      "3 | . . . K . . . .\n" +
      "2 | . . . . . . . .\n" +
      "1 | . . . . . . . .\n" +
      "--|----------------\n" +
      "  | A B C D E F G H\n" +
      "Enter Black move:\n" +
      "White\n" +
      "8 | . . . . . . . .\n" +
      "7 | . . . . . . . .\n" +
      "6 | . . . . . . . .\n" +
      "5 | . . . . . K . .\n" +
      "4 | . . . . R . . .\n" +
      "3 | . . . K . . . .\n" +
      "2 | . . . . . . . .\n" +
      "1 | . . . . . . . .\n" +
      "--|----------------\n" +
      "  | A B C D E F G H\n" +
      "Enter White move:\n" +
      "Black\n" +
      "8 | . . . . . . . .\n" +
      "7 | . . . . . . . .\n" +
      "6 | . . . . . . . .\n" +
      "5 | . . . . . K . .\n" +
      "4 | . . . . R . . .\n" +
      "3 | . . K . . . . .\n" +
      "2 | . . . . . . . .\n" +
      "1 | . . . . . . . .\n" +
      "--|----------------\n" +
      "  | A B C D E F G H\n" +
      "Enter Black move:\n" +
      "White\n" +
      "8 | . . . . . . . .\n" +
      "7 | . . . . . . . .\n" +
      "6 | . . . . . . . .\n" +
      "5 | . . . . K . . .\n" +
      "4 | . . . . R . . .\n" +
      "3 | . . K . . . . .\n" +
      "2 | . . . . . . . .\n" +
      "1 | . . . . . . . .\n" +
      "--|----------------\n" +
      "  | A B C D E F G H\n" +
      "Enter Black move:\n" +
      "White\n" +
      "8 | . . . . . . . .\n" +
      "7 | . . . . . . . .\n" +
      "6 | . . . . . . . .\n" +
      "5 | . . . . K . . .\n" +
      "4 | . . . . R . . .\n" +
      "3 | . . . K . . . .\n" +
      "2 | . . . . . . . .\n" +
      "1 | . . . . . . . .\n" +
      "--|----------------\n" +
      "  | A B C D E F G H\n" +
      "Enter White move:\n" +
      "Black\n" +
      "8 | . . . . . . . .\n" +
      "7 | . . . . . . . .\n" +
      "6 | . . . . . . . .\n" +
      "5 | . . . K . . . .\n" +
      "4 | . . . . R . . .\n" +
      "3 | . . . K . . . .\n" +
      "2 | . . . . . . . .\n" +
      "1 | . . . . . . . .\n" +
      "--|----------------\n" +
      "  | A B C D E F G H\n" +
      "Enter Black move:\n" +
      "White\n" +
      "8 | . . . . . . . .\n" +
      "7 | . . . . . . . .\n" +
      "6 | . . . . . . . .\n" +
      "5 | . . . K . . . .\n" +
      "4 | . . . . R . . .\n" +
      "3 | . . . . . . . .\n" +
      "2 | . . . K . . . .\n" +
      "1 | . . . . . . . .\n" +
      "--|----------------\n" +
      "  | A B C D E F G H\n" +
      "Enter White move:\n" +
      "Black\n" +
      "8 | . . . . . . . .\n" +
      "7 | . . . . . . . .\n" +
      "6 | . . . . . . . .\n" +
      "5 | . . . . . . . .\n" +
      "4 | . . . K R . . .\n" +
      "3 | . . . . . . . .\n" +
      "2 | . . . K . . . .\n" +
      "1 | . . . . . . . .\n" +
      "--|----------------\n" +
      "  | A B C D E F G H\n" +
      "Enter Black move:\n" +
      "White\n" +
      "8 | . . . . . . . .\n" +
      "7 | . . . . . . . .\n" +
      "6 | . . . . . . . .\n" +
      "5 | . . . . . . . .\n" +
      "4 | . . . K R . . .\n" +
      "3 | . . . . . . . .\n" +
      "2 | . . K . . . . .\n" +
      "1 | . . . . . . . .\n" +
      "--|----------------\n" +
      "  | A B C D E F G H\n" +
      "Enter White move:\n" +
      "Black\n" +
      "8 | . . . . . . . .\n" +
      "7 | . . . . . . . .\n" +
      "6 | . . . . . . . .\n" +
      "5 | . . . . . . . .\n" +
      "4 | . . . K . . . .\n" +
      "3 | . . . . R . . .\n" +
      "2 | . . . . . . . .\n" +
      "1 | . . K . . . . .\n" +
      "--|----------------\n" +
      "  | A B C D E F G H\n" +
      "Enter Black move:\n" +
      "White\n" +
      "8 | . . . . . . . .\n" +
      "7 | . . . . . . . .\n" +
      "6 | . . . . . . . .\n" +
      "5 | . . . . . . . .\n" +
      "4 | . . . K . . . .\n" +
      "3 | . . . . . . . .\n" +
      "2 | . . . . R . . .\n" +
      "1 | . . K . . . . .\n" +
      "--|----------------\n" +
      "  | A B C D E F G H\n" +
      "Enter White move:\n" +
      "Black\n" +
      "8 | . . . . . . . .\n" +
      "7 | . . . . . . . .\n" +
      "6 | . . . . . . . .\n" +
      "5 | . . . . . . . .\n" +
      "4 | . . . K . . . .\n" +
      "3 | . . . . . . . .\n" +
      "2 | . . . . R . . .\n" +
      "1 | . . . K . . . .\n" +
      "--|----------------\n" +
      "  | A B C D E F G H\n" +
      "Enter Black move:\n" +
      "White\n" +
      "8 | . . . . . . . .\n" +
      "7 | . . . . . . . .\n" +
      "6 | . . . . . . . .\n" +
      "5 | . . . . . . . .\n" +
      "4 | . . . . . . . .\n" +
      "3 | . . . K . . . .\n" +
      "2 | . . . . R . . .\n" +
      "1 | . . . K . . . .\n" +
      "--|----------------\n" +
      "  | A B C D E F G H\n" +
      "Enter White move:\n" +
      "Black\n" +
      "8 | . . . . . . . .\n" +
      "7 | . . . . . . . .\n" +
      "6 | . . . . . . . .\n" +
      "5 | . . . . . . . .\n" +
      "4 | . . . . . . . .\n" +
      "3 | . . . K . . . .\n" +
      "2 | . . . . R . . .\n" +
      "1 | . . K . . . . .\n" +
      "--|----------------\n" +
      "  | A B C D E F G H\n" +
      "Enter Black move:\n" +
      "White\n" +
      "8 | . . . . . . . .\n" +
      "7 | . . . . . . . .\n" +
      "6 | . . . . . . . .\n" +
      "5 | . . . . . . . .\n" +
      "4 | . . . . . . . .\n" +
      "3 | . . . K . . . .\n" +
      "2 | . . . R . . . .\n" +
      "1 | . . K . . . . .\n" +
      "--|----------------\n" +
      "  | A B C D E F G H\n" +
      "Enter White move:\n" +
      "Black\n" +
      "8 | . . . . . . . .\n" +
      "7 | . . . . . . . .\n" +
      "6 | . . . . . . . .\n" +
      "5 | . . . . . . . .\n" +
      "4 | . . . . . . . .\n" +
      "3 | . . . K . . . .\n" +
      "2 | . . . R . . . .\n" +
      "1 | . K . . . . . .\n" +
      "--|----------------\n" +
      "  | A B C D E F G H\n" +
      "Enter Black move:\n" +
      "White\n" +
      "8 | . . . . . . . .\n" +
      "7 | . . . . . . . .\n" +
      "6 | . . . . . . . .\n" +
      "5 | . . . . . . . .\n" +
      "4 | . . . . . . . .\n" +
      "3 | . . . K . . . .\n" +
      "2 | . . R . . . . .\n" +
      "1 | . K . . . . . .\n" +
      "--|----------------\n" +
      "  | A B C D E F G H\n" +
      "Enter White move:\n" +
      "Black\n" +
      "8 | . . . . . . . .\n" +
      "7 | . . . . . . . .\n" +
      "6 | . . . . . . . .\n" +
      "5 | . . . . . . . .\n" +
      "4 | . . . . . . . .\n" +
      "3 | . . . K . . . .\n" +
      "2 | . . R . . . . .\n" +
      "1 | K . . . . . . .\n" +
      "--|----------------\n" +
      "  | A B C D E F G H\n" +
      "Enter Black move:\n" +
      "White\n" +
      "8 | . . . . . . . .\n" +
      "7 | . . . . . . . .\n" +
      "6 | . . . . . . . .\n" +
      "5 | . . . . . . . .\n" +
      "4 | . . . . . . . .\n" +
      "3 | . . K . . . . .\n" +
      "2 | . . R . . . . .\n" +
      "1 | K . . . . . . .\n" +
      "--|----------------\n" +
      "  | A B C D E F G H\n" +
      "Enter White move:\n" +
      "Black\n" +
      "8 | . . . . . . . .\n" +
      "7 | . . . . . . . .\n" +
      "6 | . . . . . . . .\n" +
      "5 | . . . . . . . .\n" +
      "4 | . . . . . . . .\n" +
      "3 | . . K . . . . .\n" +
      "2 | . . R . . . . .\n" +
      "1 | . K . . . . . .\n" +
      "--|----------------\n" +
      "  | A B C D E F G H\n" +
      "Enter Black move:\n" +
      "White\n" +
      "8 | . . . . . . . .\n" +
      "7 | . . . . . . . .\n" +
      "6 | . . . . . . . .\n" +
      "5 | . . . . . . . .\n" +
      "4 | . . . . . . . .\n" +
      "3 | . K . . . . . .\n" +
      "2 | . . R . . . . .\n" +
      "1 | . K . . . . . .\n" +
      "--|----------------\n" +
      "  | A B C D E F G H\n" +
      "Enter White move:\n" +
      "Black\n" +
      "8 | . . . . . . . .\n" +
      "7 | . . . . . . . .\n" +
      "6 | . . . . . . . .\n" +
      "5 | . . . . . . . .\n" +
      "4 | . . . . . . . .\n" +
      "3 | . K . . . . . .\n" +
      "2 | . . R . . . . .\n" +
      "1 | K . . . . . . .\n" +
      "--|----------------\n" +
      "  | A B C D E F G H\n" +
      "Enter Black move:\n" +
      "White\n" +
      "8 | . . . . . . . .\n" +
      "7 | . . . . . . . .\n" +
      "6 | . . . . . . . .\n" +
      "5 | . . . . . . . .\n" +
      "4 | . . . . . . . .\n" +
      "3 | . K . . . . . .\n" +
      "2 | . . . . . . . .\n" +
      "1 | K . R . . . . .\n" +
      "--|----------------\n" +
      "  | A B C D E F G H\n" +
      "Checkmate. Black wins.",
      writer.toString()
    );
  }

  @Test
  public void run_executesScenarioWithConsoleInOut_handlesInvalidMoves() throws IOException {
    var in = new BufferedReader(new StringReader(
      "black\n" +
        "white king d3\n" +
        "black king f5\n" +
        "black rook e6\n" +
        "\n" +
        "e7 e4\n" +
        "e6 e4\n"
    ));

    var writer = new StringWriter();
    var out = new BufferedWriter(writer);

    App.run(in, out);

    Assert.assertEquals(
      "Chess End Games v. 0.1\n" +
        "\n" +
        "Enter Initial Situation:\n" +
        "Black\n" +
        "8 | . . . . . . . .\n" +
        "7 | . . . . . . . .\n" +
        "6 | . . . . R . . .\n" +
        "5 | . . . . . K . .\n" +
        "4 | . . . . . . . .\n" +
        "3 | . . . K . . . .\n" +
        "2 | . . . . . . . .\n" +
        "1 | . . . . . . . .\n" +
        "--|----------------\n" +
        "  | A B C D E F G H\n" +
        "Enter Black move:\n" +
        "Move is not valid.\n" +
        "Enter Black move:\n" +
        "White\n" +
        "8 | . . . . . . . .\n" +
        "7 | . . . . . . . .\n" +
        "6 | . . . . . . . .\n" +
        "5 | . . . . . K . .\n" +
        "4 | . . . . R . . .\n" +
        "3 | . . . K . . . .\n" +
        "2 | . . . . . . . .\n" +
        "1 | . . . . . . . .\n" +
        "--|----------------\n" +
        "  | A B C D E F G H\n",
      writer.toString()
    );
  }
}
