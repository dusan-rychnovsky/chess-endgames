package cz.dusanrychnovsky.chessendgames;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static cz.dusanrychnovsky.chessendgames.Color.*;
import static cz.dusanrychnovsky.chessendgames.PieceType.*;
import static cz.dusanrychnovsky.chessendgames.Position.*;

public class SituationTest {
  @Test
  @Ignore
  public void next_appliesCurrentPlayersMove() {
    Piece whiteKing = new Piece(White, King);
    Piece blackKing = new Piece(Black, King);
    Piece blackRook = new Piece(Black, Rook);
    Situation situation = new Situation(
      new ScriptedPlayer(White, new Move(D3, D4)),
      new ScriptedPlayer(Black),
      Board.builder()
        .add(whiteKing, D3)
        .add(blackKing, F5)
        .add(blackRook, E6)
        .build());

    situation = situation.next();

    Assert.assertEquals(
      Board.builder()
        .add(whiteKing, D4)
        .add(blackKing, F5)
        .add(blackRook, E6)
        .build(),
      situation.board()
    );
  }
}
