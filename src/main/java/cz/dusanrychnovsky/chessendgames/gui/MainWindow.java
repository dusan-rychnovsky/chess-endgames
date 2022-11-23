package cz.dusanrychnovsky.chessendgames.gui;

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
    var window = new MainWindow();
    runOnUiThread(() -> {
      window.setUp();
      window.open();
    });
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
    window.setTitle("CHESS ENDGAMES");
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

  public void open() {
    window.setVisible(true);
  }
}
