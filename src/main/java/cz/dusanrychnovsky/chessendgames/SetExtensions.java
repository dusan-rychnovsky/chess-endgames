package cz.dusanrychnovsky.chessendgames;

import java.util.Set;

public class SetExtensions {
  public static <T> T single(Set<T> values) {
    if (values.size() != 1) {
      throw new IllegalArgumentException("Unexpected set size: " + values.size());
    }
    return values.iterator().next();
  }
}
