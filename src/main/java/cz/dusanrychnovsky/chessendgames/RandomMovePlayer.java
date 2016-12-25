package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.core.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.get;
import static com.google.common.collect.Iterables.size;

public class RandomMovePlayer implements Player {
  
  private final Random rnd;
  private final Color color;
  private final DisplayMessage displayMessage;
  
  public RandomMovePlayer(Random rnd, Color color, DisplayMessage displayMessage) {
    this.rnd = rnd;
    this.color = color;
    this.displayMessage = displayMessage;
  }
  
  @Override
  public Color getColor() {
    return color;
  }
  
  @Override
  public Move pickMove(Situation situation) {
    
    Piece piece = getRandomItem(situation.getPiecesOfColor(color));
    Position fromPos = situation.getPosition(piece).get();
    
    Iterable<Move> moves = filter(
      piece.getType().listAllMovesFromPosition(fromPos),
      situation::isValidMove
    );
    
    Move result = getRandomItem(moves);
    displayMessage.displayMessage(color + " Picked move: " + result);
    
    return result;
  }
  
  private <T> T getRandomItem(Iterable<T> items) {
    return get(items, rnd.nextInt(size(items)));
  }
}
