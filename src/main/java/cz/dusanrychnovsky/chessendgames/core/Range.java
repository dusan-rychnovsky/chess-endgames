package cz.dusanrychnovsky.chessendgames.core;

import com.google.common.collect.AbstractIterator;

import java.util.Iterator;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

public class Range<T extends Comparable<T> & Navigable<T>> implements Iterable<T> {

  // TODO: maybe consolidate with PositionRange?
  
  private final Navigator<T> navigator;
  private final T from;
  private final T to;

  public Range(T from, T to) {
    
    this.from = from;
    this.to = to;
    
    if (from.compareTo(to) <= 0) {
      navigator = new StraightNavigator();
    }
    else {
      navigator = new ReversedNavigator();
    }
  }
  
  
  @Override
  public Iterator<T> iterator() {
    return new AbstractIterator<T>() {
      private Optional<T> current = Optional.of(from);
      @Override
      protected T computeNext() {
        
        if (!current.isPresent()) {
          endOfData();
          return null;
        }
        
        T result = current.get();
        
        if (!current.get().equals(to)) {
          current = navigator.getNext(current.get());
          Assertions.check(current.isPresent());
        }
        else {
          current = Optional.empty();
        }
        
        return result;
      }
    };
  }
  
  private interface Navigator<T extends Navigable<T>> {
    Optional<T> getNext(T item);
  }
  
  private static class StraightNavigator<T extends Navigable<T>> implements Navigator<T> {
    @Override
    public Optional<T> getNext(T item) {
      return item.getNext();
    }
  }
  
  private static class ReversedNavigator<T extends Navigable<T>> implements Navigator<T> {
    @Override
    public Optional<T> getNext(T item) {
      return item.getPrevious();
    }
  }
}
