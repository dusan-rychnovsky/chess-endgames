package cz.dusanrychnovsky.chessendgames.core;

import lombok.EqualsAndHashCode;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static cz.dusanrychnovsky.chessendgames.core.Status.IN_PROGRESS;
import static java.util.Collections.unmodifiableMap;
import static java.util.Optional.ofNullable;

@EqualsAndHashCode
public class Situation {
  private final Color player;
  private final Map<Position, Piece> pieces = new HashMap<>();

  public Situation(Color player, Map<Position, Piece> pieces) {
    this.player = player;
    this.pieces.putAll(pieces);
  }

  public Color getPlayer() {
    return player;
  }

  public Map<Position, Piece> getPieces() {
    return unmodifiableMap(this.pieces);
  }

  public Status getStatus() {
    return IN_PROGRESS;
  }

  public Situation apply(Move move) {
    Position from = move.getFrom();
    checkArgument(pieces.containsKey(from));
    checkArgument(pieces.get(from).getColor().equals(player));

    Situation result = new Situation(player.getOpponent(), pieces);
    swap(result.pieces, from, move.getTo());
    return result;
  }

  private void swap(Map<Position,Piece> pieces, Position from, Position to) {
    Piece piece = pieces.get(from);
    pieces.remove(from);
    pieces.put(to, piece);
  }

  public Optional<Piece> getPiece(Position pos) {
    return ofNullable(pieces.get(pos));
  }
}
