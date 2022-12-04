package cz.dusanrychnovsky.chessendgames;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.Map;

import static cz.dusanrychnovsky.chessendgames.Color.*;
import static java.nio.charset.StandardCharsets.UTF_8;

public class AppTest {

  @Test
  public void runShouldExecuteScenarioWithConsoleInOut() {
    var in = new ByteArrayInputStream(
      """
        f5
        e6
        d3
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
        """.getBytes(UTF_8)
    );

    var out = new ByteArrayOutputStream();
    var ui = new CommandLineInterface(in, out);
    var player = new UIPlayer(ui);
    var players = Map.<Color, Player>of(WHITE, player, BLACK, player);

    App.run(ui, players, WHITE);

    Assert.assertEquals(
      """
        CHESS END GAMES v. 0.1

        Enter positions of all pieces on the board.
        WHITE KING:
        WHITE ROOK:
        BLACK KING:
                
        WHITE
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
          
        Enter WHITE move:
                
        WHITE move: E6 E4
        
        BLACK
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
          
        Enter BLACK move:
        
        BLACK move: D3 C3
        
        WHITE
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
          
        Enter WHITE move:
        
        WHITE move: F5 E5
        
        BLACK
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
          
        Enter BLACK move:
        
        BLACK move: C3 D3
                
        WHITE
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
          
        Enter WHITE move:
        
        WHITE move: E5 D5
                
        BLACK
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
          
        Enter BLACK move:
        
        BLACK move: D3 D2
                
        WHITE
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
          
        Enter WHITE move:
        
        WHITE move: D5 D4
                
        BLACK
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
          
        Enter BLACK move:
        
        BLACK move: D2 C2
                
        WHITE
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
          
        Enter WHITE move:
        
        WHITE move: E4 E3
                
        BLACK
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
          
        Enter BLACK move:
        
        BLACK move: C2 C1
                
        WHITE
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
          
        Enter WHITE move:
        
        WHITE move: E3 E2
                
        BLACK
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
          
        Enter BLACK move:
        
        BLACK move: C1 D1
                
        WHITE
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
          
        Enter WHITE move:
        
        WHITE move: D4 D3
                
        BLACK
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
          
        Enter BLACK move:
        
        BLACK move: D1 C1
                
        WHITE
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
          
        Enter WHITE move:
        
        WHITE move: E2 D2
                
        BLACK
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
          
        Enter BLACK move:
        
        BLACK move: C1 B1
                
        WHITE
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
          
        Enter WHITE move:
        
        WHITE move: D2 C2
                
        BLACK
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
          
        Enter BLACK move:
        
        BLACK move: B1 A1
                
        WHITE
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
          
        Enter WHITE move:
        
        WHITE move: D3 C3
                
        BLACK
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
          
        Enter BLACK move:
        
        BLACK move: A1 B1
                
        WHITE
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
          
        Enter WHITE move:
        
        WHITE move: C3 B3
                
        BLACK
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
          
        Enter BLACK move:
        
        BLACK move: B1 A1
                
        WHITE
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
          
        Enter WHITE move:
        
        WHITE move: C2 C1
                
        BLACK
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
          
        Mate. WHITE wins.
        """,
      out.toString(UTF_8)
    );
  }

  @Test
  public void runShouldHandleInvalidMoves() {
    var in = new ByteArrayInputStream(
      """
        b3
        c3
        a1
        c3 b3
        c3 c1
        """.getBytes(UTF_8)
    );

    var out = new ByteArrayOutputStream();
    var ui = new CommandLineInterface(in, out);
    var player = new UIPlayer(ui);
    var players = Map.<Color, Player>of(WHITE, player, BLACK, player);

    App.run(ui, players, WHITE);

    Assert.assertEquals(
      """
        CHESS END GAMES v. 0.1

        Enter positions of all pieces on the board.
        WHITE KING:
        WHITE ROOK:
        BLACK KING:
        
        WHITE
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
          
        Enter WHITE move:
        Move is not valid.
        Enter WHITE move:
        
        WHITE move: C3 C1
        
        BLACK
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
          
        Mate. WHITE wins.
        """,
      out.toString(UTF_8)
    );
  }
}
