package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.core.*;
import java.util.Random;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.get;
import static com.google.common.collect.Iterables.size;

public class RandomMovePlayer implements Player {
  
  private final Random rnd;
  private final Color color;
  private final UserInterface ui;
  
  public RandomMovePlayer(Random rnd, Color color, UserInterface ui) {
    this.rnd = rnd;
    this.color = color;
    this.ui = ui;
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
    ui.displayMessage(color + " Picked move: " + result);
    
    return result;
  }
  
  private <T> T getRandomItem(Iterable<T> items) {
    return get(items, rnd.nextInt(size(items)));
  }
}
