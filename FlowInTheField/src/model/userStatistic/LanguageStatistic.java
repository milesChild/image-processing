package model.userStatistic;

import java.util.List;

public class LanguageStatistic implements Statistic {
  String language;
  double averageScore, averageRoundDuration, totalScore, totalDuration;
  int totalMerges;

  public LanguageStatistic(String language, double averageScore,
                           double averageRoundDuration) {
    this.language = language;
    this.averageScore = averageScore;
    this.averageRoundDuration = averageRoundDuration;
    this.totalScore = averageScore;
    this.totalDuration = averageRoundDuration;
    this.totalMerges = 1;
  }

  public List<LanguageStatistic> mergeAdd(List<LanguageStatistic> other) {
    for (LanguageStatistic ls : other) {
      if (this.sameLanguage(ls)) {
        ls.combine(this);
        return other;
      }
    }

    other.add(this);
    return other;
  }

  // determines if two language statistics have the same language
  private boolean sameLanguage(LanguageStatistic other) {
    return this.language.equals(other.language);
  }

  // combines the stats of this language statistic with another language statistic
  private void combine(LanguageStatistic other) {
    this.totalScore += other.totalScore;
    this.totalDuration += other.totalDuration;
    this.totalMerges++;
    this.averageScore = this.totalScore / this.totalMerges;
    this.averageRoundDuration = this.totalDuration / this.totalMerges;
  }

}
