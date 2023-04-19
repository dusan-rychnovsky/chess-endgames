package cz.dusanrychnovsky.chessendgames.database;

import cz.dusanrychnovsky.chessendgames.core.Color;
import cz.dusanrychnovsky.chessendgames.core.Move;
import cz.dusanrychnovsky.chessendgames.core.Situation;
import cz.dusanrychnovsky.chessendgames.core.Situations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Stream;

import static cz.dusanrychnovsky.chessendgames.core.Color.WHITE;
import static cz.dusanrychnovsky.chessendgames.util.MapExtensions.*;
import static cz.dusanrychnovsky.chessendgames.util.TimeExtensions.printDuration;

public class Generator {

  private static final Logger LOGGER = LogManager.getLogger(Generator.class);

  private static final int INFINITE = Integer.MAX_VALUE;

  public static void main(String[] args) throws IOException {

    if (args.length == 0) {
      System.err.println("USAGE: [FILE-PATH] - file to store generated database into");
      System.exit(-1);
    }

    LOGGER.info("Going to generate DB.");

    var generator = new Generator();
    var db = generator.generateDatabase();
    LOGGER.debug("db size: {}", db.moves().size());

    var serializer = new ProtoSerializer();
    var proto = serializer.toProto(db);

    try (var os = new FileOutputStream(args[0])) {
      proto.writeTo(os);
    }

    LOGGER.info("DONE");
  }

  public Database generateDatabase() {

    var startTime = System.nanoTime();

    var allSituations = Situations.all();
    var totalSize = allSituations.size();
    LOGGER.debug("total situations: {}", totalSize);

    var color = WHITE;
    var acc = new HashMap<Situation, Record>();

    var iterationNo = 0;
    var totalResolved = 0;
    while (true) {
      LOGGER.info("Iteration no.: {}", (++iterationNo));

      var numResolved = runIteration(color, allSituations, acc);
      totalResolved += numResolved;

      LOGGER.debug("resolved: {}", numResolved);
      LOGGER.debug("total resolved: {}", totalResolved);
      LOGGER.debug("remaining: {}", (totalSize - totalResolved));

      if (numResolved == 0) {
        break;
      }
    }

    var result = new Database(
      mapValues(
        filter(
          filter(acc, entry -> entry.getValue().move != null),
          entry -> entry.getKey().color() == color),
        value -> value.move
      )
    );

    var finishTime = System.nanoTime();
    var elapsedTime = finishTime - startTime;
    LOGGER.debug("elapsed time : {}", () -> printDuration(elapsedTime));

    return result;
  }

  private int runIteration(Color color, Iterable<Situation> situations, HashMap<Situation, Record> acc) {
    var numResolved = 0;
    var it = situations.iterator();
    while (it.hasNext()) {
      var situation = it.next();
      if (tryResolveSituation(color, situation, acc)) {
        it.remove();
        numResolved++;
      }
    }
    return numResolved;
  }

  private boolean tryResolveSituation(Color color, Situation situation, HashMap<Situation, Record> acc) {

    var status = situation.status();
    if (status.isFinal()) {
      var score = INFINITE;
      if (status.isWin(color)) {
        score = 0;
      }
      acc.put(situation, new Record(score, null));
      return true;
    }

    var nextSituations = situation.nextMoves();
    var nextRanks = nextSituations.entrySet().stream()
      .map(entry ->
        get(acc, entry.getValue())
          .map(rec -> new Record(rec.numMoves, entry.getKey()))
      );

    var result = Optional.<Record>empty();
    if (situation.color() == color) {
      result = selectMin(nextRanks);
    }
    else {
      result = selectMax(nextRanks);
    }

    if (result.isPresent()) {
      acc.put(situation, new Record(inc(result.get().numMoves), result.get().move));
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
  private Optional<Record> selectMin(Stream<Optional<Record>> records) {
    // if there already is a neighbour situation with a finite distance,
    // it's safe to ignore any remaining unmarked neighbours - they can't
    // have a lower distance (the algorithm progresses through iterations
    // of increasing distance)
    return records.reduce(Optional.empty(), (acc, rec) -> {
      if (rec.isPresent()) {
        var numMoves = rec.get().numMoves;
        if (numMoves != INFINITE && (acc.isEmpty() || numMoves < acc.get().numMoves)) {
          return rec;
        }
      }
      return acc;
    });
  }

  /*
   * Strategy for the defending player - select moves to maximize the number
   * of rounds required to end the match.
   */
  private Optional<Record> selectMax(Stream<Optional<Record>> records) {
    var result = Optional.<Record>empty();
    for (var rec : records.toList()) {
      if (rec.isPresent()) {
        var numMoves = rec.get().numMoves;
        if (result.isEmpty() || numMoves > result.get().numMoves) {
          result = rec;
        }
      }
      else {
        // maximum distance is only known when distance of all neighbours is known
        return Optional.empty();
      }
    }
    return result;
  }

  private record Record (int numMoves, Move move) {
  }
}
