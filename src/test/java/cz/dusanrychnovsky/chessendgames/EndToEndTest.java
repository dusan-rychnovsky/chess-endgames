package cz.dusanrychnovsky.chessendgames;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;

import static cz.dusanrychnovsky.chessendgames.Color.BLACK;
import static cz.dusanrychnovsky.chessendgames.Color.WHITE;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.joining;

public class EndToEndTest {

  @Test
  public void runShouldExecuteScenarioWithConsoleInOut() {
    var scenario = new Scenario("""
      O: CHESS END GAMES v. 0.1
      O:
      O: Enter positions of all pieces on the board.
      O: WHITE KING:
      I: f5
      O: WHITE ROOK:
      I: e6
      O: BLACK KING:
      I: d3
      O:
      O: WHITE
      O: 8 | . . . . . . . .
      O: 7 | . . . . . . . .
      O: 6 | . . . . R . . .
      O: 5 | . . . . . K . .
      O: 4 | . . . . . . . .
      O: 3 | . . . K . . . .
      O: 2 | . . . . . . . .
      O: 1 | . . . . . . . .
      O: --|----------------
      O:   | A B C D E F G H
      O:
      O: Enter WHITE move:
      I: e6 e4
      O:
      O: WHITE move: E6 E4
      O:
      O: BLACK
      O: 8 | . . . . . . . .
      O: 7 | . . . . . . . .
      O: 6 | . . . . . . . .
      O: 5 | . . . . . K . .
      O: 4 | . . . . R . . .
      O: 3 | . . . K . . . .
      O: 2 | . . . . . . . .
      O: 1 | . . . . . . . .
      O: --|----------------
      O:   | A B C D E F G H
      O:
      O: Enter BLACK move:
      I: d3 c3
      O:
      O: BLACK move: D3 C3
      O:
      O: WHITE
      O: 8 | . . . . . . . .
      O: 7 | . . . . . . . .
      O: 6 | . . . . . . . .
      O: 5 | . . . . . K . .
      O: 4 | . . . . R . . .
      O: 3 | . . K . . . . .
      O: 2 | . . . . . . . .
      O: 1 | . . . . . . . .
      O: --|----------------
      O:   | A B C D E F G H
      O:
      O: Enter WHITE move:
      I: f5 e5
      O:
      O: WHITE move: F5 E5
      O:
      O: BLACK
      O: 8 | . . . . . . . .
      O: 7 | . . . . . . . .
      O: 6 | . . . . . . . .
      O: 5 | . . . . K . . .
      O: 4 | . . . . R . . .
      O: 3 | . . K . . . . .
      O: 2 | . . . . . . . .
      O: 1 | . . . . . . . .
      O: --|----------------
      O:   | A B C D E F G H
      O:
      O: Enter BLACK move:
      I: c3 d3
      O:
      O: BLACK move: C3 D3
      O:
      O: WHITE
      O: 8 | . . . . . . . .
      O: 7 | . . . . . . . .
      O: 6 | . . . . . . . .
      O: 5 | . . . . K . . .
      O: 4 | . . . . R . . .
      O: 3 | . . . K . . . .
      O: 2 | . . . . . . . .
      O: 1 | . . . . . . . .
      O: --|----------------
      O:   | A B C D E F G H
      O:
      O: Enter WHITE move:
      I: e5 d5
      O:
      O: WHITE move: E5 D5
      O:
      O: BLACK
      O: 8 | . . . . . . . .
      O: 7 | . . . . . . . .
      O: 6 | . . . . . . . .
      O: 5 | . . . K . . . .
      O: 4 | . . . . R . . .
      O: 3 | . . . K . . . .
      O: 2 | . . . . . . . .
      O: 1 | . . . . . . . .
      O: --|----------------
      O:   | A B C D E F G H
      O:
      O: Enter BLACK move:
      I: d3 d2
      O:
      O: BLACK move: D3 D2
      O:
      O: WHITE
      O: 8 | . . . . . . . .
      O: 7 | . . . . . . . .
      O: 6 | . . . . . . . .
      O: 5 | . . . K . . . .
      O: 4 | . . . . R . . .
      O: 3 | . . . . . . . .
      O: 2 | . . . K . . . .
      O: 1 | . . . . . . . .
      O: --|----------------
      O:   | A B C D E F G H
      O:
      O: Enter WHITE move:
      I: d5 d4
      O:
      O: WHITE move: D5 D4
      O:
      O: BLACK
      O: 8 | . . . . . . . .
      O: 7 | . . . . . . . .
      O: 6 | . . . . . . . .
      O: 5 | . . . . . . . .
      O: 4 | . . . K R . . .
      O: 3 | . . . . . . . .
      O: 2 | . . . K . . . .
      O: 1 | . . . . . . . .
      O: --|----------------
      O:   | A B C D E F G H
      O:
      O: Enter BLACK move:
      I: d2 c2
      O:
      O: BLACK move: D2 C2
      O:
      O: WHITE
      O: 8 | . . . . . . . .
      O: 7 | . . . . . . . .
      O: 6 | . . . . . . . .
      O: 5 | . . . . . . . .
      O: 4 | . . . K R . . .
      O: 3 | . . . . . . . .
      O: 2 | . . K . . . . .
      O: 1 | . . . . . . . .
      O: --|----------------
      O:   | A B C D E F G H
      O:
      O: Enter WHITE move:
      I: e4 e3
      O:
      O: WHITE move: E4 E3
      O:
      O: BLACK
      O: 8 | . . . . . . . .
      O: 7 | . . . . . . . .
      O: 6 | . . . . . . . .
      O: 5 | . . . . . . . .
      O: 4 | . . . K . . . .
      O: 3 | . . . . R . . .
      O: 2 | . . K . . . . .
      O: 1 | . . . . . . . .
      O: --|----------------
      O:   | A B C D E F G H
      O:
      O: Enter BLACK move:
      I: c2 c1
      O:
      O: BLACK move: C2 C1
      O:
      O: WHITE
      O: 8 | . . . . . . . .
      O: 7 | . . . . . . . .
      O: 6 | . . . . . . . .
      O: 5 | . . . . . . . .
      O: 4 | . . . K . . . .
      O: 3 | . . . . R . . .
      O: 2 | . . . . . . . .
      O: 1 | . . K . . . . .
      O: --|----------------
      O:   | A B C D E F G H
      O:
      O: Enter WHITE move:
      I: e3 e2
      O:
      O: WHITE move: E3 E2
      O:
      O: BLACK
      O: 8 | . . . . . . . .
      O: 7 | . . . . . . . .
      O: 6 | . . . . . . . .
      O: 5 | . . . . . . . .
      O: 4 | . . . K . . . .
      O: 3 | . . . . . . . .
      O: 2 | . . . . R . . .
      O: 1 | . . K . . . . .
      O: --|----------------
      O:   | A B C D E F G H
      O:
      O: Enter BLACK move:
      I: c1 d1
      O:
      O: BLACK move: C1 D1
      O:
      O: WHITE
      O: 8 | . . . . . . . .
      O: 7 | . . . . . . . .
      O: 6 | . . . . . . . .
      O: 5 | . . . . . . . .
      O: 4 | . . . K . . . .
      O: 3 | . . . . . . . .
      O: 2 | . . . . R . . .
      O: 1 | . . . K . . . .
      O: --|----------------
      O:   | A B C D E F G H
      O:
      O: Enter WHITE move:
      I: d4 d3
      O:
      O: WHITE move: D4 D3
      O:
      O: BLACK
      O: 8 | . . . . . . . .
      O: 7 | . . . . . . . .
      O: 6 | . . . . . . . .
      O: 5 | . . . . . . . .
      O: 4 | . . . . . . . .
      O: 3 | . . . K . . . .
      O: 2 | . . . . R . . .
      O: 1 | . . . K . . . .
      O: --|----------------
      O:   | A B C D E F G H
      O:
      O: Enter BLACK move:
      I: d1 c1
      O:
      O: BLACK move: D1 C1
      O:
      O: WHITE
      O: 8 | . . . . . . . .
      O: 7 | . . . . . . . .
      O: 6 | . . . . . . . .
      O: 5 | . . . . . . . .
      O: 4 | . . . . . . . .
      O: 3 | . . . K . . . .
      O: 2 | . . . . R . . .
      O: 1 | . . K . . . . .
      O: --|----------------
      O:   | A B C D E F G H
      O:
      O: Enter WHITE move:
      I: e2 d2
      O:
      O: WHITE move: E2 D2
      O:
      O: BLACK
      O: 8 | . . . . . . . .
      O: 7 | . . . . . . . .
      O: 6 | . . . . . . . .
      O: 5 | . . . . . . . .
      O: 4 | . . . . . . . .
      O: 3 | . . . K . . . .
      O: 2 | . . . R . . . .
      O: 1 | . . K . . . . .
      O: --|----------------
      O:   | A B C D E F G H
      O:
      O: Enter BLACK move:
      I: c1 b1
      O:
      O: BLACK move: C1 B1
      O:
      O: WHITE
      O: 8 | . . . . . . . .
      O: 7 | . . . . . . . .
      O: 6 | . . . . . . . .
      O: 5 | . . . . . . . .
      O: 4 | . . . . . . . .
      O: 3 | . . . K . . . .
      O: 2 | . . . R . . . .
      O: 1 | . K . . . . . .
      O: --|----------------
      O:   | A B C D E F G H
      O:
      O: Enter WHITE move:
      I: d2 c2
      O:
      O: WHITE move: D2 C2
      O:
      O: BLACK
      O: 8 | . . . . . . . .
      O: 7 | . . . . . . . .
      O: 6 | . . . . . . . .
      O: 5 | . . . . . . . .
      O: 4 | . . . . . . . .
      O: 3 | . . . K . . . .
      O: 2 | . . R . . . . .
      O: 1 | . K . . . . . .
      O: --|----------------
      O:   | A B C D E F G H
      O:
      O: Enter BLACK move:
      I: b1 a1
      O:
      O: BLACK move: B1 A1
      O:
      O: WHITE
      O: 8 | . . . . . . . .
      O: 7 | . . . . . . . .
      O: 6 | . . . . . . . .
      O: 5 | . . . . . . . .
      O: 4 | . . . . . . . .
      O: 3 | . . . K . . . .
      O: 2 | . . R . . . . .
      O: 1 | K . . . . . . .
      O: --|----------------
      O:   | A B C D E F G H
      O:
      O: Enter WHITE move:
      I: d3 c3
      O:
      O: WHITE move: D3 C3
      O:
      O: BLACK
      O: 8 | . . . . . . . .
      O: 7 | . . . . . . . .
      O: 6 | . . . . . . . .
      O: 5 | . . . . . . . .
      O: 4 | . . . . . . . .
      O: 3 | . . K . . . . .
      O: 2 | . . R . . . . .
      O: 1 | K . . . . . . .
      O: --|----------------
      O:   | A B C D E F G H
      O:
      O: Enter BLACK move:
      I: a1 b1
      O:
      O: BLACK move: A1 B1
      O:
      O: WHITE
      O: 8 | . . . . . . . .
      O: 7 | . . . . . . . .
      O: 6 | . . . . . . . .
      O: 5 | . . . . . . . .
      O: 4 | . . . . . . . .
      O: 3 | . . K . . . . .
      O: 2 | . . R . . . . .
      O: 1 | . K . . . . . .
      O: --|----------------
      O:   | A B C D E F G H
      O:
      O: Enter WHITE move:
      I: c3 b3
      O:
      O: WHITE move: C3 B3
      O:
      O: BLACK
      O: 8 | . . . . . . . .
      O: 7 | . . . . . . . .
      O: 6 | . . . . . . . .
      O: 5 | . . . . . . . .
      O: 4 | . . . . . . . .
      O: 3 | . K . . . . . .
      O: 2 | . . R . . . . .
      O: 1 | . K . . . . . .
      O: --|----------------
      O:   | A B C D E F G H
      O:
      O: Enter BLACK move:
      I: b1 a1
      O:
      O: BLACK move: B1 A1
      O:
      O: WHITE
      O: 8 | . . . . . . . .
      O: 7 | . . . . . . . .
      O: 6 | . . . . . . . .
      O: 5 | . . . . . . . .
      O: 4 | . . . . . . . .
      O: 3 | . K . . . . . .
      O: 2 | . . R . . . . .
      O: 1 | K . . . . . . .
      O: --|----------------
      O:   | A B C D E F G H
      O:
      O: Enter WHITE move:
      I: c2 c1
      O:
      O: WHITE move: C2 C1
      O:
      O: BLACK
      O: 8 | . . . . . . . .
      O: 7 | . . . . . . . .
      O: 6 | . . . . . . . .
      O: 5 | . . . . . . . .
      O: 4 | . . . . . . . .
      O: 3 | . K . . . . . .
      O: 2 | . . . . . . . .
      O: 1 | K . R . . . . .
      O: --|----------------
      O:   | A B C D E F G H
      O:
      O: Mate. WHITE wins.
      O:
      """
    );

    var out = new ByteArrayOutputStream();
    var ui = new CommandLineInterface(scenario.in(), out);
    var player = new UIPlayer(ui);

    var players = Map.<Color, Player>of(WHITE, player, BLACK, player);

    App.run(ui, players, WHITE);

    Assert.assertEquals(scenario.out(), out.toString(UTF_8));
  }

