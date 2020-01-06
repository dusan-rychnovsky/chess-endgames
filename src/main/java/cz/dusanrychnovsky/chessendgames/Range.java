package cz.dusanrychnovsky.chessendgames;

import java.util.Iterator;
import java.util.Optional;
import static java.util.Optional.of;

public class Range<T extends Comparable<T> & Navigable<T>> implements Iterable<T> {

  private final Axis<T> axis;
  private final T from;
  private final T to;

  public Range(T from, T to) {
    this.from = from;
    this.to = to;
    if (from.compareTo(to) <= 1) {
      axis = Axis.right();
    }
    else {
      axis = Axis.left();
    }
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<>() {

      private Optional<T> curr = of(from);
      private boolean hasNext = true;

      @Override
      public boolean hasNext() {
        return hasNext;
      }

      @Override
      public T next() {
        var result = curr.get();
        curr = axis.next(result);
        hasNext = !result.equals(to);
        return result;
      }
    };
  }
}
