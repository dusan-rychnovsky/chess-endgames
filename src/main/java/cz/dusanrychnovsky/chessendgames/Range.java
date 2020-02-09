package cz.dusanrychnovsky.chessendgames;

import java.util.Iterator;
import java.util.Optional;
import static java.util.Optional.of;

public class Range<T extends Comparable<T>> implements Iterable<T> {

  private final Axis<T> axis;
  private final T from;
  private final T to;

  public static <T extends Comparable<T> & Navigable> Range<T> from(T from, T to) {
    return new Range<>(from, to, getAxis(from, to));
  }

  private static <T extends Comparable<T> & Navigable> Axis<T> getAxis(T from, T to) {
    if (from.compareTo(to) <= 0) {
      // left to right
      return Navigable::next;
    }
    else {
      // right to left
      return Navigable::prev;
    }
  }

  public static Range<Position> from(Position from, Position to) {
    return new Range<>(from, to, getAxis(from, to));
  }

  private static Axis<Position> getAxis(Position from, Position to) {
    if (from.row() == to.row()) {
      if (from.column().compareTo(to.column()) <= 0) {
        // left to right
        return pos -> pos.column().next().map(col -> Position.get(col, pos.row()));
      }
      else {
        // right to left
        return pos -> pos.column().prev().map(col -> Position.get(col, pos.row()));
      }
    }
    if (from.column() == to.column()) {
      if (from.row().compareTo(to.row()) <= 0) {
        // bottom to top
        return pos -> pos.row().next().map(row -> Position.get(pos.column(), row));
      }
      else {
        // top to bottom
        return pos -> pos.row().prev().map(row -> Position.get(pos.column(), row));
      }
    }
    throw new IllegalArgumentException(
      "Unsupported combination of positions: " + from + ", " + to + "."
    );
  }

  public Range(T from, T to, Axis<T> axis) {
    this.axis = axis;
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
        var result = curr.get();
        curr = axis.next(result);
        hasNext = !result.equals(to);
        return result;
      }
    };
  }
}
