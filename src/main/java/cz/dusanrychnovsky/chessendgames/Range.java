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
    return new Iterator<>() {

      private Optional<T> curr = of(from);
      private boolean hasNext = true;

      @Override
      public boolean hasNext() {
        return hasNext;
      }

      @Override
      public T next() {
        T result = curr.get();

        int comparison = (int) Math.signum(to.compareTo(result));
        switch (comparison) {
          case 0:
            hasNext = false;
            break;

          case 1:
            curr = result.next();
            break;

          case -1:
            curr = result.prev();
            break;

          default:
            throw new IllegalStateException(
              "Unexpected comparison result: " + to + " x " + result + ": " + comparison
            );
        }
        return result;
      }
    };
  }
}
