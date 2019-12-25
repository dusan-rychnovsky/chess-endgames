package cz.dusanrychnovsky.chessendgames;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static cz.dusanrychnovsky.chessendgames.Color.*;
import static cz.dusanrychnovsky.chessendgames.Piece.*;
import static cz.dusanrychnovsky.chessendgames.Position.*;

public class SituationTest {
  @Test
  @Ignore
  public void next_appliesCurrentPlayersMove() {
    var situation = new Situation(
      new ScriptedPlayer(White, new Move(D3, D4)),
      new ScriptedPlayer(Black),
      Board.builder()
        .add(WhiteKing, D3)
        .add(BlackKing, F5)
        .add(BlackRook, E6)
        .build());

    situation = situation.next();

    Assert.assertEquals(
      Board.builder()
        .add(WhiteKing, D4)
        .add(BlackKing, F5)
        .add(BlackRook, E6)
        .build(),
      situation.board()
    );
  }
}
