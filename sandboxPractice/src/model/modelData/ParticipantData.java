package model.modelData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.rawData.Participant;
import model.rawData.RawData;

public class ParticipantData implements ModelData {

  int participantId;
  String name; // format: "<First> <Last>"
  int age;
  List<Integer> sessions; // list of session IDs, each int corresponding to a single session

  Map<Integer, SessionData> sessionsMap; // format: <sessionID>, <Session>

  public ParticipantData(int participantId, String name, int age, List<Integer> sessions) {
    this.participantId = participantId;
    this.name = name;
    this.age = age;
    this.sessions = sessions;
    this.sessionsMap = new HashMap<>();
  }

  // populates the sessions map given a list of sessions
  public void initSessions(ArrayList<SessionData> sessions) {
    for (SessionData sd : sessions) {
      int id = sd.sessionID;

      if (this.sessions.contains(id)) {
        sessionsMap.put(id, sd);
      }
    }
  }

  public ModelStatistic generateStatistics() {
    ArrayList<ModelLanguageStatistic> languageStatistics = this.generateLanguageStatistics();

    return new ModelStatistic(this.participantId, this.name, languageStatistics,
            this.averageRoundScore(), this.averageSessionDuration());
  }

  private ArrayList<ModelLanguageStatistic> generateLanguageStatistics() {
    ArrayList<ModelLanguageStatistic> stats = new ArrayList<>();

    for (SessionData sd : this.sessionsMap.values()) {
      ModelLanguageStatistic tempStat = sd.generateLanguageStatistic();
      stats = tempStat.mergeAdd(stats);
    }

    return stats;
  }

  public double averageRoundScore() {
    double totScore = 0;
    double totRounds = 0;

    for (SessionData sd : this.sessionsMap.values()) {
      totScore += sd.totalScore();
      totRounds += sd.rounds.size();
    }

    return (totScore / totRounds);
  }

  private double averageSessionDuration() {
    double tot = 0;
    int sessions = 0;

    for (SessionData sd : this.sessionsMap.values()) {
      tot += sd.getDuration();
      sessions++;
    }

    return tot / sessions;
  }

  @Override
  public ModelData toModelData() {
    return this;
  }

  @Override
  public RawData toRawData() {
    return new Participant(this.participantId, this.name, this.age, this.sessions);
  }

}
