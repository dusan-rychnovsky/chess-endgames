package cz.dusanrychnovsky.chessendgames.gui;

import cz.dusanrychnovsky.chessendgames.core.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static cz.dusanrychnovsky.chessendgames.core.Piece.*;

public class ChessBoard extends JPanel implements MouseClickedListener {
  
  private static final int SQUARE_WIDTH = 53;
  private static final int BOARD_WIDTH = SQUARE_WIDTH * 8;
  private static final int SQUARE_HEIGHT = 53;
  private static final int BOARD_HEIGHT = SQUARE_HEIGHT * 8;
  
  private final Image boardImage;
  private final Image whiteKingIcon;
  private final Image whiteRookIcon;
  private final Image blackKingIcon;
  
  private final Map<Piece, Image> iconMapping;
  
  private Situation situation;
  
  private volatile CompletableFuture<Position> position = new CompletableFuture<>();
  
  public ChessBoard() {
    boardImage = loadImage("empty-board.png");
  
    iconMapping = new HashMap<>();
    
    whiteKingIcon = loadImage("white-king.png");
    iconMapping.put(WHITE_KING, whiteKingIcon);
  
    whiteRookIcon = loadImage("white-rook.png");
    iconMapping.put(WHITE_ROOK, whiteRookIcon);
  
    blackKingIcon = loadImage("black-king.png");
    iconMapping.put(BLACK_KING, blackKingIcon);
    
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
  
  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    
    Graphics2D graphics2d = (Graphics2D) graphics;
    paintBoard(graphics2d);
    if (situation != null) {
      printSituation(graphics2d, situation);
    }
  }
  
  private void printSituation(Graphics2D graphics2d, Situation situation) {
    for (Piece piece : situation.getPieces()) {
      printPiece(graphics2d, piece, situation.getPosition(piece).get());
    }
  }
  
  private void printPiece(Graphics2D graphics2d, Piece piece, Position position) {
    graphics2d.drawImage(
      iconMapping.get(piece),
      getPosX(position),
      getPosY(position),
      null
    );
  }
  
  private int getPosY(Position position) {
    return BOARD_HEIGHT - (position.getRow().getOrdinal() + 1) * SQUARE_HEIGHT;
  }
  
  private int getPosX(Position position) {
    return position.getColumn().getOrdinal() * SQUARE_WIDTH;
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
    System.out.println("Mouse clicked.");
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
