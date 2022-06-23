import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.modelData.ModelStatistic;
import model.modelData.ParticipantData;
import model.modelData.RoundData;
import model.modelData.SessionData;
import model.rawData.Participant;
import model.rawData.Round;
import model.rawData.Session;
import model.rawData.Statistic;

public class main {

  public static void main(String[] args) {

    ArrayList<ParticipantData> participantData = deserialize();
    ArrayList<Statistic> statistics = generateStats(participantData);

    serialize(statistics);

  }

  private static ArrayList<ParticipantData> deserialize() {

    try {
      File input = new File("src/input.json");
      JsonElement getRequest = JsonParser.parseReader(new FileReader(input));
      JsonObject inputJson = getRequest.getAsJsonObject();

      // extract the sessions, rounds, and participants
      JsonArray unparsedSessions = inputJson.get("sessions").getAsJsonArray();
      JsonArray unparsedRounds = inputJson.get("rounds").getAsJsonArray();
      JsonArray unparsedParticipants = inputJson.get("participantInfo").getAsJsonArray();

      // initialize data that came from the JSON
      return initData(unparsedSessions, unparsedRounds, unparsedParticipants);

    } catch (IOException e) {
      throw new IllegalArgumentException();
    }

  }

  private static ArrayList<ParticipantData> initData(JsonArray sessions, JsonArray rounds,
                                                     JsonArray participants) {

    // convert the JSON Arrays into raw data arrays
    Session[] rawSessions = new Gson().fromJson(sessions, Session[].class);
    Round[] rawRounds = new Gson().fromJson(rounds, Round[].class);
    Participant[] rawParticipants = new Gson().fromJson(participants, Participant[].class);

    // convert the raw data into model data
    ArrayList<SessionData> modelSessions = new ArrayList<>();
    ArrayList<RoundData> modelRounds = new ArrayList<>();
    ArrayList<ParticipantData> modelParticipants = new ArrayList<>();

    for (Session s : rawSessions) {
      SessionData sd = (SessionData) s.toModelData();
      modelSessions.add(sd);
    }

    for (Round r : rawRounds) {
      RoundData rd = (RoundData) r.toModelData();
      modelRounds.add(rd);
    }

    for (Participant p : rawParticipants) {
      ParticipantData pd = (ParticipantData) p.toModelData();
      modelParticipants.add(pd);
    }

    // link the data in sessionsData, roundsData, and participantsData so all data can be stores in
    // the participantsData

    // link the rounds to the sessions
    for (SessionData sd : modelSessions) {
      sd.initRounds(modelRounds);
    }

    // link the sessions to the participants
    for (ParticipantData pd : modelParticipants) {
      pd.initSessions(modelSessions);
    }

    return modelParticipants;
  }

  private static ArrayList<Statistic> generateStats(ArrayList<ParticipantData> participantData) {
    ArrayList<ModelStatistic> modelStats = new ArrayList<>();
    ArrayList<Statistic> rawStats = new ArrayList<>();

    for (ParticipantData pd : participantData) {
      modelStats.add(pd.generateStatistics());
    }

    for (ModelStatistic ms : modelStats) {
      Statistic rs = (Statistic) ms.toRawData();
      rawStats.add(rs);
    }

    return rawStats;
  }

  // convert the statistics into a JSON to POST
  private static void serialize(ArrayList<Statistic> statistics) {

    // create custom GSON that will convert null fields to "N/A"
    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(Double.class, new DoubleSerializer()) ;
    Gson gson = builder.create();

    File post = new File("src/post.json");
    String postString = gson.toJson(statistics);

    try {
      FileWriter writer = new FileWriter(post);
      post.createNewFile();
      writer.write(postString);
      writer.close();
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

}
