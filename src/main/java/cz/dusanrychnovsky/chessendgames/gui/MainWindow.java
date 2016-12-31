package cz.dusanrychnovsky.chessendgames.gui;

import cz.dusanrychnovsky.chessendgames.core.*;
import cz.dusanrychnovsky.chessendgames.core.Color;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.SOUTH;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainWindow {

  private JFrame window;
  
  private ChessBoard chessBoard;
  private StatusBar statusBar;
  private HistoryBar historyBar;

  public void setUp() {
    window = new JFrame();
    window.setTitle("CHESS ENDGAMES");  // TODO: display version here
    window.setDefaultCloseOperation(EXIT_ON_CLOSE);
    window.setResizable(false);
    window.setLayout(new BorderLayout());
    
    chessBoard = new ChessBoard();
    window.add(chessBoard, CENTER);
    
    statusBar = new StatusBar();
    window.add(statusBar, SOUTH);

    historyBar = new HistoryBar();
    window.add(historyBar, EAST);

    window.pack();
    window.setLocationRelativeTo(null);
  }
  
  public void open() {
    window.setVisible(true);
  }
  
  public void showMessage(String message) {
    statusBar.setStatusMessage(message);
  }

  public void showMove(Color color, Move move) {
    historyBar.showMove(color, move);
  }

  public void showResult(Result result) {
    historyBar.showResult(result);
  }

  public void setSituation(Situation situation) {
    chessBoard.setSituation(situation);
  }
  
  public Position requestPosition() {
    return chessBoard.getPosition();
  }

  public void addDarkBorderAroundPosition(Position pos) {
    chessBoard.addDarkBorder(pos);
  }

  public void clearDarkBorders() {
    chessBoard.clearDarkBorders();
  }
}
