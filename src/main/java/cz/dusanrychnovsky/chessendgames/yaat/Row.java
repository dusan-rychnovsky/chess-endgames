package cz.dusanrychnovsky.chessendgames.yaat;

import java.util.Optional;

public enum Row implements Navigable<Row> {
  R1, R2, R3, R4, R5, R6, R7, R8;

  public Optional<Row> getPrevious() {
    if (ordinal() > 0) {
      return Optional.of(values()[ordinal() - 1]);
    }
    else {
      return Optional.empty();
    }
  }

  public Optional<Row> getNext() {
    if (ordinal() < values().length - 1) {
      return Optional.of(values()[ordinal() + 1]);
    }
    return Optional.empty();
  }
}
