package cz.dusanrychnovsky.chessendgames.gui;

import cz.dusanrychnovsky.chessendgames.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SwingInterface implements UserInterface {

  private static final Logger LOGGER = LogManager.getLogger(SwingInterface.class);

  private final MainWindow mainWindow;

  public SwingInterface() {
    mainWindow = MainWindow.get();
  }

  @Override
  public void printTitle(String title) {
    mainWindow.showTitle(title);
  }

  @Override
  public Board queryInitialSituation(List<Piece> pieces) {
    LOGGER.info("Going to query initial situation with pieces: " + pieces);
    var builder = Board.builder();
    for (var piece : pieces) {
      LOGGER.debug("Going to query position for: " + piece);
      mainWindow.setStatus("Place " + piece.print() + ".");
      var pos = mainWindow.queryPosition();
      LOGGER.info("Putting " + piece + " at " + pos);
      builder.add(piece, pos);
      mainWindow.showSituation(builder.build());
    }
    var result = builder.build();
    mainWindow.clearDarkBorders();
    return result;
  }

  @Override
  public Move queryMove(Situation situation) {
    var color = situation.color();
    mainWindow.setStatus("Enter " + color + " move.");
    var move = getMove();
    while (!situation.isValid(move)) {
      mainWindow.clearDarkBorders();
      mainWindow.setStatus("Move " + move.print() + " isn't valid. Enter " + color + " move.");
      move = getMove();
    }
    return move;
  }

  private Move getMove() {
    var fromPos = mainWindow.queryPosition();
    var toPos = mainWindow.queryPosition();
    return new Move(fromPos, toPos);
  }

  @Override
  public void printSituation(Situation situation) {
    mainWindow.showSituation(situation.board());
  }

  @Override
  public void printResult(String result) {
    mainWindow.showResult(result);
  }
}