package cz.dusanrychnovsky.chessendgames;

import java.util.Optional;

public interface Axis<T> {
  Optional<T> next(T from);
}
