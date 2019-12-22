package cz.dusanrychnovsky.chessendgames;

import java.util.Iterator;
import java.util.Optional;

import static java.util.Optional.of;

public class Range<T extends Comparable<T> & Navigable<T>> implements Iterable<T> {

  private final T from;
  private final T to;

  public Range(T from, T to) {
    this.from = from;
    this.to = to;
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      private Optional<T> curr = of(from);

      @Override
      public boolean hasNext() {
        return curr.isPresent() && curr.get().compareTo(to) <= 0;
      }

      @Override
      public T next() {
        T result = curr.get();
        curr = result.next();
        return result;
      }
    };
  }
}
