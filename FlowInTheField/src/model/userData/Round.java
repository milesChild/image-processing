package model.userData;

public class Round implements UserData {
  int roundId, sessionId, score;
  long startTime, endTime;

  public Round(int roundId, int sessionId, int score, long startTime, long endTime) {
    this.roundId = roundId;
    this.sessionId = sessionId;
    this.score = score;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public int getID() {
    int id = this.roundId;
    return id;
  }

  @Override
  public double getAverageRoundScore() {
    return this.score;
  }

}
