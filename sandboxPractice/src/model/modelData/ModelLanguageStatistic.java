package model.modelData;

import java.util.ArrayList;

import model.rawData.LanguageStatistic;
import model.rawData.RawData;

public class ModelLanguageStatistic implements ModelData, Comparable<ModelLanguageStatistic> {

  String language;
  public double totalScore, totalDuration;
  public int totalRounds;

  public ModelLanguageStatistic(String language, double totalScore,
                                double totalDuration, int totalRounds) {
    this.language = language;
    this.totalScore = totalScore;
    this.totalDuration = totalDuration;
    this.totalRounds = totalRounds;
  }

  public ArrayList<ModelLanguageStatistic> mergeAdd(ArrayList<ModelLanguageStatistic> other) {
    ArrayList<ModelLanguageStatistic> mergedList = new ArrayList<>();
    int iterations = 0;
    boolean merged = false;

    for (ModelLanguageStatistic ls : other) {

      // if the languages are the same, then combine the two, then add back the rest of the list
      if (this.language.equals(ls.language)) {
        ModelLanguageStatistic mergedLs = this.combine(ls);
        //ls.combine(this);
        mergedList.add(mergedLs);
        iterations++;
        merged = true;
        break;
      }

      // otherwise add the ls back to the mergedList
      mergedList.add(ls);
      iterations++;
    }

    // if there has not been a merge, then add this to the end of the original list
    if (!merged) {
      mergedList.add(this);
    }

    // otherwise, copy over the rest of the list, picking up where we left of when trying to find
    // a language match above
    for (int i = iterations; i < other.size(); i++) {
      ModelLanguageStatistic ls = other.get(i);
      mergedList.add(ls);
    }

    return mergedList;
  }

  private ModelLanguageStatistic combine(ModelLanguageStatistic other) {
    double totalScore = this.totalScore + other.totalScore;
    double totalDuration = this.totalDuration + other.totalDuration;
    int totalRounds = this.totalRounds + other.totalRounds;

    return new ModelLanguageStatistic(this.language, totalScore, totalDuration, totalRounds);
  }

  @Override
  public ModelData toModelData() {
    return this;
  }

  @Override
  public RawData toRawData() {

    double avgScore = Math.round((this.totalScore / this.totalRounds) * 100.0) / 100.0;
    double avgRoundDuration = Math.round((this.totalDuration / this.totalRounds) * 100.0) / 100.0;

    return new LanguageStatistic(this.language, avgScore, avgRoundDuration);
  }

  @Override
  public int compareTo(ModelLanguageStatistic o) {
    return (int) (this.totalScore - o.totalScore);
  }

}
