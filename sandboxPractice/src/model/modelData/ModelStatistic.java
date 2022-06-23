package model.modelData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.rawData.LanguageStatistic;
import model.rawData.RawData;
import model.rawData.Statistic;

public class ModelStatistic implements ModelData {

  int id;
  String name;
  ArrayList<ModelLanguageStatistic> languages;
  double averageRoundScore, averageSessionDuration;

  public ModelStatistic(int id, String name, List<LanguageStatistic> languages,
                        double averageRoundScore, double averageSessionDuration) {
    this.id = id;
    this.name = name;
    this.averageRoundScore = averageRoundScore;
    this.averageSessionDuration = averageSessionDuration;

    // convert the language statistics to new ModelLanguageStatistics
    ArrayList<ModelLanguageStatistic> convertedLanguages = new ArrayList<>();

    for (LanguageStatistic ls : languages) {
      ModelLanguageStatistic convertedLanguageStatistic = (ModelLanguageStatistic) ls.toModelData();
      convertedLanguages.add(convertedLanguageStatistic);
    }

    this.languages = convertedLanguages;
  }

  public ModelStatistic(int id, String name, ArrayList<ModelLanguageStatistic> languages,
                        double averageRoundScore, double averageSessionDuration) {
    this.id = id;
    this.name = name;
    this.averageRoundScore = averageRoundScore;
    this.averageSessionDuration = averageSessionDuration;
    this.languages = languages;
  }

  @Override
  public ModelData toModelData() {
    return this;
  }

  // converts this list of model statistics into a raw list of statistics (in the format desired
  // based on the prompt). This method handles the sorting of the language statistics by total
  // score.
  @Override
  public RawData toRawData() {
    ArrayList<LanguageStatistic> rawLanguages = new ArrayList<>();

    // create a copy of this list of language stats to sort
    ArrayList<ModelLanguageStatistic> unsortedLanguages = new ArrayList<>();

    for (ModelLanguageStatistic mls : this.languages) {
      unsortedLanguages.add(mls);
    }

    // sort this list of languages before converting
    Collections.sort(unsortedLanguages);
    Collections.reverse(unsortedLanguages);

    for (ModelLanguageStatistic mls : unsortedLanguages) {
      LanguageStatistic rawLanguageStat = (LanguageStatistic) mls.toRawData();
      rawLanguages.add(rawLanguageStat);
    }

    // round the avg values in this statistic before converting
    double avgRoundScore = Math.round(this.averageRoundScore * 100.0) / 100.0;
    double avgSessionDuration = Math.round(this.averageSessionDuration * 100.0) / 100.0;

    return new Statistic(this.id, this.name, rawLanguages,
            avgRoundScore, avgSessionDuration);
  }

}
