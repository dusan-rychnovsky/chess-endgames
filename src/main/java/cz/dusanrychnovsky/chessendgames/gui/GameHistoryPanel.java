package cz.dusanrychnovsky.chessendgames.gui;

import cz.dusanrychnovsky.chessendgames.core.Move;
import cz.dusanrychnovsky.chessendgames.core.Color;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static cz.dusanrychnovsky.chessendgames.gui.SwingExtensions.runOnUiThread;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;

public class GameHistoryPanel {

  private static final int PANEL_WIDTH = 150;

  private final JPanel panel;

  private final JTextArea body;

  public GameHistoryPanel(int height) {
    panel = new JPanel();
    panel.setPreferredSize(new Dimension(PANEL_WIDTH, height));
    panel.setBackground(SystemColor.control);
    panel.setLayout(new BorderLayout());

    var header = new JLabel();
    header.setBorder(new EmptyBorder(20, 20, 0, 0));
    header.setFont(header.getFont().deriveFont(14f));
    header.setText("HISTORY:");
    panel.add(header, NORTH);

    body = new JTextArea();
    body.setBorder(new EmptyBorder(20, 20, 0, 0));
    body.setEditable(false);
    body.setBackground(SystemColor.control);
    body.setFont(body.getFont().deriveFont(14f));
    panel.add(body, CENTER);
  }

  public void attach(JFrame frame, Object constraints) {
    frame.add(panel, constraints);
  }

  public void showMove(Color color, Move move) {
    runOnUiThread(() ->
      body.setText(body.getText() + color + " " + move.print() + "\n")
    );
  }

  public void showResult(String result) {
    runOnUiThread(() ->
      body.setText(body.getText() + "\n" + result)
    );
  }
}
