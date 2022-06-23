import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import model.modelData.ModelLanguageStatistic;
import model.modelData.ModelStatistic;
import model.rawData.LanguageStatistic;
import model.rawData.Statistic;

import static org.junit.Assert.assertEquals;

public class testModelStatistics {

  ModelStatistic ms1;
  ModelStatistic ms2;
  ModelLanguageStatistic mls1;
  ModelLanguageStatistic mls2;
  ModelLanguageStatistic mls3;


  @Before
  public void init() {

    this.mls1 = new ModelLanguageStatistic("Japanese", 7,
            500, 5);

    this.mls2 = new ModelLanguageStatistic("French", 6.5,
            80, 1);

    this.mls3 = new ModelLanguageStatistic("German", 9,
            200, 1);

    this.ms1 = new ModelStatistic(0, "Kurapika Kurta",
            new ArrayList<>(Arrays.asList(mls1, mls2)),
            6.75, 386.67);

    this.ms2 = new ModelStatistic(1, "Levi Ackerman",
            new ArrayList<>(Arrays.asList(mls3)),
            9, 200);

  }

  @Test
  public void testCombineTwoLanguageStatistics() {
    ArrayList<ModelLanguageStatistic> list =
            new ArrayList<ModelLanguageStatistic>(Arrays.asList(mls1, mls2));

    ModelLanguageStatistic test = new ModelLanguageStatistic("French",
            6, 100, 1);

    test.mergeAdd(list);

    assertEquals(list.size(), 2);
    assertEquals(6.5, list.get(1).totalScore, .01);

  }

  @Test
  public void testAddNewLanguageToList() {
    ArrayList<ModelLanguageStatistic> list =
            new ArrayList<ModelLanguageStatistic>(Arrays.asList(mls1, mls2));

    ModelLanguageStatistic test = new ModelLanguageStatistic("Cantonese",
            6, 100, 1);

    test.mergeAdd(list);

    assertEquals(list.size(), 2);
    assertEquals(6.5, list.get(1).totalScore, .01);

    ModelLanguageStatistic test2 = new ModelLanguageStatistic("Cantonese",
            6, 101, 1);

    test2.mergeAdd(list);

    assertEquals(2, list.size());
    assertEquals(6.5, list.get(1).totalScore, .01);


  }

  @Test
  public void testConvertLanguageStatisticModelToRaw() {
    LanguageStatistic test = (LanguageStatistic) this.mls1.toRawData();

    assertEquals("Japanese", test.language);
    assertEquals(1.4, test.averageScore, .01);
    assertEquals(100, test.averageRoundDuration, .01);

    ModelLanguageStatistic mls = new ModelLanguageStatistic("Cantonese",
            100.9874, 65.9281, 1);
    LanguageStatistic test2 = (LanguageStatistic) mls.toRawData();

    assertEquals("Cantonese", test2.language);
    assertEquals(100.99, test2.averageScore, .01);
    assertEquals(65.93, test2.averageRoundDuration, .01);
  }

  @Test
  public void testCombineThenConvertToRaw() {
    ArrayList<ModelLanguageStatistic> list =
            new ArrayList<ModelLanguageStatistic>(Arrays.asList(mls1, mls2));

    ModelLanguageStatistic test = new ModelLanguageStatistic("French",
            6.000001, 100, 1);

    test.mergeAdd(list);

    ModelStatistic stat = new ModelStatistic(9, "miles", list,
            6.75, 386.67);

    Statistic test2 = (Statistic) stat.toRawData();

    assertEquals(6.5, test2.languages.get(0).averageScore, .01);
    assertEquals(1.4, test2.languages.get(1).averageScore, .01);

  }

}
