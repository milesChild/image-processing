package model.userData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.userStatistic.LanguageStatistic;
import model.userStatistic.Statistic;
import model.userStatistic.UserStatistic;

public class Participant implements UserData {
  int participantId;
  String name; // format: "<First> <Last>"
  int age;
  List<Integer> sessions; // list of session IDs, each int corresponding to a single session

  Map<Integer, Session> sessionsMap; // format: <sessionID>, <Session>

  public Participant(int participantId, String name, int age, List<Integer> sessions) {
    this.participantId = participantId;
    this.name = name;
    this.age = age;
    this.sessions = sessions;
    this.sessionsMap = new HashMap<>();
  }

  // populates the sessions map given a list of sessions
  public void initSessions(Session[] sessions) {
    for (Session s : sessions) {
      int id = s.getID();

      if (this.sessions.contains(id)) {
        sessionsMap.put(id, s);
      }
    }
  }

  public Statistic getStats() {
    int id = this.participantId;
    String name = this.name;
    List<LanguageStatistic> languages = this.languageStats();
    double averageRoundScore = this.getAverageRoundScore();
    double averageSessionDuration = this.getAverageSessionDuration();

    return new UserStatistic(id, name, languages, averageRoundScore, averageSessionDuration);
  }

  public double getAverageRoundScore() {
    double avg = 0;
    int sessions = 0;

    for (Session s : this.sessionsMap.values()) {
      avg += s.getAverageRoundScore();
      sessions++;
    }

    return avg / sessions;
  }

  private double getAverageSessionDuration() {
    double avg = 0;
    int sessions = 0;

    for (Session s : this.sessionsMap.values()) {
      avg += s.getDuration();
      sessions++;
    }

    return avg / sessions;
  }

//  private List<LanguageStatistic> languageStats() {
//
//    ArrayList<LanguageStatistic> stats = new ArrayList<>();
//    Map<String, Double> scores = new HashMap<>();
//    Map<String, Double> durations = new HashMap<>();
//
//    for (Session s : this.sessionsMap.values()) {
//      String language = s.getLanguage();
//      double avgScore = s.getAverageRoundScore();
//      double duration = s.getDuration();
//
//      scores.put(language, avgScore);
//      durations.put(language, duration);
//    }
//
//  }

  private List<LanguageStatistic> languageStats() {
    List<LanguageStatistic> stats = new ArrayList<>();

    for (Session s : this.sessionsMap.values()) {
      LanguageStatistic tempStat = s.generateLanguageStatistic();
      stats = tempStat.mergeAdd(stats);
    }

    return stats;
  }

  @Override
  public int getID() {
    int id = this.participantId;
    return id;
  }

}
