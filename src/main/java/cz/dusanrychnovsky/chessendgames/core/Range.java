package cz.dusanrychnovsky.chessendgames.core;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * For types with total order, represents an (ordered) range of values between
 * {@link #from} and {@link #to}.
 *
 * {@link Axis} is an internal concept which captures details of how
 * to traverse between {@link #from} and {@link #to}.
 */
public class Range<T extends Comparable<T>> implements Iterable<T> {

  private final Axis<T> axis;
  private final T from;
  private final T to;

  public static <T extends Comparable<T> & Navigable<T>> Range<T> from(T from, T to) {
    return new Range<>(from, to, Axis.get(from, to));
  }

  public static Range<Position> from(Position from, Position to) {
    return new Range<>(from, to, Axis.get(from, to));
  }

  private Range(T from, T to, Axis<T> axis) {
    this.axis = axis;
    this.from = from;
    this.to = to;
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<>() {

      private T curr = from;
      private boolean hasNext = true;

      @Override
      public boolean hasNext() {
        return hasNext;
      }

      @Override
      public T next() {
        if (!hasNext) {
          throw new NoSuchElementException();
        }
        var result = curr;
        curr = axis.next(result).orElse(null);
        hasNext = !result.equals(to);
        return result;
      }
    };
  }

  /**
   * For types with total order, captures details of how to traverse between
   * the given from and to values.
   *
   * It can be either horizontal (such as for {@link Column}, vertical
   * (such as for {@link Row}) or diagonal (for {@link Position}) axis.
   */
  @FunctionalInterface
  private interface Axis<T> {

    /**
     * @return Value in the represented total order, which immediately follows
     * the given of value, in the direction between the represented from
     * and to values, if such value exists for the represented type.
     */
    Optional<T> next(T of);

    /**
     * @return Axis which represents the direction between the given from and
     * to values in any total order.
     */
    static <T extends Comparable<T> & Navigable<T>> Axis<T> get(T from, T to) {
      if (from.compareTo(to) <= 0) {
        // left to right
        return Navigable::next;
      }
      else {
        // right to left
        return Navigable::prev;
      }
    }

    /**
     * @return Axis which represents the direction between the given from and
     * to positions.
     * @throws IllegalArgumentException if the given positions do not form
     * a proper range
     */
    static Axis<Position> get(Position from, Position to) {
      if (from.row() == to.row()) {
        return getHorizontal(from, to);
      }
      if (from.column() == to.column()) {
        return getVertical(from, to);
      }
      if (from.column().distanceTo(to.column()) == from.row().distanceTo(to.row())) {
        return getDiagonal(from, to);
      }
      throw new IllegalArgumentException(
        "Unsupported combination of positions: " + from + ", " + to + "."
      );
    }

    private static Axis<Position> getHorizontal(Position from, Position to) {
      if (from.row() != to.row()) {
        throw new IllegalArgumentException("Positions " + from + " and " + to + " don't form a horizontal range.");
      }

      if (from.column().compareTo(to.column()) <= 0) {
        // left to right
        return pos ->
          pos.column().next()
            .map(col -> Position.get(col, pos.row()));
      }
      else {
        // right to left
        return pos ->
          pos.column().prev()
            .map(col -> Position.get(col, pos.row()));
      }
    }

    private static Axis<Position> getVertical(Position from, Position to) {
      if (from.column() != to.column()) {
        throw new IllegalArgumentException("Positions " + from + " and " + to + " don't form a vertical range.");
      }

      if (from.row().compareTo(to.row()) <= 0) {
        // bottom to top
        return pos ->
          pos.row().next()
            .map(row -> Position.get(pos.column(), row));
      }
      else {
        // top to bottom
        return pos ->
          pos.row().prev()
            .map(row -> Position.get(pos.column(), row));
      }
    }

    private static Axis<Position> getDiagonal(Position from, Position to) {
      if (from.column().distanceTo(to.column()) != from.row().distanceTo(to.row())) {
        throw new IllegalArgumentException("Positions " + from + " and " + to + " don't form a diagonal range.");
      }

      if (from.column().compareTo(to.column()) <= 0) {
        if (from.row().compareTo(to.row()) <= 0) {
          // bottom left to top right
          return pos ->
            pos.row().next()
              .flatMap(row -> pos.column().next()
                .map(col -> Position.get(col, row)));
        }
        else {
          // top left to bottom right
          return pos ->
            pos.row().prev()
              .flatMap(row -> pos.column().next()
                .map(col -> Position.get(col, row)));
        }
      }
      else {
        if (from.row().compareTo(to.row()) <= 0) {
          // bottom right to top left
          return pos ->
            pos.row().next()
              .flatMap(row -> pos.column().prev()
                .map(col -> Position.get(col, row)));
        }
        else {
          // top right to bottom left
          return pos ->
            pos.row().prev()
              .flatMap(row -> pos.column().prev()
                .map(col -> Position.get(col, row)));
        }
      }
    }
  }
}
