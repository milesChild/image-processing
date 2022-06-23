package model.rawData;

import java.util.List;

import model.modelData.ModelData;
import model.modelData.ParticipantData;

public class Participant implements RawData {

  int participantId;
  String name; // format: "<First> <Last>"
  int age;
  List<Integer> sessions; // list of session IDs, each int corresponding to a single session

  public Participant(int participantId, String name, int age, List<Integer> sessions) {
    this.participantId = participantId;
    this.name = name;
    this.age = age;
    this.sessions = sessions;
  }

  @Override
  public ModelData toModelData() {
    return new ParticipantData(this.participantId, this.name, this.age, this.sessions);
  }

  @Override
  public RawData toRawData() {
    return this;
  }

}
