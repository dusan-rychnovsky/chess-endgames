package cz.dusanrychnovsky.chessendgames.gui;

import cz.dusanrychnovsky.chessendgames.UserInterface;
import cz.dusanrychnovsky.chessendgames.core.Color;
import cz.dusanrychnovsky.chessendgames.core.Move;
import cz.dusanrychnovsky.chessendgames.core.Position;
import cz.dusanrychnovsky.chessendgames.core.Result;
import cz.dusanrychnovsky.chessendgames.core.Situation;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class GraphicalUserInterface implements UserInterface {
  
  private final MainWindow mainWindow;
  
  public GraphicalUserInterface() {
    mainWindow = new MainWindow();
    runOnUiThread(() -> {
      mainWindow.setUp();
      mainWindow.open();
    });
  }

  @Override
  public void displayPrompt(String message) {
    runOnUiThread(() -> mainWindow.showMessage(message));
  }

  @Override
  public void displayChosenMove(Color color, Move move) {
    runOnUiThread(() -> mainWindow.showMove(color, move));
  }

  @Override
  public void displayResult(Result result) {
    runOnUiThread(() -> mainWindow.showResult(result));
  }

  @Override
  public void displaySituation(Situation situation) {
    runOnUiThread(() -> {
      mainWindow.setSituation(situation);
    });
  }
  
  @Override
  public Move requestMove(Situation situation) {
    Color color = situation.getCurrentColor();
  
    displayPrompt(buildPrompt(color));
    Move move = requestMove(color);

    while (!situation.isValidMove(move)) {
      displayPrompt(buildErrorPrompt(color, move));
      move = requestMove(color);
    }
  
    return move;
  }

  private Move requestMove(Color color) {
    dropAllBorders();

    Position fromPos = requestPosition();
    displayBorderAroundPosition(fromPos);
    displayPrompt(buildPrompt(color, fromPos));

    Position toPos = requestPosition();
    displayBorderAroundPosition(toPos);
    displayPrompt(buildPrompt(color, fromPos, toPos));

    return new Move(fromPos, toPos);
  }

  private void displayBorderAroundPosition(Position pos) {
    runOnUiThread(() -> mainWindow.addBorderAroundPosition(pos));
  }

  private void dropAllBorders() {
    runOnUiThread(() -> mainWindow.clearBorders());
  }

  private String buildErrorPrompt(Color color, Move invalidMove) {
    return color + " Invalid move: " + invalidMove + "! Enter move:";
  }

  private String buildPrompt(Color color) {
    return buildPrompt(color, null, null);
  }

  private String buildPrompt(Color color, Position from) {
    return buildPrompt(color, from, null);
  }

  private String buildPrompt(Color color, Position from, Position to) {

    StringBuilder builder = new StringBuilder();
    builder.append(color);
    builder.append(" Enter move:");

    if (from != null) {
      builder.append(" ");
      builder.append(from);
    }

    if (to != null) {
      builder.append(" ");
      builder.append(to);
    }

    return builder.toString();
  }

  private Position requestPosition() {
    return mainWindow.requestPosition();
  }
  
  private void runOnUiThread(Runnable action) {
    try {
      SwingUtilities.invokeAndWait(action::run);
    }
    catch (InterruptedException | InvocationTargetException ex) {
      throw new RuntimeException(ex);
    }
  }
}
