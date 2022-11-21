package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.EnumExtensions.parseEnum;

public record Move (Position from, Position to) {

  public static Move parseMove(String value) {
    var tokens = value.split(" ");
    return new Move(parseEnum(tokens[0], Position.class), parseEnum(tokens[1], Position.class));
  }
}
