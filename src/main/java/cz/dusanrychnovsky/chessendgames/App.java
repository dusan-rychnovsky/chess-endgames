package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.database.DbPlayer;

import static cz.dusanrychnovsky.chessendgames.Color.*;
import static cz.dusanrychnovsky.chessendgames.Piece.*;
import static cz.dusanrychnovsky.chessendgames.Status.*;

import java.io.*;
import java.util.List;
import java.util.Map;

public class App {

  private static final List<Piece> PIECES = List.of(WHITE_KING, WHITE_ROOK, BLACK_KING);

  public static void main(String[] args) {
    var reader = new BufferedReader(new InputStreamReader(System.in));
    var writer = new BufferedWriter(new OutputStreamWriter(System.out));
    var ui = new CommandLineInterface(reader, writer);
    // var ui = new SwingInterface();
    var players = Map.of(
      WHITE, new DbPlayer("/movesdb"),
      BLACK, new UIPlayer(ui)
    );
    run(ui, players, WHITE);
  }

  public static void run(UserInterface ui, Map<Color, Player> players, Color color) {
    ui.printTitle("CHESS END GAMES v. 0.1");
    var situation = new Situation(color, ui.queryInitialSituation(PIECES));
    var result = gameLoop(situation, ui, players);
    ui.printResult(result.print());
  }

  private static Status gameLoop(Situation situation, UserInterface ui, Map<Color, Player> players) {

    ui.printSituation(situation);

    var status = situation.status();
    while (status.equals(IN_PROGRESS)) {
      var color = situation.color();

      var move = players.get(color).getMove(situation);
      situation = situation.apply(move);
      status = situation.status();

      ui.printSituation(situation);
    }

    return status;
  }
}
