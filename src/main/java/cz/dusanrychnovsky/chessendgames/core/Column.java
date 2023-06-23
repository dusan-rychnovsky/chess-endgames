package cz.dusanrychnovsky.chessendgames.core;

/**
 * Represents a column on a chessboard - A to H.
 */
public enum Column implements Navigable<Column> {
  CA, CB, CC, CD, CE, CF, CG, CH;

  @Override
  public int ord() {
    return ordinal();
  }

  @Override
  public Column[] all() {
    return values();
  }

  @Override
  public String toString() {
    return super.toString().substring(1, 2);
  }
}
