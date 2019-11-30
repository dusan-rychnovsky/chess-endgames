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
    Situation situation = new Situation(
      new ScriptedPlayer(White, new Move(D3, D4)),
      new ScriptedPlayer(Black),
      Board.builder()
        .add(White, King, D3)
        .add(Black, King, F5)
        .add(Black, Rook, E6)
        .build());

    situation = situation.next();

    Assert.assertEquals(
      Board.builder()
        .add(White, King, D4)
        .add(Black, King, F5)
        .add(Black, Rook, E6)
        .build(),
      situation.board()
    );
  }
}
