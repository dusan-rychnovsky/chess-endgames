package cz.dusanrychnovsky.chessendgames;

import lombok.Value;

@Value
public class Move {
  Position from;
  Position to;
}