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
  public void next_appliesMove() {
    var move = new Move(D3, D4);
    var situation = new Situation(
      White,
      Board.builder()
        .add(WhiteKing, D3)
        .add(BlackKing, F5)
        .add(BlackRook, E6)
        .build());

    situation = situation.next(move);

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
