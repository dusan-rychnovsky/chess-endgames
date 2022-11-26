package cz.dusanrychnovsky.chessendgames.gui;

import cz.dusanrychnovsky.chessendgames.*;

import javax.swing.*;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.SOUTH;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainWindow {

  private JFrame window;

  private ChessBoardPanel chessBoardPanel;

  private StatusPanel statusPanel;

  private GameHistoryPanel gameHistoryPanel;

  public static void main(String[] args) {
    get();
  }

  public static MainWindow get() {
    var window = new MainWindow();
    runOnUiThread(() -> {
      window.setUp();
    });
    return window;
  }

  private static void runOnUiThread(Runnable action) {
    try {
      SwingUtilities.invokeAndWait(action);
    }
    catch (InterruptedException | InvocationTargetException ex) {
      throw new RuntimeException(ex);
    }
  }

  public void setUp() {
    window = new JFrame();
    window.setDefaultCloseOperation(EXIT_ON_CLOSE);
    window.setResizable(false);
    window.setLayout(new BorderLayout());

    chessBoardPanel = ChessBoardPanel.setUp();
    window.add(chessBoardPanel, CENTER);

    statusPanel = new StatusPanel(chessBoardPanel.getWidth());
    window.add(statusPanel, SOUTH);

    gameHistoryPanel = new GameHistoryPanel(chessBoardPanel.getHeight() + statusPanel.getHeight());
    window.add(gameHistoryPanel, EAST);

    window.pack();
    window.setLocationRelativeTo(null);
  }

  public void showTitle(String title) {
    runOnUiThread(() -> {
      window.setTitle(title);
      window.setVisible(true);
    });
  }

  public void showResult(String result) {
    gameHistoryPanel.showResult(result);
  }

  public Position queryPosition(Piece piece) {
    statusPanel.setStatusMessage("Place " + piece.print() + ".");
    return chessBoardPanel.queryPosition();
  }

  public void showSituation(Board board) {
    chessBoardPanel.showSituation(board);
  }

  public Move queryMove(Situation situation) {
    statusPanel.setStatusMessage("Enter " + situation.color() + " move.");

    var fromPos = chessBoardPanel.queryPosition();
    chessBoardPanel.addDarkBorder(fromPos);

    var toPos = chessBoardPanel.queryPosition();
    chessBoardPanel.addDarkBorder(toPos);

    return new Move(fromPos, toPos);
  }
}
