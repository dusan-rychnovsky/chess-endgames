package cz.dusanrychnovsky.chessendgames.yaat;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;

import java.util.Iterator;

import static com.google.common.base.Preconditions.checkArgument;

public class PositionRange implements Iterable<Position> {
  
  private final Iterable<Position> wrapped;
  
  public PositionRange(Position from, Position to) {
    
    if (from.getColumn() == to.getColumn()) {
      wrapped = new VerticalRange(from, to);
      return;
    }
    
    if (from.getRow() == to.getRow()) {
      wrapped = new HorizontalRange(from, to);
      return;
    }
    
    throw new IllegalArgumentException(
      "[" + from + ", " + to + "] does not form a valid range."
    );
  }
  
  @Override
  public Iterator<Position> iterator() {
    return wrapped.iterator();
  }
  
  private static class HorizontalRange implements Iterable<Position> {
  
    private final Row row;
    private final Range<Column> columnRange;
    
    public HorizontalRange(Position from, Position to) {
      checkArgument(from.getRow() == to.getRow());
    
      row = from.getRow();
      
      if (from.getColumn().compareTo(to.getColumn()) <= 0) {
        columnRange = new Range<>(from.getColumn(), to.getColumn());
      }
      else {
        columnRange = new Range<>(to.getColumn(), from.getColumn());
      }
    }
    
    @Override
    public Iterator<Position> iterator() {
      return new AbstractIterator<Position>() {
        private final Iterator<Column> columnIt = columnRange.iterator();
        @Override
        protected Position computeNext() {
          if (columnIt.hasNext()) {
            return new Position(columnIt.next(), row);
          }
          else {
            endOfData();
            return null;
          }
        }
      };
    }
  }
  
  private static class VerticalRange implements Iterable<Position> {
  
    private final Column column;
    private final Range<Row> rowRange;
    
    public VerticalRange(Position from, Position to) {
      checkArgument(from.getColumn() == to.getColumn());
      
      column = from.getColumn();
      
      if (from.getRow().compareTo(to.getRow()) <= 0) {
        rowRange = new Range<>(from.getRow(), to.getRow());
      }
      else {
        rowRange = new Range<>(to.getRow(), from.getRow());
      }
    }
    
    @Override
    public Iterator<Position> iterator() {
      return new AbstractIterator<Position>() {
        private final Iterator<Row> rowIt = rowRange.iterator();
        @Override
        protected Position computeNext() {
          if (rowIt.hasNext()) {
            return new Position(column, rowIt.next());
          }
          else {
            endOfData();
            return null;
          }
        }
      };
    }
  }
}