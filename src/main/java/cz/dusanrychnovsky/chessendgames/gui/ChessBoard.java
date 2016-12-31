package cz.dusanrychnovsky.chessendgames.gui;

import cz.dusanrychnovsky.chessendgames.core.*;

import java.util.List;
import java.util.LinkedList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static cz.dusanrychnovsky.chessendgames.core.Piece.*;

public class ChessBoard extends JPanel implements MouseClickedListener {

  private static final int BOARD_WIDTH = 825;
  private static final int SQUARE_WIDTH = 100;
  private static final int LEFT_OFFSET = 25;

  private static final int BOARD_HEIGHT = 825;
  private static final int SQUARE_HEIGHT = 100;
  private static final int TOP_OFFSET = 0;

  private final Image boardImage;
  private final Image borderImage;
  private final Map<Piece, Image> iconMapping;
  
  private Situation situation;
  private List<Position> borderedPositions = new LinkedList<>();

  private volatile CompletableFuture<Position> position = new CompletableFuture<>();
  
  public ChessBoard() {
    boardImage = loadImage("empty-board.png");
    borderImage = loadImage("border.png");

    iconMapping = new HashMap<>();
    iconMapping.put(WHITE_KING, loadImage("white-king.png"));
    iconMapping.put(WHITE_ROOK, loadImage("white-rook.png"));
    iconMapping.put(BLACK_KING, loadImage("black-king.png"));
    
    setPreferredSize(
      new Dimension(BOARD_WIDTH, BOARD_HEIGHT)
    );
  }
  
  private Image loadImage(String fileName) {
    return new ImageIcon(ChessBoard.class.getResource(fileName)).getImage();
  }
  
  public void setSituation(Situation situation) {
    this.situation = situation;
    repaint();
  }

  public void addBorder(Position pos) {
    borderedPositions.add(pos);
    repaint();
  }

  public void clearBorders() {
    borderedPositions.clear();
    repaint();
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    
    Graphics2D graphics2d = (Graphics2D) graphics;
    paintBoard(graphics2d);
    for (Position position : borderedPositions) {
      paintBorderAroundPosition(graphics2d, position);
    }
    if (situation != null) {
      paintSituation(graphics2d, situation);
    }
  }

  private void paintBorderAroundPosition(Graphics2D graphics2d, Position position) {
    graphics2d.drawImage(
      borderImage,
      getPosX(position) - 2,
      getPosY(position) - 2,
      null
    );
  }

  private void paintSituation(Graphics2D graphics2d, Situation situation) {
    for (Piece piece : situation.getPieces()) {
      paintPiece(graphics2d, piece, situation.getPosition(piece).get());
    }
  }
  
  private void paintPiece(Graphics2D graphics2d, Piece piece, Position position) {
    graphics2d.drawImage(
      iconMapping.get(piece),
      getPosX(position),
      getPosY(position),
      null
    );
  }
  
  private int getPosY(Position position) {
    return TOP_OFFSET + (7 - position.getRow().getOrdinal()) * SQUARE_HEIGHT;
  }
  
  private int getPosX(Position position) {
    return LEFT_OFFSET + position.getColumn().getOrdinal() * SQUARE_WIDTH;
  }
  
  private void paintBoard(Graphics2D graphics2d) {
    graphics2d.drawImage(boardImage, 0, 0, null);
  }
  
  public Position getPosition() {
    position = new CompletableFuture<>();
    try {
      return position.get();
    }
    catch (InterruptedException | ExecutionException ex) {
      throw new RuntimeException(ex);
    }
  }
  
  @Override
  public void mouseClicked(MouseEvent event) {
    Point point = event.getPoint();
    position.complete(
      Position.get(
        getColumn((int) point.getX()),
        getRow((int) point.getY())
      )
    );
  }
  
  private Column getColumn(int posX) {
    return Column.values()[posX / SQUARE_WIDTH];
  }
  
  private Row getRow(int posY) {
    // TODO: refactore
    return Row.values()[7 - posY / SQUARE_HEIGHT];
  }
}
