package cz.dusanrychnovsky.chessendgames;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class MapExtensions {

  public static <K, V> Map<K, V> filterByKey(Map<K, V> map, Predicate<K> predicate) {
    return filter(map, entry -> predicate.test(entry.getKey()));
  }

  public static <K, V> Map<K, V> filter(Map<K, V> map, Predicate<Map.Entry<K, V>> predicate) {
    var result = new HashMap<K, V>();
    for (var entry : map.entrySet()) {
      if (predicate.test(entry)) {
        result.put(entry.getKey(), entry.getValue());
      }
    }
    return result;
  }

  public static <K, V1, V2> Map<K, V2> mapValues(Map<K, V1> map, Function<V1, V2> mapper) {
    var result = new HashMap<K, V2>();
    for (var entry : map.entrySet()) {
      var newValue = mapper.apply(entry.getValue());
      result.put(entry.getKey(), newValue);
    }
    return result;
  }

  public static <K, V> Optional<V> get(Map<K, V> map, K key) {
    return Optional.ofNullable(map.get(key));
  }
}
