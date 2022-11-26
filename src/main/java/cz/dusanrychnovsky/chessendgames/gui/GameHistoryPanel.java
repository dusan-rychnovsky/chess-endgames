package cz.dusanrychnovsky.chessendgames.gui;

import cz.dusanrychnovsky.chessendgames.Move;
import cz.dusanrychnovsky.chessendgames.Color;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;

public class GameHistoryPanel extends JPanel {

  private static final int PANEL_WIDTH = 150;

  private final JTextArea body;

  public GameHistoryPanel(int height) {
    setPreferredSize(new Dimension(PANEL_WIDTH, height));
    setBackground(SystemColor.control);
    setLayout(new BorderLayout());

    var header = new JLabel();
    header.setBorder(new EmptyBorder(20, 20, 10, 0));
    header.setFont(header.getFont().deriveFont(14f));
    header.setText("HISTORY:");
    add(header, NORTH);

    body = new JTextArea();
    body.setBorder(new EmptyBorder(20, 20, 0, 0));
    body.setEditable(false);
    body.setBackground(SystemColor.control);
    body.setFont(body.getFont().deriveFont(14f));
    add(body, CENTER);
  }

  public void showMove(Color color, Move move) {
    body.setText(body.getText() + color + " " + move.print() + "\n");
  }

  public void showResult(String result) {
    body.setText(body.getText() + result);
  }
}
