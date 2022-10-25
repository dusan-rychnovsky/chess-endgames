package cz.dusanrychnovsky.chessendgames;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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

  public static <K, V> Optional<V> get(Map<K, V> map, K key) {
    return Optional.ofNullable(map.get(key));
  }
}
