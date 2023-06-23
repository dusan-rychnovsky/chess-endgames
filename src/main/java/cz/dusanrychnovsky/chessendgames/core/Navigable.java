package cz.dusanrychnovsky.chessendgames.core;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

/**
 * A trait. For types with a total order, implements functionality of moving
 * to the next/previous value.
 */
public interface Navigable<T extends Navigable<T>> {

  /**
   * @return The position of the represented value within the represented total
   * ordering.
   */
  int ord();

  /**
   * @return A list of all values of the represented type.
   */
  T[] all();

  /**
   * @return Next value of the represented type within the represented total
   * ordering.
   */
  default Optional<T> prev() {
    if (ord() > 0) {
      return of(all()[ord() - 1]);
    }
    else {
      return empty();
    }
  }

  /**
   * @return Previous value of the represented type within the represented total
   * ordering.
   */
  default Optional<T> next() {
    if (ord() < all().length - 1) {
      return of(all()[ord() + 1]);
    }
    else {
      return empty();
    }
  }

  /**
   * @return Distance (as in, number of values) between the represented and
   * the given value within the represented total ordering.
   */
  default int distanceTo(T other) {
    return Math.abs(ord() - other.ord());
  }
}
