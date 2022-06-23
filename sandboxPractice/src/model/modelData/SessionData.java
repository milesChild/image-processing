package model.modelData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.rawData.RawData;
import model.rawData.Session;

public class SessionData implements ModelData {

  int participantID, sessionID;
  String language;
  List<Integer> rounds; // list of roundIDs, each corresponding to a specific round in the session
  long startTime, endTime;

  Map<Integer, RoundData> roundsMap;

  public SessionData(int participantID, int sessionID, String language,
                 List<Integer> rounds, long startTime, long endTime) {
    this.participantID = participantID;
    this.sessionID = sessionID;
    this.language = language;
    this.rounds = rounds;
    this.startTime = startTime;
    this.endTime = endTime;
    this.roundsMap = new HashMap<>();
  }

  public void initRounds(ArrayList<RoundData> rounds) {
    for (RoundData rd : rounds) {
      int id = rd.roundId;

      if (this.rounds.contains(id)) {
        roundsMap.put(id, rd);
      }
    }
  }

  public double totalScore() {
    double tot = 0;

    for (RoundData rd : this.roundsMap.values()) {
      tot += rd.score;
    }

    return tot;
  }

  public double getDuration() {
    return this.endTime - this.startTime;
  }

  public ModelLanguageStatistic generateLanguageStatistic() {

    return new ModelLanguageStatistic(this.language, this.totalScore(),
            this.getDuration(), this.rounds.size());
  }

  @Override
  public ModelData toModelData() {
    return this;
  }

  @Override
  public RawData toRawData() {
    return new Session(this.participantID, this.sessionID, this.language, this.rounds,
            this.startTime, this.endTime);
  }

}
