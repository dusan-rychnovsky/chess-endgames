package cz.dusanrychnovsky.chessendgames;

import java.util.Optional;

public interface Axis<T> {
  Optional<T> next(T from);

  static <T extends Navigable> Axis<T> left() {
    return Navigable::prev;
  }

  static <T extends Navigable> Axis<T> right() {
    return Navigable::next;
  }
}
