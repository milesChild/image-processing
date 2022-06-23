package model.modelData;

import model.rawData.RawData;
import model.rawData.Round;

public class RoundData implements ModelData {

  int roundId, sessionId, score;
  long startTime, endTime;

  public RoundData(int roundId, int sessionId, int score, long startTime, long endTime) {
    this.roundId = roundId;
    this.sessionId = sessionId;
    this.score = score;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public ModelData toModelData() {
    return this;
  }

  @Override
  public RawData toRawData() {
    return new Round(this.roundId, this.sessionId, this.score, this.startTime, this.endTime);
  }

}
