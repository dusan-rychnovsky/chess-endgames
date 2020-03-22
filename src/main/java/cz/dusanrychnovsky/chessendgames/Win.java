package cz.dusanrychnovsky.chessendgames;

import lombok.Value;

@Value
public class Win implements Status {
  Color color;
}
