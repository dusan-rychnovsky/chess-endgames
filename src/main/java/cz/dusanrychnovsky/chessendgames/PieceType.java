package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.StringExtensions.capitalize;

public enum PieceType {
  King, Rook;

  public static PieceType parse(String value) {
    return valueOf(capitalize(value));
  }
}
