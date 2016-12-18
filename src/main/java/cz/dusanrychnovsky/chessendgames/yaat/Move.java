package cz.dusanrychnovsky.chessendgames.yaat;

import lombok.Value;

@Value
public class Move {

  Piece piece;
  Position from;
  Position to;
}
