package cz.dusanrychnovsky.chessendgames.core;

import lombok.Value;

@Value
public class Win implements Status {
  Color player;
}
