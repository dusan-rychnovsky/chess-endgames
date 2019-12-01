package cz.dusanrychnovsky.chessendgames;

public enum Color {
  White, Black;

  public static Color parse(String color) {
    return valueOf(capitalize(color));
  }

  private static String capitalize(String value) {
    return value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
  }
}
