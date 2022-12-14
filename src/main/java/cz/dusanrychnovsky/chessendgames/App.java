package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.database.DbPlayer;
import cz.dusanrychnovsky.chessendgames.cli.CommandLineInterface;
import cz.dusanrychnovsky.chessendgames.core.*;
import cz.dusanrychnovsky.chessendgames.gui.SwingInterface;

import static cz.dusanrychnovsky.chessendgames.core.Color.*;
import static cz.dusanrychnovsky.chessendgames.core.Piece.*;
import static cz.dusanrychnovsky.chessendgames.core.Status.*;
import static java.util.Arrays.asList;

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
      return new CommandLineInterface(System.in, System.out);
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
      ui.showMove(color, move);

      situation = situation.apply(move);
      ui.showSituation(situation);

      status = situation.status();
    }

    return status;
  }
}
