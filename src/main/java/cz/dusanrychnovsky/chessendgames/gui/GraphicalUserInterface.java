package cz.dusanrychnovsky.chessendgames.gui;

import cz.dusanrychnovsky.chessendgames.UserInterface;
import cz.dusanrychnovsky.chessendgames.core.Color;
import cz.dusanrychnovsky.chessendgames.core.Move;
import cz.dusanrychnovsky.chessendgames.core.Position;
import cz.dusanrychnovsky.chessendgames.core.Result;
import cz.dusanrychnovsky.chessendgames.core.Situation;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

import static cz.dusanrychnovsky.chessendgames.core.Color.WHITE;
import static cz.dusanrychnovsky.chessendgames.core.Piece.BLACK_KING;
import static cz.dusanrychnovsky.chessendgames.core.Piece.WHITE_KING;
import static cz.dusanrychnovsky.chessendgames.core.Piece.WHITE_ROOK;
import static cz.dusanrychnovsky.chessendgames.core.Position.A7;
import static cz.dusanrychnovsky.chessendgames.core.Position.A8;
import static cz.dusanrychnovsky.chessendgames.core.Position.F3;

public class GraphicalUserInterface implements UserInterface {
  
  private final MainWindow mainWindow;
  
  public GraphicalUserInterface() {
    mainWindow = new MainWindow();
    runOnUiThread(() -> {
      mainWindow.setUp();
      mainWindow.open();
    });
  }

  @Override
  public void displayPrompt(String message) {
    runOnUiThread(() -> mainWindow.showMessage(message));
  }

  @Override
  public void displayChosenMove(Color color, Move move) {
    runOnUiThread(() -> mainWindow.showMove(color, move));
  }

  @Override
  public void displayResult(Result result) {
    runOnUiThread(() -> mainWindow.showResult(result));
  }

  @Override
  public void displaySituation(Situation situation) {
    runOnUiThread(() -> {
      mainWindow.setSituation(situation);
    });
  }
  
  @Override
  public Move requestMove(Situation situation) {
    Color color = situation.getCurrentColor();
  
    displayPrompt(new RequestMoveMessage(color).toString());
    Move move = requestMove(color);

    while (!situation.isValidMove(move)) {
      displayPrompt(new RequestMoveInErrorMessage(color, move).toString());
      move = requestMove(color);
    }
  
    return move;
  }

  private Move requestMove(Color color) {
    dropAllBorders();

    Position fromPos = requestPosition();
    displayBorderAroundPosition(fromPos);
    displayPrompt(new RequestMoveMessage(color, fromPos).toString());

    Position toPos = requestPosition();
    displayBorderAroundPosition(toPos);
    displayPrompt(new RequestMoveMessage(color, fromPos, toPos).toString());

    return new Move(fromPos, toPos);
  }

  private void displayBorderAroundPosition(Position pos) {
    runOnUiThread(() -> mainWindow.addBorderAroundPosition(pos));
  }

  private void dropAllBorders() {
    runOnUiThread(() -> mainWindow.clearBorders());
  }

  @Override
  public Situation requestInitialSituation() {

    displayPrompt(new RequestSituationMessage().toString());
    Situation situation = requestSituation();

    while (situation == null) {
      displayPrompt(new RequestSituationInErrorMessage().toString());
      situation = requestSituation();
    }

    return situation;
  }

  private Situation requestSituation() {
    dropAllBorders();

    Position whiteKingPos = requestPosition();
    displayBorderAroundPosition(whiteKingPos);  // TODO: display here the figure with the border
    displayPrompt(new RequestSituationMessage(whiteKingPos).toString());

    Position whiteRookPos = requestPosition();
    displayBorderAroundPosition(whiteRookPos);
    displayPrompt(new RequestSituationMessage(whiteKingPos, whiteRookPos).toString());

    Position blackKingPos = requestPosition();
    displayBorderAroundPosition(blackKingPos);
    displayPrompt(new RequestSituationMessage(whiteKingPos, whiteRookPos, blackKingPos).toString());

    try {
      return Situation.builder(WHITE)
        .addPiece(WHITE_KING, whiteKingPos)
        .addPiece(WHITE_ROOK, whiteRookPos)
        .addPiece(BLACK_KING, blackKingPos)
        .build();
    }
    catch (IllegalArgumentException | IllegalStateException ex) {
      return null;
    }
  }

  private Position requestPosition() {
    return mainWindow.requestPosition();
  }

  private void runOnUiThread(Runnable action) {
    try {
      SwingUtilities.invokeAndWait(action::run);
    }
    catch (InterruptedException | InvocationTargetException ex) {
      throw new RuntimeException(ex);
    }
  }

  private static class RequestMoveMessage {

    private final Color color;
    private final Position from;
    private final Position to;

    public RequestMoveMessage(Color color, Position from, Position to) {
      this.color = color;
      this.from = from;
      this.to = to;
    }

    public RequestMoveMessage(Color color) {
      this(color, null, null);
    }

    public RequestMoveMessage(Color color, Position from) {
      this(color, from, null);
    }

    @Override
    public String toString() {

      StringBuilder builder = new StringBuilder();
      builder.append(color);
      builder.append(" Enter move:");

      if (from != null) {
        builder.append(" ");
        builder.append(from);
      }

      if (to != null) {
        builder.append(" ");
        builder.append(to);
      }

      return builder.toString();
    }
  }

  private static class RequestMoveInErrorMessage {

    private final Color color;
    private final Move invalidMove;

    public RequestMoveInErrorMessage(Color color, Move invalidMove) {
      this.color = color;
      this.invalidMove = invalidMove;
    }

    @Override
    public String toString() {
      return color + " Invalid move: " + invalidMove + "! Enter move:";
    }
  }

  private static class RequestSituationMessage {

    // TODO: refactoring needed

    private final Position whiteKingPos;
    private final Position whiteRookPos;
    private final Position blackKingPos;

    public RequestSituationMessage(Position whiteKingPos, Position whiteRookPos, Position blackKingPos) {
      this.whiteKingPos = whiteKingPos;
      this.whiteRookPos = whiteRookPos;
      this.blackKingPos = blackKingPos;
    }

    public RequestSituationMessage() {
      this(null, null, null);
    }

    public RequestSituationMessage(Position whiteKingPos) {
      this(whiteKingPos, null, null);
    }

    public RequestSituationMessage(Position whiteKingPos, Position whiteRookPos) {
      this(whiteKingPos, whiteRookPos, null);
    }

    @Override
    public String toString() {
      if (whiteKingPos == null) {
        return "Place " + WHITE_KING + ":";
      }
      else if (whiteRookPos == null) {
        return "Place " + WHITE_ROOK + " (" + whiteKingPos + "):";
      }
      else if (blackKingPos == null) {
        return "Place " + BLACK_KING + " (" + whiteKingPos + ", " + whiteRookPos + "):";
      }
      else {
        return "Initial situation: " + whiteKingPos + ", " + whiteRookPos + ", " + blackKingPos;
      }
    }
  }

  private static class RequestSituationInErrorMessage {
    @Override
    public String toString() {
      return "Invalid situation! Place " + WHITE_KING + ":";
    }
  }
}
