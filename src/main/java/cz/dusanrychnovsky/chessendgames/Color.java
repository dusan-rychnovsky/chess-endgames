package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.StringExtensions.capitalize;

public enum Color {
  White, Black;

  public static Color parse(String color) {
    return valueOf(capitalize(color));
  }
}
