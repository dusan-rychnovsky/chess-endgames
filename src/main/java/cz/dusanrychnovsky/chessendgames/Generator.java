package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.proto.Movesdb;
import lombok.Value;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import static cz.dusanrychnovsky.chessendgames.IterableExtensions.map;
import static cz.dusanrychnovsky.chessendgames.IterableExtensions.size;
import static cz.dusanrychnovsky.chessendgames.MapExtensions.*;

public class Generator {

  private static final int INFINITE = Integer.MAX_VALUE;

  public static void main(String[] args) throws IOException {

    if (args.length == 0) {
      System.err.println("USAGE: [FILE-PATH] - file to store generated database into");
      System.exit(-1);
    }

    System.out.println("Going to generate DB.");

    var generator = new Generator();
    var db = generator.generate();

    System.out.println("DB size: " + db.getValuesCount());

    var os = new FileOutputStream(args[0]);
    db.writeTo(os);

    System.out.println("DONE");
  }

  public Movesdb.Database generate() {

    var situations = Situations.all();
    var size = size(situations);
    System.out.println("Total situations: " + size);

    var color = Color.White;
    var acc = new HashMap<Situation, Record>();

    var round = 0;
    var totalResolved = 0;
    while (true) {
      System.out.println("Round no.: " + (++round));

      var numResolved = walkThrough(color, situations, acc);
      totalResolved += numResolved;

      System.out.println("Resolved: " + numResolved);
      System.out.println("Total resolved: " + totalResolved);
      System.out.println("Acc size: " + acc.size());
      System.out.println("Remaining: " + size(situations));

      if (numResolved == 0) {
        break;
      }
    }

    var serializer = new Serializer();
    var db = new Database(mapValues(
      filter(
        filter(acc, entry -> entry.getValue().move != null),
        entry -> entry.getKey().color() == color),
      value -> value.move));
    return serializer.toProto(db);
  }

  private int walkThrough(Color color, Iterable<Situation> situations, HashMap<Situation, Record> acc) {
    var numResolved = 0;
    var it = situations.iterator();
    while (it.hasNext()) {
      var situation = it.next();
      if (resolve(color, situation, acc)) {
        it.remove();
        numResolved++;
      }
    }
    return numResolved;
  }

  private boolean resolve(Color color, Situation situation, HashMap<Situation, Record> acc) {

    var currColor = situation.color();
    var status = situation.status();
    if (status.isFinal()) {
      var score = INFINITE;
      if (status.isWin(currColor)) {
        score = 0;
      }
      acc.put(situation, new Record(score, null));
      return true;
    }

    var situations = situation.next();
    var ranks = map(situations.entrySet(), entry ->
      get(acc, entry.getValue())
        .map(rec -> new Record(rec.numMoves, entry.getKey())));

    var record = Optional.<Record>empty();
    if (situation.color() == color) {
      record = selectMin(ranks);
    }
    else {
      record = selectMax(ranks);
    }

    if (record.isPresent()) {
      acc.put(situation, new Record(inc(record.get().numMoves), record.get().move));
      return true;
    }
    else {
      return false;
    }
  }

  private static int inc(int numMoves) {
    if (numMoves != INFINITE) {
      return numMoves + 1;
    }
    else {
      return numMoves;
    }
  }

  /*
   * Strategy for the attacking player - select moves to minimize the number
   * of rounds required to win the match.
   */
  private Optional<Record> selectMin(Iterable<Optional<Record>> records) {
    // if there already is a neighbour situation with a finite distance,
    // it's safe to ignore any remaining unmarked neighbours - they can't
    // have a lower distance (the algorithm progresses through iterations
    // of increasing distance)
    var result = Optional.<Record>empty();
    for (var record : records) {
      if (record.isPresent()) {
        var numMoves = record.get().numMoves;
        if (result.isEmpty() || numMoves < result.get().numMoves) {
          result = record;
        }
      }
    }
    return result;
  }

  /*
   * Strategy for the defending player - select moves to maximize the number
   * of rounds required to end the match.
   */
  private Optional<Record> selectMax(Iterable<Optional<Record>> records) {
    var result = Optional.<Record>empty();
    for (var record : records) {
      if (record.isPresent()) {
        var numMoves = record.get().numMoves;
        if (result.isEmpty() || numMoves > result.get().numMoves) {
          result = record;
        }
      }
      else {
        // maximum distance is only known when distance of all neighbours is known
        return Optional.empty();
      }
    }
    return result;
  }

  @Value
  private static class Record {
    int numMoves;
    Move move;
  }
}
