package cz.dusanrychnovsky.chessendgames.database;

public class TextSerializer {

  public static void main(String[] args) {
    System.out.println("Going to dump contents of movesdb database.");

    var serializer = new TextSerializer();
    var db = Database.load("/movesdb");

    System.out.println("DB size: " + db.moves().size());
    System.out.println(serializer.asString(db));
    System.out.println("DONE");
  }

  public String asString(Database db) {
    var sb = new StringBuilder();
    for (var entry : db.moves().entrySet()) {
      sb.append("Situation:\n");
      sb.append(entry.getKey().print());
      sb.append("Move: " + entry.getValue().from() + " " + entry.getValue().to() + "\n");
      sb.append("\n");
    }
    return sb.toString();
  }
}
