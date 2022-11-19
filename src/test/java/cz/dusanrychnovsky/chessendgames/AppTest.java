package cz.dusanrychnovsky.chessendgames;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.Map;

import static cz.dusanrychnovsky.chessendgames.PlayerType.STDIN;

public class AppTest {

  @Test
  public void runShouldExecuteScenarioWithConsoleInOut() throws IOException {
    var in = new BufferedReader(new StringReader(
      """
        StdIn
        StdIn
        black
        white king d3
        black king f5
        black rook e6

        e6 e4
        d3 c3
        f5 e5
        c3 d3
        e5 d5
        d3 d2
        d5 d4
        d2 c2
        e4 e3
        c2 c1
        e3 e2
        c1 d1
        d4 d3
        d1 c1
        e2 d2
        c1 b1
        d2 c2
        b1 a1
        d3 c3
        a1 b1
        c3 b3
        b1 a1
        c2 c1
        """
    ));

    var writer = new StringWriter();
    var out = new BufferedWriter(writer);
    var playerDb = Map.<PlayerType, Player>of(STDIN, new StdInPlayer(in, out));

    App.run(in, out, playerDb);

    Assert.assertEquals(
      """
        CHESS END GAMES v. 0.1

        Choose players.
        [a] StdIn - command line interface
        [b] Db - computer algorithm

        White player:

        Black player:

        Enter current player's color (White/Black):

        Now enter positions of all pieces on the board.
        . One piece per line.
        . Format: "[Color] [Type] [Position]".
        . Example: "White King A1".
        . Finish with an empty line.

        Enter first piece:

        Enter next piece:

        Enter next piece:

        Enter next piece:
        BLACK
        8 | . . . . . . . .
        7 | . . . . . . . .
        6 | . . . . R . . .
        5 | . . . . . K . .
        4 | . . . . . . . .
        3 | . . . K . . . .
        2 | . . . . . . . .
        1 | . . . . . . . .
        --|----------------
          | A B C D E F G H
        Enter BLACK move:
        WHITE
        8 | . . . . . . . .
        7 | . . . . . . . .
        6 | . . . . . . . .
        5 | . . . . . K . .
        4 | . . . . R . . .
        3 | . . . K . . . .
        2 | . . . . . . . .
        1 | . . . . . . . .
        --|----------------
          | A B C D E F G H
        Enter WHITE move:
        BLACK
        8 | . . . . . . . .
        7 | . . . . . . . .
        6 | . . . . . . . .
        5 | . . . . . K . .
        4 | . . . . R . . .
        3 | . . K . . . . .
        2 | . . . . . . . .
        1 | . . . . . . . .
        --|----------------
          | A B C D E F G H
        Enter BLACK move:
        WHITE
        8 | . . . . . . . .
        7 | . . . . . . . .
        6 | . . . . . . . .
        5 | . . . . K . . .
        4 | . . . . R . . .
        3 | . . K . . . . .
        2 | . . . . . . . .
        1 | . . . . . . . .
        --|----------------
          | A B C D E F G H
        Enter WHITE move:
        BLACK
        8 | . . . . . . . .
        7 | . . . . . . . .
        6 | . . . . . . . .
        5 | . . . . K . . .
        4 | . . . . R . . .
        3 | . . . K . . . .
        2 | . . . . . . . .
        1 | . . . . . . . .
        --|----------------
          | A B C D E F G H
        Enter BLACK move:
        WHITE
        8 | . . . . . . . .
        7 | . . . . . . . .
        6 | . . . . . . . .
        5 | . . . K . . . .
        4 | . . . . R . . .
        3 | . . . K . . . .
        2 | . . . . . . . .
        1 | . . . . . . . .
        --|----------------
          | A B C D E F G H
        Enter WHITE move:
        BLACK
        8 | . . . . . . . .
        7 | . . . . . . . .
        6 | . . . . . . . .
        5 | . . . K . . . .
        4 | . . . . R . . .
        3 | . . . . . . . .
        2 | . . . K . . . .
        1 | . . . . . . . .
        --|----------------
          | A B C D E F G H
        Enter BLACK move:
        WHITE
        8 | . . . . . . . .
        7 | . . . . . . . .
        6 | . . . . . . . .
        5 | . . . . . . . .
        4 | . . . K R . . .
        3 | . . . . . . . .
        2 | . . . K . . . .
        1 | . . . . . . . .
        --|----------------
          | A B C D E F G H
        Enter WHITE move:
        BLACK
        8 | . . . . . . . .
        7 | . . . . . . . .
        6 | . . . . . . . .
        5 | . . . . . . . .
        4 | . . . K R . . .
        3 | . . . . . . . .
        2 | . . K . . . . .
        1 | . . . . . . . .
        --|----------------
          | A B C D E F G H
        Enter BLACK move:
        WHITE
        8 | . . . . . . . .
        7 | . . . . . . . .
        6 | . . . . . . . .
        5 | . . . . . . . .
        4 | . . . K . . . .
        3 | . . . . R . . .
        2 | . . K . . . . .
        1 | . . . . . . . .
        --|----------------
          | A B C D E F G H
        Enter WHITE move:
        BLACK
        8 | . . . . . . . .
        7 | . . . . . . . .
        6 | . . . . . . . .
        5 | . . . . . . . .
        4 | . . . K . . . .
        3 | . . . . R . . .
        2 | . . . . . . . .
        1 | . . K . . . . .
        --|----------------
          | A B C D E F G H
        Enter BLACK move:
        WHITE
        8 | . . . . . . . .
        7 | . . . . . . . .
        6 | . . . . . . . .
        5 | . . . . . . . .
        4 | . . . K . . . .
        3 | . . . . . . . .
        2 | . . . . R . . .
        1 | . . K . . . . .
        --|----------------
          | A B C D E F G H
        Enter WHITE move:
        BLACK
        8 | . . . . . . . .
        7 | . . . . . . . .
        6 | . . . . . . . .
        5 | . . . . . . . .
        4 | . . . K . . . .
        3 | . . . . . . . .
        2 | . . . . R . . .
        1 | . . . K . . . .
        --|----------------
          | A B C D E F G H
        Enter BLACK move:
        WHITE
        8 | . . . . . . . .
        7 | . . . . . . . .
        6 | . . . . . . . .
        5 | . . . . . . . .
        4 | . . . . . . . .
        3 | . . . K . . . .
        2 | . . . . R . . .
        1 | . . . K . . . .
        --|----------------
          | A B C D E F G H
        Enter WHITE move:
        BLACK
        8 | . . . . . . . .
        7 | . . . . . . . .
        6 | . . . . . . . .
        5 | . . . . . . . .
        4 | . . . . . . . .
        3 | . . . K . . . .
        2 | . . . . R . . .
        1 | . . K . . . . .
        --|----------------
          | A B C D E F G H
        Enter BLACK move:
        WHITE
        8 | . . . . . . . .
        7 | . . . . . . . .
        6 | . . . . . . . .
        5 | . . . . . . . .
        4 | . . . . . . . .
        3 | . . . K . . . .
        2 | . . . R . . . .
        1 | . . K . . . . .
        --|----------------
          | A B C D E F G H
        Enter WHITE move:
        BLACK
        8 | . . . . . . . .
        7 | . . . . . . . .
        6 | . . . . . . . .
        5 | . . . . . . . .
        4 | . . . . . . . .
        3 | . . . K . . . .
        2 | . . . R . . . .
        1 | . K . . . . . .
        --|----------------
          | A B C D E F G H
        Enter BLACK move:
        WHITE
        8 | . . . . . . . .
        7 | . . . . . . . .
        6 | . . . . . . . .
        5 | . . . . . . . .
        4 | . . . . . . . .
        3 | . . . K . . . .
        2 | . . R . . . . .
        1 | . K . . . . . .
        --|----------------
          | A B C D E F G H
        Enter WHITE move:
        BLACK
        8 | . . . . . . . .
        7 | . . . . . . . .
        6 | . . . . . . . .
        5 | . . . . . . . .
        4 | . . . . . . . .
        3 | . . . K . . . .
        2 | . . R . . . . .
        1 | K . . . . . . .
        --|----------------
          | A B C D E F G H
        Enter BLACK move:
        WHITE
        8 | . . . . . . . .
        7 | . . . . . . . .
        6 | . . . . . . . .
        5 | . . . . . . . .
        4 | . . . . . . . .
        3 | . . K . . . . .
        2 | . . R . . . . .
        1 | K . . . . . . .
        --|----------------
          | A B C D E F G H
        Enter WHITE move:
        BLACK
        8 | . . . . . . . .
        7 | . . . . . . . .
        6 | . . . . . . . .
        5 | . . . . . . . .
        4 | . . . . . . . .
        3 | . . K . . . . .
        2 | . . R . . . . .
        1 | . K . . . . . .
        --|----------------
          | A B C D E F G H
        Enter BLACK move:
        WHITE
        8 | . . . . . . . .
        7 | . . . . . . . .
        6 | . . . . . . . .
        5 | . . . . . . . .
        4 | . . . . . . . .
        3 | . K . . . . . .
        2 | . . R . . . . .
        1 | . K . . . . . .
        --|----------------
          | A B C D E F G H
        Enter WHITE move:
        BLACK
        8 | . . . . . . . .
        7 | . . . . . . . .
        6 | . . . . . . . .
        5 | . . . . . . . .
        4 | . . . . . . . .
        3 | . K . . . . . .
        2 | . . R . . . . .
        1 | K . . . . . . .
        --|----------------
          | A B C D E F G H
        Enter BLACK move:
        WHITE
        8 | . . . . . . . .
        7 | . . . . . . . .
        6 | . . . . . . . .
        5 | . . . . . . . .
        4 | . . . . . . . .
        3 | . K . . . . . .
        2 | . . . . . . . .
        1 | K . R . . . . .
        --|----------------
          | A B C D E F G H
        Mate. BLACK wins.
        """,
      writer.toString()
    );
  }

