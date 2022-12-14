package cz.dusanrychnovsky.chessendgames.database;

import cz.dusanrychnovsky.chessendgames.core.*;
import org.junit.Test;

import java.util.Map;

import static cz.dusanrychnovsky.chessendgames.core.Color.*;
import static cz.dusanrychnovsky.chessendgames.core.Piece.*;
import static cz.dusanrychnovsky.chessendgames.core.Position.*;
import static org.junit.Assert.assertTrue;

public class TextSerializerTest {

  private static final TextSerializer SERIALIZER = new TextSerializer();

  @Test
  public void asStringShouldSerializeDbToString() {
    var db = new Database(Map.of(
      new Situation(
        WHITE,
        Board.builder()
          .add(WHITE_KING, C3)
          .add(WHITE_ROOK, C4)
          .add(BLACK_KING, H1)
          .build()
      ),
      new Move(C3, D3),
      new Situation(
        BLACK,
        Board.builder()
          .add(WHITE_KING, A1)
          .add(BLACK_KING, H8)
          .build()
      ),
      new Move(H8, H7)
    ));

    var result = SERIALIZER.asString(db);
    assertTrue(
      result.contains(
        """
          Situation:
          BLACK
          8 | . . . . . . . K
          7 | . . . . . . . .
          6 | . . . . . . . .
          5 | . . . . . . . .
          4 | . . . . . . . .
          3 | . . . . . . . .
          2 | . . . . . . . .
          1 | K . . . . . . .
          --|----------------
            | A B C D E F G H
          Move: H8 H7

          """
      )
    );
    assertTrue(
      result.contains(
        """
          Situation:
          WHITE
          8 | . . . . . . . .
          7 | . . . . . . . .
          6 | . . . . . . . .
          5 | . . . . . . . .
          4 | . . R . . . . .
          3 | . . K . . . . .
          2 | . . . . . . . .
          1 | . . . . . . . K
          --|----------------
            | A B C D E F G H
          Move: C3 D3

          """
      )
    );
  }
}
