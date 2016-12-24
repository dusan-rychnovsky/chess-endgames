package cz.dusanrychnovsky.chessendgames;

import com.google.common.collect.AbstractIterator;

import java.util.Iterator;
import java.util.Optional;

import static cz.dusanrychnovsky.chessendgames.Assertions.check;

public class PositionRange implements Iterable<Position> {
  
  private final Navigator navigator;
  private final Position from;
  private final Position to;
  
  public PositionRange(Position from, Position to) {
    
    this.from = from;
    this.to = to;
    
    if (from.getColumn() == to.getColumn()) {
      if (from.getRow().compareTo(to.getRow()) <= 0) {
        navigator = new BottomToTopVerticalNavigator();
      }
      else {
        navigator = new TopToBottomVerticalNavigator();
      }
      return;
    }
    
    if (from.getRow() == to.getRow()) {
      if (from.getColumn().compareTo(to.getColumn()) <= 0) {
        navigator = new LeftToRightHorizontalNavigator();
      }
      else {
        navigator = new RightToLeftHorizontalNavigator();
      }
      return;
    }
    
    if (getColumnDistance(from, to) == getRowDistance(from, to)) {
      if (from.getColumn().compareTo(to.getColumn()) <= 0) {
        if (from.getRow().compareTo(to.getRow()) <= 0) {
          navigator = new BottomLeftToTopRightNavigator();
        }
        else {
          navigator = new TopLeftToBottomRightNavigator();
        }
      }
      else {
        if (from.getRow().compareTo(to.getRow()) <= 0) {
          navigator = new BottomRightToTopLeftNavigator();
        }
        else {
          navigator = new TopRightToBottomLeftNavigator();
        }
      }
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
    return new AbstractIterator<Position>() {
      private Optional<Position> current = Optional.of(from);
      @Override
      protected Position computeNext() {
        
        if (!current.isPresent()) {
          endOfData();
          return null;
        }
        
        Position result = current.get();
  
        if (!current.get().equals(to)) {
          current = navigator.getNext(current.get());
          check(current.isPresent());
        }
        else {
          current = Optional.empty();
        }
        
        return result;
      }
    };
  }
  
  private interface Navigator {
    Optional<Position> getNext(Position position);
  }
  
  private static class BottomToTopVerticalNavigator implements Navigator {
    @Override
    public Optional<Position> getNext(Position position) {
      Optional<Row> nextRow = position.getRow().getNext();
      if (nextRow.isPresent()) {
        return Optional.of(new Position(position.getColumn(), nextRow.get()));
      }
      else {
        return Optional.empty();
      }
    }
  }
  
  private static class TopToBottomVerticalNavigator implements Navigator {
    @Override
    public Optional<Position> getNext(Position position) {
      Optional<Row> previousRow = position.getRow().getPrevious();
      if (previousRow.isPresent()) {
        return Optional.of(new Position(position.getColumn(), previousRow.get()));
      }
      else {
        return Optional.empty();
      }
    }
  }
  
  private static class LeftToRightHorizontalNavigator implements Navigator {
    @Override
    public Optional<Position> getNext(Position position) {
      Optional<Column> nextColumn = position.getColumn().getNext();
      if (nextColumn.isPresent()) {
        return Optional.of(new Position(nextColumn.get(), position.getRow()));
      }
      else {
        return Optional.empty();
      }
    }
  }
  
  private static class RightToLeftHorizontalNavigator implements Navigator {
    @Override
    public Optional<Position> getNext(Position position) {
      Optional<Column> previousColumn = position.getColumn().getPrevious();
      if (previousColumn.isPresent()) {
        return Optional.of(new Position(previousColumn.get(), position.getRow()));
      }
      else {
        return Optional.empty();
      }
    }
  }
  
  private static class BottomLeftToTopRightNavigator implements Navigator {
    @Override
    public Optional<Position> getNext(Position position) {
      Optional<Column> nextColumn = position.getColumn().getNext();
      Optional<Row> nextRow = position.getRow().getNext();
      if (nextColumn.isPresent() && nextRow.isPresent()) {
        return Optional.of(new Position(nextColumn.get(), nextRow.get()));
      }
      else {
        return Optional.empty();
      }
    }
  }
  
  private static class TopRightToBottomLeftNavigator implements Navigator {
    @Override
    public Optional<Position> getNext(Position position) {
      Optional<Column> previousColumn = position.getColumn().getPrevious();
      Optional<Row> previousRow = position.getRow().getPrevious();
      if (previousColumn.isPresent() && previousRow.isPresent()) {
        return Optional.of(new Position(previousColumn.get(), previousRow.get()));
      }
      else {
        return Optional.empty();
      }
    }
  }
  
  private static class TopLeftToBottomRightNavigator implements Navigator {
    @Override
    public Optional<Position> getNext(Position position) {
      Optional<Column> nextColumn = position.getColumn().getNext();
      Optional<Row> previousRow = position.getRow().getPrevious();
      if (nextColumn.isPresent() && previousRow.isPresent()) {
        return Optional.of(new Position(nextColumn.get(), previousRow.get()));
      }
      else {
        return Optional.empty();
      }
    }
  }
  
  private static class BottomRightToTopLeftNavigator implements Navigator {
    @Override
    public Optional<Position> getNext(Position position) {
      Optional<Column> previousColumn = position.getColumn().getPrevious();
      Optional<Row> nextRow = position.getRow().getNext();
      if (previousColumn.isPresent() && nextRow.isPresent()) {
        return Optional.of(new Position(previousColumn.get(), nextRow.get()));
      }
      else {
        return Optional.empty();
      }
    }
  }
}
