package cz.dusanrychnovsky.chessendgames.gui;

import cz.dusanrychnovsky.chessendgames.core.Color;
import cz.dusanrychnovsky.chessendgames.core.Move;
import cz.dusanrychnovsky.chessendgames.core.Result;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.Font.BOLD;

public class HistoryBar extends JPanel {

  private static final int BAR_WIDTH = 150;
  private static final int BAR_HEIGHT = 800;

  private final JLabel header;
  private final JTextArea body;

  public HistoryBar() {
    setPreferredSize(new Dimension(BAR_WIDTH, BAR_HEIGHT));
    setBackground(SystemColor.control);
    setLayout(new BorderLayout());

    header = new JLabel();
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
    body.setText(body.getText() + color + " " + move + "\n\n");
  }

  public void showResult(Result result) {
    body.setText(body.getText() + result);
  }
}
