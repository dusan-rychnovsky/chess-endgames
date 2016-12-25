package cz.dusanrychnovsky.chessendgames.gui;

import cz.dusanrychnovsky.chessendgames.UserInterface;
import cz.dusanrychnovsky.chessendgames.core.Color;
import cz.dusanrychnovsky.chessendgames.core.Move;
import cz.dusanrychnovsky.chessendgames.core.Position;
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
  public void displayMessage(String message) {
    runOnUiThread(() -> {
      mainWindow.showMessage(message);
    });
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
  
    displayMessage(color + " Enter move: ");
    Move move = new Move(requestPosition(), requestPosition());
  
    while (move == null || !situation.isValidMove(move)) {
      displayMessage("Invalid move! " + color + " Enter move: ");
      move = new Move(requestPosition(), requestPosition());
    }
  
    return move;
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
