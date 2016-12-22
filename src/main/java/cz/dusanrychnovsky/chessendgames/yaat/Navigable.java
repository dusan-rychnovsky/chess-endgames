package cz.dusanrychnovsky.chessendgames.yaat;

import java.util.Optional;

public interface Navigable<T extends Navigable<T>> {
  Optional<T> getPrevious();
  Optional<T> getNext();
}
