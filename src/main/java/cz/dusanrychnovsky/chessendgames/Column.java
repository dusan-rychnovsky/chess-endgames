package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.StringExtensions.capitalize;

public enum Column {
  CA, CB, CC, CD, CE, CF, CG, CH;

  public static Column parse(String value) {
    return valueOf("C" + capitalize(value));
  }
}
