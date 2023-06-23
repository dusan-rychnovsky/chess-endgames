package cz.dusanrychnovsky.chessendgames.core;

/**
 * Represents a row on a chessboard - 1 to 8.
 */
public enum Row implements Navigable<Row> {
  R1, R2, R3, R4, R5, R6, R7, R8;

  @Override
  public int ord() {
    return ordinal();
  }

  @Override
  public Row[] all() {
    return values();
  }

  @Override
  public String toString() {
    return super.toString().substring(1, 2);
  }
}
