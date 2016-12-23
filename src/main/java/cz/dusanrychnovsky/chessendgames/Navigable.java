package cz.dusanrychnovsky.chessendgames;

import java.util.Optional;

public interface Navigable<T extends Navigable<T>> {
  
  int getOrdinal();
  T[] getValues();
  
  default Optional<T> getPrevious() {
    if (getOrdinal() > 0) {
      return Optional.of(getValues()[getOrdinal() - 1]);
    }
    else {
      return Optional.empty();
    }
  }
  
  default Optional<T> getNext() {
    if (getOrdinal() < getValues().length - 1) {
      return Optional.of(getValues()[getOrdinal() + 1]);
    }
    return Optional.empty();
  }
  
  default int getDistance(T other) {
    return Math.abs(getOrdinal() - other.getOrdinal());
  }
}
