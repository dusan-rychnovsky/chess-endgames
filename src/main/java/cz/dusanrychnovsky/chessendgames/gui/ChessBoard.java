package cz.dusanrychnovsky.chessendgames.gui;

import cz.dusanrychnovsky.chessendgames.core.*;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.LinkedList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static cz.dusanrychnovsky.chessendgames.core.Piece.*;

public class ChessBoard extends JPanel {

  private static final int BOARD_WIDTH = 825;
  private static final int SQUARE_WIDTH = 100;
  private static final int LEFT_OFFSET = 25;

  private static final int BOARD_HEIGHT = 825;
  private static final int SQUARE_HEIGHT = 100;
  private static final int BOTTOM_OFFSET = 25;

  private final Image boardImage;
  private final Image lightBorderImage;
  private final Image darkBorderImage;
  private final Map<Piece, Image> iconMapping;
  
  private Situation situation;
  private List<Position> darkBorderedPositions = new LinkedList<>();
  private Position lightBorderedPosition;

  private volatile CompletableFuture<Position> position = new CompletableFuture<>();
  
  public ChessBoard() {
    boardImage = loadImage("empty-board.png");
    lightBorderImage = loadImage("light-border.png");
    darkBorderImage = loadImage("dark-border.png");

    iconMapping = new HashMap<>();
    iconMapping.put(WHITE_KING, loadImage("white-king.png"));
    iconMapping.put(WHITE_ROOK, loadImage("white-rook.png"));
    iconMapping.put(BLACK_KING, loadImage("black-king.png"));
    
    setPreferredSize(
      new Dimension(BOARD_WIDTH, BOARD_HEIGHT)
    );

    addMouseListener(new MouseClickedListener());

    addMouseListener(new MouseMovedListener());
    addMouseMotionListener(new MouseMovedListener());
  }
  
  private Image loadImage(String fileName) {
    return new ImageIcon(ChessBoard.class.getResource(fileName)).getImage();
  }
  
  public void setSituation(Situation situation) {
    this.situation = situation;
    repaint();
  }

  public void addDarkBorder(Position pos) {
    darkBorderedPositions.add(pos);
    repaint();
  }

  public void clearDarkBorders() {
    darkBorderedPositions.clear();
    repaint();
  }

  private void setLightBorder(Position pos) {
    lightBorderedPosition = pos;
    repaint();
  }

  private void clearLightBorder() {
    lightBorderedPosition = null;
    repaint();
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    
    Graphics2D graphics2d = (Graphics2D) graphics;
    paintBoard(graphics2d);
    if (lightBorderedPosition != null) {
      paintBorderAroundPosition(graphics2d, lightBorderedPosition, lightBorderImage);
    }
    for (Position position : darkBorderedPositions) {
      paintBorderAroundPosition(graphics2d, position, darkBorderImage);
    }
    if (situation != null) {
      paintSituation(graphics2d, situation);
    }
  }

  private void paintBorderAroundPosition(Graphics2D graphics2d, Position position, Image borderImage) {
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
    return (7 - position.getRow().getOrdinal()) * SQUARE_HEIGHT;
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

  private Optional<Column> getColumn(int posX) {

    if (posX < LEFT_OFFSET) {
      return Optional.empty();
    }

    posX -= LEFT_OFFSET;
    return Optional.of(
      Column.values()[posX / SQUARE_WIDTH]
    );
  }
  
  private Optional<Row> getRow(int posY) {

    if (posY >= BOARD_HEIGHT - BOTTOM_OFFSET) {
      return Optional.empty();
    }

    return Optional.of(
      Row.values()[7 - posY / SQUARE_HEIGHT]
    );
  }

  private Optional<Position> getPosition(Point point) {

    Optional<Column> column = getColumn((int) point.getX());
    Optional<Row> row = getRow((int) point.getY());

    if (column.isPresent() && row.isPresent()) {
      return Optional.of(
        Position.get(column.get(), row.get())
      );
    }
    else {
      return Optional.empty();
    }
  }

  public class MouseClickedListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent event) {
      Optional<Position> pos = getPosition(event.getPoint());
      if (pos.isPresent()) {
        position.complete(pos.get());
      }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
  }

  public class MouseMovedListener implements MouseMotionListener, MouseListener {

    @Override
    public void mouseExited(MouseEvent e) {
      clearLightBorder();
    }

    @Override
    public void mouseMoved(MouseEvent event) {
      Optional<Position> pos = getPosition(event.getPoint());
      if (pos.isPresent()) {
        setLightBorder(pos.get());
      }
      else {
        clearLightBorder();
      }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}
  }
}
