package cz.dusanrychnovsky.chessendgames;

import lombok.Value;
import lombok.experimental.Accessors;

@Value
@Accessors(fluent = true)
public class Move {

  Position from;
  Position to;

  public static Move parse(String value) {
    var tokens = value.split(" ");
    return new Move(Position.parse(tokens[0]), Position.parse(tokens[1]));
  }
}
