package cz.dusanrychnovsky.chessendgames.core;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Streams {

  public static <T> Stream<T> stream(Iterable<T> items) {
    return StreamSupport.stream(items.spliterator(), false);
  }
}
