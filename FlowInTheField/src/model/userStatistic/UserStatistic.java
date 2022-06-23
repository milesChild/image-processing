package model.userStatistic;

import java.util.List;

public class UserStatistic implements Statistic {
  int id;
  String name;
  List<LanguageStatistic> languages;
  double averageRoundScore, averageSessionDuration;

  public UserStatistic(int id, String name, List<LanguageStatistic> languages,
                       double averageRoundScore, double averageSessionDuration) {
    this.id = id;
    this.name = name;
    this.languages = languages;
    this.averageRoundScore = averageRoundScore;
    this.averageSessionDuration = averageSessionDuration;
  }

}
