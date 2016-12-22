package cz.dusanrychnovsky.chessendgames.yaat;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;

import java.util.Iterator;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

public class Range<T extends Comparable<T> & Navigable<T>> implements Iterable<T> {

  private final T from;
  private final T to;

  public Range(T from, T to) {
    checkArgument(from.compareTo(to) <= 0, "[from, to] must form a non-empty interval");
    this.from = from;
    this.to = to;
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      private Optional<T> current = Optional.of(from);

      @Override
      public boolean hasNext() {
        return current.isPresent();
      }

      @Override
      public T next() {
        T result = current.get();

        current = result.getNext();
        if (current.isPresent() && current.get().compareTo(to) > 0) {
          current = Optional.empty();
        }

        return result;
      }
    };
  }
}
