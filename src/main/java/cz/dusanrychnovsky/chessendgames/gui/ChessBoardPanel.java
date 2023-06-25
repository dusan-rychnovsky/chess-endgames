package cz.dusanrychnovsky.chessendgames.gui;

import cz.dusanrychnovsky.chessendgames.core.*;
import cz.dusanrychnovsky.chessendgames.util.UnexpectedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static cz.dusanrychnovsky.chessendgames.core.Piece.*;
import static cz.dusanrychnovsky.chessendgames.gui.SwingExtensions.runOnUiThread;

public class ChessBoardPanel {

  private static final Logger LOGGER = LogManager.getLogger(ChessBoardPanel.class);

  private static final int LEFT_OFFSET = 25;
  private static final int BOTTOM_OFFSET = 25;
  private static final int BOARD_WIDTH = 825;
  private static final int BOARD_HEIGHT = 825;
  private static final int SQUARE_WIDTH = 100;
  private static final int SQUARE_HEIGHT = 100;

  private final JPanel panel;
  private Position lightBorderPos;
  private final Set<Position> darkBorderPos = new HashSet<>();
  private Board board;

  private volatile CompletableFuture<Position> future = new CompletableFuture<>();

  public ChessBoardPanel(Image boardImg, Image lightBorderImg, Image darkBorderImg, Map<Piece, Image> pieceImgs) {
    panel = new JPanel()
    {
      @Override
      public void paint(Graphics graphics) {
        super.paint(graphics);

        Graphics2D graphics2d = (Graphics2D) graphics;
        paintBoard(graphics2d);

        if (board != null) {
          paintSituation(graphics2d, board);
        }

        if (lightBorderPos != null) {
          paintBorder(graphics2d, lightBorderImg, ChessBoardPanel.this.lightBorderPos);
        }

        for (var pos : darkBorderPos) {
          paintBorder(graphics2d, darkBorderImg, pos);
        }
      }

      private void paintSituation(Graphics2D graphics, Board board) {
        board.pieces()
          .forEach(piece -> paintPiece(graphics, piece));
      }

      private void paintPiece(Graphics2D graphics, PiecePosition piece) {
        var point = Point.fromPosition(piece.position());
        graphics.drawImage(
          pieceImgs.get(piece.piece()),
          point.px,
          point.py,
          null
        );
      }

      private void paintBorder(Graphics2D graphics, Image img, Position pos) {
        var point = Point.fromPosition(pos);
        graphics.drawImage(
          img,
          point.px - 2,
          point.py - 2,
          null
        );
      }

      private void paintBoard(Graphics2D graphics2d) {
        graphics2d.drawImage(boardImg, 0, 0, null);
      }
    };

    panel.addMouseListener(new MouseMovedClickedListener());
    panel.addMouseMotionListener(new MouseMovedClickedListener());
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
    result.preferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
    return result;
  }

  private static Image loadImage(String fileName) {
    return new ImageIcon(ChessBoardPanel.class.getResource("/img/" + fileName)).getImage();
  }

  public void attach(JFrame frame, Object constraints) {
    frame.add(panel, constraints);
  }

  public void preferredSize(Dimension dimension) {
    panel.setPreferredSize(dimension);
  }

  public int width() {
    return panel.getWidth();
  }

  public int height() {
    return panel.getHeight();
  }

  public void addDarkBorder(Position pos) {
    runOnUiThread(() -> {
      darkBorderPos.add(pos);
      panel.repaint();
    });
  }

  public void clearDarkBorders() {
    runOnUiThread(() -> {
      darkBorderPos.clear();
      panel.repaint();
    });
  }

  public Position queryPosition() {
    LOGGER.debug("Resetting future.");
    future = new CompletableFuture<>();
    try {
      LOGGER.debug("Going to wait for future.");
      var result = future.get();
      LOGGER.debug("Future completed. Got: {}", result);
      return result;
    }
    catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
      throw new UnexpectedException(ex);
    }
    catch (ExecutionException ex) {
      throw new UnexpectedException(ex);
    }
  }

  public void showSituation(Board board) {
    this.board = board;
    panel.repaint();
  }

  private class MouseMovedClickedListener implements MouseMotionListener, MouseListener {

    @Override
    public void mouseClicked(MouseEvent event) {
      LOGGER.debug("Mouse clicked. Point: {}", event.getPoint());
      Optional<Position> pos = new Point(event.getPoint()).toPosition();
      LOGGER.debug("Position: {}", pos);
      pos.ifPresent(
        position -> {
          LOGGER.debug("Completing future.");
          future.complete(position);
        }
      );
      LOGGER.debug("Mouse clicked handled.");
    }

    @Override
    public void mouseMoved(MouseEvent event) {
      var pos = new Point(event.getPoint()).toPosition();
      if (pos.isPresent()) {
        setLightBorder(pos.get());
      }
      else {
        clearLightBorder();
      }
    }

    private void setLightBorder(Position pos) {
      lightBorderPos = pos;
      panel.repaint();
    }

    private void clearLightBorder() {
      lightBorderPos = null;
      panel.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
      // no action
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      // no action
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      // no action
    }

    @Override
    public void mouseExited(MouseEvent e) {
      // no action
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      // no action
    }
  }

  private record Point(int px, int py) {

    public Point(java.awt.Point point) {
      this(point.x, point.y);
    }

    public static Point fromPosition(Position pos) {
      return new Point(getPX(pos), getPY(pos));
    }

    public Optional<Position> toPosition() {
      Optional<Column> column = getColumn(px);
      Optional<Row> row = getRow(py);
      if (column.isPresent() && row.isPresent()) {
        return Optional.of(
          Position.get(column.get(), row.get())
        );
      }
      else {
        return Optional.empty();
      }
    }

    private static int getPX(Position pos) {
      return LEFT_OFFSET + pos.column().ord() * SQUARE_WIDTH;
    }

    private static int getPY(Position pos) {
      return (7 - pos.row().ord()) * SQUARE_HEIGHT;
    }

    private static Optional<Column> getColumn(int px) {
      if (px < LEFT_OFFSET) {
        return Optional.empty();
      }

      px -= LEFT_OFFSET;
      return Optional.of(
        Column.values()[px / SQUARE_WIDTH]
      );
    }

    private static Optional<Row> getRow(int py) {
      if (py >= BOARD_HEIGHT - BOTTOM_OFFSET) {
        return Optional.empty();
      }

      return Optional.of(
        Row.values()[7 - py / SQUARE_HEIGHT]
      );
    }
  }
}
