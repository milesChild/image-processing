package model.rawData;

import model.modelData.ModelData;
import model.modelData.RoundData;

public class Round implements RawData {

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
  public ModelData toModelData() {
    return new RoundData(this.roundId, this.sessionId, this.score, this.startTime, this.endTime);
  }

  @Override
  public RawData toRawData() {
    return this;
  }

}
