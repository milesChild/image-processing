import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import model.userData.Participant;
import model.userData.Round;
import model.userData.Session;
import model.userStatistic.LanguageStatistic;
import model.userStatistic.Statistic;
import model.userStatistic.UserStatistic;

import static org.junit.Assert.assertEquals;

public class ModelTests {

  Participant p1;

  Session s0;
  Session s1;
  Session s2;
  Round r0;
  Round r1;
  Round r2;
  Round r3;

  @Before
  public void init() {
    this.p1 = new Participant(0, "Kurapika Kurta", 17,
            new ArrayList<>(Arrays.asList(0, 1, 2)));

    this.s0 = new Session(0, 0, "French",
            new ArrayList<>(Arrays.asList(0)), 1650328640, 1650328740);
    this.s1 = new Session(0, 1, "Japanese",
            new ArrayList<>(Arrays.asList(1, 2)), 1650736936, 1650737936);
    this.s2 = new Session(0, 2, "French",
            new ArrayList<>(Arrays.asList(3)), 1650758536, 1650758596);
    this.r0 = new Round(0, 0, 7, 1650328640, 1650328740);
    this.r1 = new Round(1, 1, 2, 1650736936, 1650737636);
    this.r2 = new Round(2, 1, 12, 1650737636, 1650737936);
    this.r3 = new Round(3, 2, 6, 1650758536, 1650758596);
  }

  @Test
  public void testinitSessions() {
    Statistic test = new UserStatistic(0, "Kurapika Kurta",
            new ArrayList<LanguageStatistic>(), 0, 0);

    assertEquals(test, this.p1.getStats());


    p1.initSessions(new Session[] {this.s0, this.s1, this.s2});

    Statistic test2 = new UserStatistic(0, "Kurapika Kurta",
            new ArrayList<LanguageStatistic>(), 6.75, 386.67);

  }

}
