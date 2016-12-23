package cz.dusanrychnovsky.chessendgames;

public interface PieceType {
  Iterable<Move> listAllMovesFromPosition(Position pos);
}
