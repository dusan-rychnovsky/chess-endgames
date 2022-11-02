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

      var players = Map.of(
        Color.White, new StdInPlayer(Color.White, in, out),
        Color.Black, new StdInPlayer(Color.Black, in, out)
      );

      out.write("Chess End Games v. 0.1\n\n");
      out.write("Enter Initial Situation:\n");
      out.flush();

      var line = in.readLine();
      var color = Color.parse(line);

      var builder = new Board.Builder();
      while ((line = in.readLine()) != null) {
        if (line.isEmpty()) {
          break;
        }

        var entry = Board.Entry.parse(line);
        builder.add(entry.piece(), entry.position());
      }
      var board = builder.build();
      var situation = new Situation(color, board);

      out.write(situation.print());
      out.flush();

      var status = situation.status();
      while (status.equals(InProgress)) {
        System.out.println(situation.print());
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
