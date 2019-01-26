package cz.dusanrychnovsky.chessendgames.core;

import java.util.HashMap;
import java.util.Map;

public class Situation {
  private final Color player;
  private final Map<Position, Piece> pieces = new HashMap<>();

  public Situation(Color player, Map<Position, Piece> pieces) {
    this.player = player;
    this.pieces.putAll(pieces);
  }
}
