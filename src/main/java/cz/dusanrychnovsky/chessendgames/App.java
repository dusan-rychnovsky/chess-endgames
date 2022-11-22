package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.database.DbPlayer;

import static cz.dusanrychnovsky.chessendgames.Color.*;
import static cz.dusanrychnovsky.chessendgames.EnumExtensions.parseEnum;
import static cz.dusanrychnovsky.chessendgames.PlayerType.*;
import static cz.dusanrychnovsky.chessendgames.Status.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class App
{
  public static void main(String[] args) throws IOException {
    var reader = reader(System.in);
    var writer = writer(System.out);
    var playerDb = Map.of(
      STDIN, new StdInPlayer(reader, writer),
      DB, new DbPlayer("/movesdb")
    );
    run(reader, writer, playerDb);
  }

  private static BufferedReader reader(InputStream in) {
    return new BufferedReader(new InputStreamReader(in));
  }

  private static BufferedWriter writer(OutputStream out) {
    return new BufferedWriter(new OutputStreamWriter(out));
  }

  public static void run(BufferedReader in, BufferedWriter out, Map<PlayerType, Player> playerDb)
    throws IOException {

    printTitle(out);
    var players = queryPlayers(in, out, playerDb);
    var color = queryCurrentColor(in, out);
    var situation = queryInitialSituation(in, out, color);
    var result = gameLoop(out, situation, players);
    printResult(out, result);
  }

  private static void printTitle(BufferedWriter out) throws IOException {
    out.write("CHESS END GAMES v. 0.1\n\n");
  }

  private static Map<Color, Player> queryPlayers(BufferedReader in, BufferedWriter out, Map<PlayerType, Player> playerDb)
    throws IOException {

    var result = new HashMap<Color, Player>();

    out.write("Choose players.\n");
    out.write("[a] StdIn - command line interface\n");
    out.write("[b] Db - computer algorithm\n\n");

    out.write("White player:\n");
    out.flush();

    var line = in.readLine();
    result.put(WHITE, playerDb.get(parseEnum(line, PlayerType.class)));

    out.write("\nBlack player:\n");
    out.flush();

    line = in.readLine();
    result.put(BLACK, playerDb.get(parseEnum(line, PlayerType.class)));

    return result;
  }

  private static Color queryCurrentColor(BufferedReader in, BufferedWriter out) throws IOException {
    out.write("\nEnter current player's color (White/Black):\n");
    out.flush();
    var line = in.readLine();
    return parseEnum(line, Color.class);
  }

  private static Situation queryInitialSituation(BufferedReader in, BufferedWriter out, Color color)
    throws IOException {

    out.write("\nNow enter positions of all pieces on the board.\n");
    out.write(". One piece per line.\n");
    out.write(". Format: \"[Color] [Type] [Position]\".\n");
    out.write(". Example: \"White King A1\".\n");
    out.write(". Finish with an empty line.\n\n");

    out.write("Enter first piece:\n");
    out.flush();

    var builder = new Board.Builder();

    var line = "";
    while ((line = in.readLine()) != null) {
      if (line.isEmpty()) {
        break;
      }

      var entry = Board.Entry.parseEntry(line);
      builder.add(entry.piece(), entry.position());

      out.write("\nEnter next piece:\n");
      out.flush();
    }

    var board = builder.build();
    return new Situation(color, board);
  }

  private static Status gameLoop(BufferedWriter out, Situation situation, Map<Color, Player> players)
    throws IOException {

    out.write(situation.print());
    out.flush();

    var status = situation.status();
    while (status.equals(IN_PROGRESS)) {
      var color = situation.color();

      var move = players.get(color).getMove(situation);
      situation = situation.apply(move);
      status = situation.status();

      out.write(situation.print());
      out.flush();
    }

    return status;
  }

  private static void printResult(BufferedWriter out, Status result) throws IOException {
    out.write(result.print() + "\n");
    out.flush();
  }
}
