package model.rawData;

import java.util.List;

import model.modelData.ModelData;
import model.modelData.SessionData;

public class Session implements RawData {

  int participantId, sessionId;
  String language;
  List<Integer> rounds; // list of roundIDs, each corresponding to a specific round in the session
  long startTime, endTime;


  public Session(int participantId, int sessionId, String language,
                 List<Integer> rounds, long startTime, long endTime) {
    this.participantId = participantId;
    this.sessionId = sessionId;
    this.language = language;
    this.rounds = rounds;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public ModelData toModelData() {
    return new SessionData(this.participantId, this.sessionId, this.language, this.rounds,
            this.startTime, this.endTime);
  }

  @Override
  public RawData toRawData() {
    return this;
  }

}
