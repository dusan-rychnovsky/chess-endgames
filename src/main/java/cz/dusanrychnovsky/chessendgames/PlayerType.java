package cz.dusanrychnovsky.chessendgames;

public enum PlayerType {

  STDIN,
  DB;

  public static PlayerType parse(String value) {
    return valueOf(value.toUpperCase());
  }
}
