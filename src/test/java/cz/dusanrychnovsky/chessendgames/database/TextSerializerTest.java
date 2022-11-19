package cz.dusanrychnovsky.chessendgames.database;

import cz.dusanrychnovsky.chessendgames.*;
import org.junit.Test;

import java.util.Map;

import static cz.dusanrychnovsky.chessendgames.Color.*;
import static cz.dusanrychnovsky.chessendgames.Piece.*;
import static cz.dusanrychnovsky.chessendgames.Position.*;
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
        "Situation:\n" +
        "BLACK\n" +
        "8 | . . . . . . . K\n" +
        "7 | . . . . . . . .\n" +
        "6 | . . . . . . . .\n" +
        "5 | . . . . . . . .\n" +
        "4 | . . . . . . . .\n" +
        "3 | . . . . . . . .\n" +
        "2 | . . . . . . . .\n" +
        "1 | K . . . . . . .\n" +
        "--|----------------\n" +
        "  | A B C D E F G H\n" +
        "Move: H8 H7\n" +
        "\n"
      )
    );
    assertTrue(
      result.contains(
        "Situation:\n" +
        "WHITE\n" +
        "8 | . . . . . . . .\n" +
        "7 | . . . . . . . .\n" +
        "6 | . . . . . . . .\n" +
        "5 | . . . . . . . .\n" +
        "4 | . . R . . . . .\n" +
        "3 | . . K . . . . .\n" +
        "2 | . . . . . . . .\n" +
        "1 | . . . . . . . K\n" +
        "--|----------------\n" +
        "  | A B C D E F G H\n" +
        "Move: C3 D3\n" +
        "\n"
      )
    );
  }
}
