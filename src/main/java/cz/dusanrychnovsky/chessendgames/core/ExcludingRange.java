package cz.dusanrychnovsky.chessendgames.core;

import com.google.common.collect.AbstractIterator;

import java.util.Iterator;

public class ExcludingRange implements Iterable<Position> {
  
  private PositionRange decorated;
  
  public ExcludingRange(Position fromPos, Position toPos) {
    decorated = new PositionRange(fromPos, toPos);
  }
  
  @Override
  public Iterator<Position> iterator() {
    return new AbstractIterator<Position>() {
      
      private final Iterator<Position> it = decorated.iterator();
      private Position current;
      private Position next;
      
      {
        if (it.hasNext()) {
          // skip first item
          it.next();
        }
        
        if (it.hasNext()) {
          current = it.next();
        }
        
        if (it.hasNext()) {
          next = it.next();
        }
      }
  
      @Override
      protected Position computeNext() {
        
        if (next == null) {
          endOfData();
          return null;
        }
      
        Position result = current;
        
        current = next;
        next = null;
        
        if (it.hasNext()) {
          next = it.next();
        }
        
        return result;
      }
    };
  }
}
