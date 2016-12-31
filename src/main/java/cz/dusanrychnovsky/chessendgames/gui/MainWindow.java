package cz.dusanrychnovsky.chessendgames.gui;

import cz.dusanrychnovsky.chessendgames.core.*;
import cz.dusanrychnovsky.chessendgames.core.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.SOUTH;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class MainWindow {
  
  private JFrame window;
  
  private ChessBoard chessBoard;
  private StatusBar statusBar;
  private HistoryBar historyBar;

  public void setUp() {
    window = new JFrame();
    window.setTitle("Chess Endgames v 0.1");
    window.setDefaultCloseOperation(EXIT_ON_CLOSE);
    window.setResizable(false);
    window.setLayout(new BorderLayout());
    
    chessBoard = new ChessBoard();
    chessBoard.addMouseListener(chessBoard);
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

  public void addBorderAroundPosition(Position pos) {
    chessBoard.addBorder(pos);
  }

  public void clearBorders() {
    chessBoard.clearBorders();
  }
}
