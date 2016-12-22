package cz.dusanrychnovsky.chessendgames.yaat;

import java.util.Optional;

public enum Column implements Navigable<Column> {
  CA, CB, CC, CD, CE, CF, CG, CH;

  @Override
  public Optional<Column> getPrevious() {
    if (ordinal() > 0) {
      return Optional.of(values()[ordinal() - 1]);
    }
    else {
      return Optional.empty();
    }
  }

  @Override
  public Optional<Column> getNext() {
    if (ordinal() < values().length - 1) {
      return Optional.of(values()[ordinal() + 1]);
    }
    return Optional.empty();
  }
}
