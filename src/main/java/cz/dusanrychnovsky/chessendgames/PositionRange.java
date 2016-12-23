package cz.dusanrychnovsky.chessendgames;

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
    
    if (getColumnDistance(from, to) == getRowDistance(from, to)) {
      wrapped = new DiagonalRange(from, to);
      return;
    }
      
    throw new IllegalArgumentException(
      "[" + from + ", " + to + "] does not form a valid range."
    );
  }
  
  private static int getRowDistance(Position from, Position to) {
    return from.getRow().getDistance(to.getRow());
  }
  
  private static int getColumnDistance(Position from, Position to) {
    return from.getColumn().getDistance(to.getColumn());
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
  
  private static class DiagonalRange implements Iterable<Position> {
  
    private final Iterable<Position> wrapped;
    
    public DiagonalRange(Position from, Position to) {
      checkArgument(getColumnDistance(from, to) == getRowDistance(from, to));
  
      if (from.getColumn().compareTo(to.getColumn()) > 0) {
        Position tmp = from;
        from = to;
        to = tmp;
      }
      
      if (from.getRow().compareTo(to.getRow()) <= 0) {
        wrapped = new TopDownDiagonalRange(from, to);
      }
      else {
        wrapped = new BottomUpDiagonalRange(from, to);
      }
    }
  
    @Override
    public Iterator<Position> iterator() {
      return wrapped.iterator();
    }
  }
  
  private static class TopDownDiagonalRange implements Iterable<Position> {
  
    private final Position from;
    private final Position to;
    
    public TopDownDiagonalRange(Position from, Position to) {
      checkArgument(getColumnDistance(from, to) == getRowDistance(from, to));
      checkArgument(from.getColumn().compareTo(to.getColumn()) <= 0);
      checkArgument(from.getRow().compareTo(to.getRow()) <= 0);
      
      this.from = from;
      this.to = to;
    }
    
    @Override
    public Iterator<Position> iterator() {
      return new AbstractIterator<Position>() {
        private Position current = from;
        @Override
        protected Position computeNext() {
          
          if (current == null) {
            endOfData();
            return null;
          }
          
          Position result = current;
          
          if (!current.equals(to)) {
            current = new Position(
              current.getColumn().getNext().get(),
              current.getRow().getNext().get()
            );
          }
          else {
            current = null;
          }
          
          return result;
        }
      };
    }
  }
  
  private static class BottomUpDiagonalRange implements Iterable<Position> {
  
    private final Position from;
    private final Position to;
  
    public BottomUpDiagonalRange(Position from, Position to) {
      checkArgument(getColumnDistance(from, to) == getRowDistance(from, to));
      checkArgument(from.getColumn().compareTo(to.getColumn()) <= 0);
      checkArgument(from.getRow().compareTo(to.getRow()) >= 0);
      
      this.from = from;
      this.to = to;
    }
    
    @Override
    public Iterator<Position> iterator() {
      return new AbstractIterator<Position>() {
        private Position current = from;
        @Override
        protected Position computeNext() {
          
          if (current == null) {
            endOfData();
            return null;
          }
          
          Position result = current;
          
          if (!current.equals(to)) {
            current = new Position(
              current.getColumn().getNext().get(),
              current.getRow().getPrevious().get()
            );
          }
          else {
            current = null;
          }
          
          return result;
        }
      };
    }
  }
}