package cz.dusanrychnovsky.chessendgames.core;

import java.util.Iterator;
import java.util.Optional;

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

  public Range(T from, T to, Axis<T> axis) {
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
        var result = curr;
        curr = axis.next(result).orElse(null);
        hasNext = !result.equals(to);
        return result;
      }
    };
  }

  @FunctionalInterface
  private interface Axis<T> {
    Optional<T> next(T from);

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

    static Axis<Position> get(Position from, Position to) {
      if (from.row() == to.row()) {
        // horizontal
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
      if (from.column() == to.column()) {
        // vertical
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
      if (from.column().distanceTo(to.column()) == from.row().distanceTo(to.row())) {
        // diagonal
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
      throw new IllegalArgumentException(
        "Unsupported combination of positions: " + from + ", " + to + "."
      );
    }
  }
}
