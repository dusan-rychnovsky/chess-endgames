package cz.dusanrychnovsky.chessendgames;

import java.io.ByteArrayOutputStream;
import java.util.Map;
import cz.dusanrychnovsky.chessendgames.core.*;
import static cz.dusanrychnovsky.chessendgames.core.Color.*;
import static cz.dusanrychnovsky.chessendgames.core.PieceType.*;
import static cz.dusanrychnovsky.chessendgames.core.Position.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PrintingListenerTest {

  private final Piece WHITE_KING = new Piece(WHITE, KING);
  private final Piece BLACK_KING = new Piece(BLACK, KING);
  private final Piece WHITE_ROOK = new Piece(WHITE, ROOK);

  @Test
  public void onSituationChanged_printsSituation() {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintingListener listener = new PrintingListener(out);

    Situation situation = new Situation(BLACK, Map.of(F2, WHITE_KING, H3, WHITE_ROOK, H1, BLACK_KING));
    listener.onSituationChanged(situation);

    assertEquals(
      "Moves: BLACK\n" +
        "8 | . . . . . . . .\n" +
        "7 | . . . . . . . .\n" +
        "6 | . . . . . . . .\n" +
        "5 | . . . . . . . .\n" +
        "4 | . . . . . . . .\n" +
        "3 | . . . . . . . R\n" +
        "2 | . . . . . K . .\n" +
        "1 | . . . . . . . K\n" +
        "--|----------------\n" +
        "  | A B C D E F G H\n\n"
      ,
      out.toString()
    );
  }

  @Test
  public void onStatusChanged_win_printsStatus() {

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintingListener listener = new PrintingListener(out);

    listener.onStatusChanged(new Win(Color.WHITE));
    assertEquals("WIN WHITE\n", out.toString());
  }

  @Test
  public void onStatusChanged_draw_printsStatus() {

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintingListener listener = new PrintingListener(out);

    listener.onStatusChanged(new Draw());
    assertEquals("DRAW\n", out.toString());
  }

  @Test
  public void onStatusChanged_inprogress_noEffect() {

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintingListener listener = new PrintingListener(out);

    listener.onStatusChanged(new InProgress());
    assertEquals("", out.toString());
  }
}
