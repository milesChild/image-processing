package model.rawData;

import model.modelData.ModelData;

public class LanguageStatistic implements RawData {

  public String language;
  public double averageScore, averageRoundDuration;

  public LanguageStatistic(String language, double averageScore,
                           double averageRoundDuration) {

    this.language = language;
    this.averageScore = averageScore;
    this.averageRoundDuration = averageRoundDuration;

  }


  @Override
  public ModelData toModelData() {
    return null;
  }

  @Override
  public RawData toRawData() {
    return this;
  }

}
