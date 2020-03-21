package cz.dusanrychnovsky.chessendgames;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.lang.String.join;
import static java.util.stream.Collectors.toList;

public class IterableExtensions {

  public static <T> T single(Iterable<T> values) {
    var it = values.iterator();
    var result = it.next();
    if (it.hasNext()) {
      throw new IllegalArgumentException("Iterable contains more than   one element: " + dump(values));
    }
    return result;
  }

  public static <T> String dump(Iterable<T> values) {
    return
      "[" +
      join(
        ",",
        stream(values)
          .map(Object::toString)
          .collect(toList())) +
      "]";
  }

  private static <T> Stream<T> stream(Iterable<T> values) {
    return StreamSupport.stream(values.spliterator(), false);
  }
}
