package cz.dusanrychnovsky.chessendgames;

public class StringExtensions {
  public static String capitalize(String value) {
    return value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
  }
}
