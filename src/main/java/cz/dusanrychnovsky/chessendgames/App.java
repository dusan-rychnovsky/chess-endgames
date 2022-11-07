package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.Status.*;

import java.io.*;
import java.util.Map;

public class App
{
    public static void main(String[] args) throws IOException {
      run(reader(System.in), writer(System.out));
    }

    private static BufferedReader reader(InputStream in) {
      return new BufferedReader(new InputStreamReader(in));
    }

    private static BufferedWriter writer(OutputStream out) {
      return new BufferedWriter(new OutputStreamWriter(out));
    }

    public static void run(BufferedReader in, BufferedWriter out) throws IOException {

      var player = new StdInPlayer(in, out);
      var players = Map.of(Color.White, player, Color.Black, player);

      out.write("CHESS END GAMES v. 0.1\n\n");

      out.write("Enter current player's color (White/Black):\n");
      out.flush();

      var line = in.readLine();
      var color = Color.parse(line);

      out.write("\nNow enter positions of all pieces on the board.\n");
      out.write(". One piece per line.\n");
      out.write(". Format: \"[Color] [Type] [Position]\".\n");
      out.write(". Example: \"White King A1\".\n");
      out.write(". Finish with an empty line.\n\n");

      out.write("Enter first piece:\n");
      out.flush();

      var builder = new Board.Builder();
      while ((line = in.readLine()) != null) {
        if (line.isEmpty()) {
          break;
        }

        var entry = Board.Entry.parse(line);
        builder.add(entry.piece(), entry.position());

        out.write("\nEnter next piece:\n");
        out.flush();
      }

      var board = builder.build();
      var situation = new Situation(color, board);

      out.write(situation.print());
      out.flush();

      var status = situation.status();
      while (status.equals(InProgress)) {
        color = situation.color();

        var move = players.get(color).getMove(situation);
        situation = situation.move(move);
        status = situation.status();

        out.write(situation.print());
        out.flush();
      }

      out.write(status.print() + "\n");
      out.flush();
    }
}
