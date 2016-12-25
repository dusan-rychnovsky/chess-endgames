package cz.dusanrychnovsky.chessendgames.core;

public interface PieceType {
  Iterable<Move> listAllMovesFromPosition(Position pos);
}
