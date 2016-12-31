package cz.dusanrychnovsky.chessendgames.core;

import java.io.Serializable;

public enum Color implements Serializable {
  WHITE, BLACK;

  public Color getOpponentColor() {
    if (this == WHITE) {
      return BLACK;
    }
    else {
      return WHITE;
    }
  }
}
