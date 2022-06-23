package model.userData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.userStatistic.LanguageStatistic;

/**
 * Class to represent a session, which is associated with exactly one participant
 * (identified by their {@code participantID} and has a unique {@code sessionID}.
 * Each session is in a particular {@code language} and can have any number of {@code rounds}
 * between 0 and 8 (inclusive). Each session also has a {@code startTime} and {@code endTime}
 * to represent the time spent by the participant in this particular session.
 */
public class Session implements UserData {
  int participantID, sessionID;
  String language;
  List<Integer> rounds; // list of roundIDs, each corresponding to a specific round in the session
  long startTime, endTime;

  Map<Integer, Round> roundsMap;


  public Session(int participantID, int sessionID, String language,
                 List<Integer> rounds, long startTime, long endTime) {
    this.participantID = participantID;
    this.sessionID = sessionID;
    this.language = language;
    this.rounds = rounds;
    this.startTime = startTime;
    this.endTime = endTime;
    this.roundsMap = new HashMap<>();
  }

  public void initRounds(Round[] rounds) {
    for (Round r : rounds) {
      int id = r.getID();

      if (this.rounds.contains(id)) {
        roundsMap.put(id, r);
      }
    }
  }

  @Override
  public int getID() {
    int id = this.sessionID;
    return id;
  }

  @Override
  public double getAverageRoundScore() {
    double score = 0;
    int rounds = 0;

    for (Round r : this.roundsMap.values()) {
      score += r.getAverageRoundScore();
      rounds++;
    }

    return score / rounds;
  }

  public double getDuration() {

    return this.endTime - this.startTime;
  }

  public String getLanguage() {
    return this.language;
  }

  public LanguageStatistic generateLanguageStatistic() {

    return new LanguageStatistic(this.language, this.getAverageRoundScore(), this.getDuration());
  }

}
