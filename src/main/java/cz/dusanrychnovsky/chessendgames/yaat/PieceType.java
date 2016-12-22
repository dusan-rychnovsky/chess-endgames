package cz.dusanrychnovsky.chessendgames.yaat;

public interface PieceType {
  Iterable<Move> listAllMovesFromPosition(Position pos);
}
