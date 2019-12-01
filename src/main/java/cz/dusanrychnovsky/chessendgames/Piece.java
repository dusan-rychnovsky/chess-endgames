package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.StringExtensions.capitalize;

public enum Piece {
  King, Rook;

  public static Piece parse(String value) {
    return valueOf(capitalize(value));
  }
}