  @Test
  public void runShouldHandleInvalidMoves() {

    var scenario = new Scenario("""
      O: CHESS END GAMES v. 0.1
      O:
      O: Enter positions of all pieces on the board.
      O: WHITE KING:
      I: b3
      O: WHITE ROOK:
      I: c3
      O: BLACK KING:
      I: a1
      O:
      O: WHITE
      O: 8 | . . . . . . . .
      O: 7 | . . . . . . . .
      O: 6 | . . . . . . . .
      O: 5 | . . . . . . . .
      O: 4 | . . . . . . . .
      O: 3 | . K R . . . . .
      O: 2 | . . . . . . . .
      O: 1 | K . . . . . . .
      O: --|----------------
      O:   | A B C D E F G H
      O:
      O: Enter WHITE move:
      I: c3 b3
      O: Move is not valid.
      O: Enter WHITE move:
      I: c3 c1
      O:
      O: WHITE move: C3 C1
      O:
      O: BLACK
      O: 8 | . . . . . . . .
      O: 7 | . . . . . . . .
      O: 6 | . . . . . . . .
      O: 5 | . . . . . . . .
      O: 4 | . . . . . . . .
      O: 3 | . K . . . . . .
      O: 2 | . . . . . . . .
      O: 1 | K . R . . . . .
      O: --|----------------
      O:   | A B C D E F G H
      O:
      O: Mate. WHITE wins.
      O:
      """
    );

    var out = new ByteArrayOutputStream();
    var ui = new CommandLineInterface(scenario.in(), out);

    var player = new UIPlayer(ui);
    var players = Map.<Color, Player>of(WHITE, player, BLACK, player);

    App.run(ui, players, WHITE);

    Assert.assertEquals(scenario.out(), out.toString(UTF_8));
  }

  private static class Scenario {

    private final String inScript;
    private final String outScript;

    public Scenario(String script) {
      inScript = filterLines(script, "I:");
      outScript = filterLines(script, "O:");
    }

    private String filterLines(String text, String prefix) {
      return text.lines()
        .filter(line -> line.startsWith(prefix))
        .map(line -> line.replaceFirst("^" + prefix + " ?", ""))
        .collect(joining("\n"));
    }

    private InputStream in() {
      return new ByteArrayInputStream(inScript.getBytes(UTF_8));
    }

    private String out() {
      return outScript;
    }
  }
}
