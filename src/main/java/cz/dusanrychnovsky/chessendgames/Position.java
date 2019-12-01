package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.StringExtensions.capitalize;

public enum Position {
  F5, E6, D4, D3;

  public static Position parse(String value) {
    return valueOf(capitalize(value));
  }
}
