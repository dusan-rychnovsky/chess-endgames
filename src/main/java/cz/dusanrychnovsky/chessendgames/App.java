package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.database.DbPlayer;
import cz.dusanrychnovsky.chessendgames.gui.SwingInterface;

import static cz.dusanrychnovsky.chessendgames.Color.*;
import static cz.dusanrychnovsky.chessendgames.Piece.*;
import static cz.dusanrychnovsky.chessendgames.Status.*;
import static java.util.Arrays.asList;

import java.io.*;
import java.util.List;
import java.util.Map;

public class App {

  private static final List<Piece> PIECES = List.of(WHITE_KING, WHITE_ROOK, BLACK_KING);

  public static void main(String[] args) {
    var ui = selectUserInterface(args);
    var players = Map.of(
      WHITE, new DbPlayer("/movesdb"),
      BLACK, new UIPlayer(ui)
    );
    run(ui, players, WHITE);
  }

  private static UserInterface selectUserInterface(String[] args) {
    if (asList(args).contains("-CLI")) {
      return new CommandLineInterface(
        new BufferedReader(new InputStreamReader(System.in)),
        new BufferedWriter(new OutputStreamWriter(System.out))
      );
    }
    return new SwingInterface();
  }

  public static void run(UserInterface ui, Map<Color, Player> players, Color color) {
    ui.showTitle("CHESS END GAMES v. 0.1");
    var situation = new Situation(color, ui.queryInitialSituation(PIECES));
    var result = gameLoop(situation, ui, players);
    ui.showResult(result.print());
  }

  private static Status gameLoop(Situation situation, UserInterface ui, Map<Color, Player> players) {

    ui.showSituation(situation);

    var status = situation.status();
    while (status.equals(IN_PROGRESS)) {
      var color = situation.color();

      var move = players.get(color).getMove(situation);
      situation = situation.apply(move);
      status = situation.status();

      ui.showSituation(situation);
    }

    return status;
  }
}
