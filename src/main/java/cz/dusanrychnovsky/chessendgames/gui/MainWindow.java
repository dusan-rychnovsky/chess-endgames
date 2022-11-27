package cz.dusanrychnovsky.chessendgames.gui;

import cz.dusanrychnovsky.chessendgames.*;

import javax.swing.*;

import java.awt.*;

import static cz.dusanrychnovsky.chessendgames.gui.SwingExtensions.runOnUiThread;
import static java.awt.BorderLayout.*;
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
    window.setUp();
    return window;
  }

  public void setUp() {
    runOnUiThread(() -> {
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
    });
  }

  public void showTitle(String title) {
    runOnUiThread(() -> {
      window.setTitle(title);
      window.setVisible(true);
    });
  }

  public Position queryPosition() {
    var result = chessBoardPanel.queryPosition();
    chessBoardPanel.addDarkBorder(result);
    return result;
  }

  public void showSituation(Board board) {
    chessBoardPanel.showSituation(board);
  }

  public void setStatus(String status) {
    statusPanel.setStatusMessage(status);
  }

  public void showResult(String result) {
    gameHistoryPanel.showResult(result);
  }
}
