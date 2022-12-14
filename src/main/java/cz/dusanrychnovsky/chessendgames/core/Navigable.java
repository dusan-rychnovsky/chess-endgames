package cz.dusanrychnovsky.chessendgames.core;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public interface Navigable<T extends Navigable<T>> {

  int ord();
  T[] all();

  default Optional<T> prev() {
    if (ord() > 0) {
      return of(all()[ord() - 1]);
    }
    else {
      return empty();
    }
  }

  default Optional<T> next() {
    if (ord() < all().length - 1) {
      return of(all()[ord() + 1]);
    }
    else {
      return empty();
    }
  }

  default int distanceTo(T other) {
    return Math.abs(ord() - other.ord());
  }
}
