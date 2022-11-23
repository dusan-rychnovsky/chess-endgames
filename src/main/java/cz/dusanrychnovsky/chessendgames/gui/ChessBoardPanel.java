package cz.dusanrychnovsky.chessendgames.gui;

import cz.dusanrychnovsky.chessendgames.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

import static cz.dusanrychnovsky.chessendgames.Piece.*;

public class ChessBoardPanel extends JPanel {

  private final ImageIcon boardImg;
  private final ImageIcon lightBorderImg;
  private final ImageIcon darkBorderImg;
  private final Map<Piece, ImageIcon> pieceImgs;

  public ChessBoardPanel(ImageIcon boardImg, ImageIcon lightBorderImg, ImageIcon darkBorderImg, Map<Piece, ImageIcon> pieceImgs) {
    this.boardImg = boardImg;
    this.lightBorderImg = lightBorderImg;
    this.darkBorderImg = darkBorderImg;
    this.pieceImgs = pieceImgs;
  }

  public static ChessBoardPanel setUp() {
    var boardImg = loadImage("empty-board.png");
    var result = new ChessBoardPanel(
      boardImg,
      loadImage("light-border.png"),
      loadImage("dark-border.png"),
      Map.of(
        WHITE_KING, loadImage("white-king.png"),
        WHITE_ROOK, loadImage("white-rook.png"),
        BLACK_KING, loadImage("black-king.png")
      )
    );
    result.setPreferredSize(new Dimension(boardImg.getIconWidth(), boardImg.getIconHeight()));
    return result;
  }

  private static ImageIcon loadImage(String fileName) {
    return new ImageIcon(ChessBoardPanel.class.getResource("/img/" + fileName));
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);

    Graphics2D graphics2d = (Graphics2D) graphics;
    paintBoard(graphics2d);

    // TODO: remove when images are properly used
    lightBorderImg.getImage();
    darkBorderImg.getImage();
    pieceImgs.size();
  }

  private void paintBoard(Graphics2D graphics2d) {
    graphics2d.drawImage(boardImg.getImage(), 0, 0, null);
  }
}
