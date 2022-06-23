package model.rawData;

import java.util.List;

import model.modelData.ModelData;
import model.modelData.ModelStatistic;

public class Statistic implements RawData {

  int id;
  String name;
  public List<LanguageStatistic> languages;
  Double averageRoundScore, averageSessionDuration;

  public Statistic(int id, String name, List<LanguageStatistic> languages,
                       double averageRoundScore, double averageSessionDuration) {
    this.id = id;
    this.name = name;
    this.languages = languages;
    this.averageRoundScore = averageRoundScore;
    this.averageSessionDuration = averageSessionDuration;

  }

  @Override
  public ModelData toModelData() {
    return new ModelStatistic(this.id, this.name, this.languages, this.averageRoundScore,
            this.averageSessionDuration);
  }

  @Override
  public RawData toRawData() {
    return this;
  }

}
