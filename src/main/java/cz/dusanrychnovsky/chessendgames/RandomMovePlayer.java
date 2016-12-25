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
  private final BufferedWriter writer;
  
  public RandomMovePlayer(Random rnd, Color color, OutputStream out) {
    this.rnd = rnd;
    this.color = color;
    writer = new BufferedWriter(new OutputStreamWriter(out));
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
    writeLn(color + " move: " + result);
    
    return result;
  }
  
  private void writeLn(String text) {
    try {
      writer.write(text);
      writer.newLine();
      writer.flush();
    }
    catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
  
  private <T> T getRandomItem(Iterable<T> items) {
    return get(items, rnd.nextInt(size(items)));
  }
}