  @Test
  public void runShouldHandleInvalidMoves() throws IOException {
    var in = new BufferedReader(new StringReader(
      """
        StdIn
        StdIn
        black
        white king a1
        black king b3
        black rook c3

        c3 b3
        c3 c1
        """
    ));

    var writer = new StringWriter();
    var out = new BufferedWriter(writer);
    var playerDb = Map.<PlayerType, Player>of(STDIN, new StdInPlayer(in, out));

    App.run(in, out, playerDb);

    Assert.assertEquals(
      """
        CHESS END GAMES v. 0.1

        Choose players.
        [a] StdIn - command line interface
        [b] Db - computer algorithm

        White player:

        Black player:

        Enter current player's color (White/Black):

        Now enter positions of all pieces on the board.
        . One piece per line.
        . Format: "[Color] [Type] [Position]".
        . Example: "White King A1".
        . Finish with an empty line.

        Enter first piece:

        Enter next piece:

        Enter next piece:

        Enter next piece:
        BLACK
        8 | . . . . . . . .
        7 | . . . . . . . .
        6 | . . . . . . . .
        5 | . . . . . . . .
        4 | . . . . . . . .
        3 | . K R . . . . .
        2 | . . . . . . . .
        1 | K . . . . . . .
        --|----------------
          | A B C D E F G H
        Enter BLACK move:
        Move is not valid.
        Enter BLACK move:
        WHITE
        8 | . . . . . . . .
        7 | . . . . . . . .
        6 | . . . . . . . .
        5 | . . . . . . . .
        4 | . . . . . . . .
        3 | . K . . . . . .
        2 | . . . . . . . .
        1 | K . R . . . . .
        --|----------------
          | A B C D E F G H
        Mate. BLACK wins.
        """,
      writer.toString()
    );
  }
}
