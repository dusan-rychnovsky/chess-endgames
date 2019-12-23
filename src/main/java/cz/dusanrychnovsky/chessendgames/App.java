package cz.dusanrychnovsky.chessendgames;

import java.io.*;

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

      out.write("Chess End Games v. 0.1\n\n");
      out.write("Enter Initial Situation:\n");
      out.flush();

      String line = in.readLine();
      Color color = Color.parse(line);

      Board.Builder builder = new Board.Builder();
      while ((line = in.readLine()) != null) {
        if (line.isEmpty()) {
          break;
        }

        Board.Entry entry = Board.Entry.parse(line);
        builder.add(entry.piece(), entry.position());
      }
      Board board = builder.build();

      out.write(color + "\n");
      out.write(board.print());
      out.flush();
    }
}
