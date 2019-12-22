package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.StringExtensions.capitalize;

public enum Row implements Navigable<Row> {
  R1, R2, R3, R4, R5, R6, R7, R8;

  public static Row parse(String value) {
    return valueOf("R" + capitalize(value));
  }

  @Override
  public int ord() {
    return ordinal();
  }

  @Override
  public Row[] all() {
    return values();
  }
}
