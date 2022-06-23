import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.userData.Participant;
import model.userData.Round;
import model.userData.Session;
import model.userStatistic.Statistic;

public class main {

  private static Participant[] participants;

  public static void main(String[] args) {

    try {
      deserialize();
    } catch (IOException e) {
      throw new IllegalArgumentException("Issue with input JSON. Try again.");
    }

    serialize();

  }

  // obtains information from the GET request and converts it into manipulable data
  private static void deserialize() throws IOException {

    try {
      File input = new File("src/testingJSONs/response.json");
      JsonElement getRequest = JsonParser.parseReader(new FileReader(input));
      JsonObject inputJson = getRequest.getAsJsonObject();

      // extract the sessions, rounds, and participants
      JsonArray unparsedSessions = inputJson.get("sessions").getAsJsonArray();
      JsonArray unparsedRounds = inputJson.get("rounds").getAsJsonArray();
      JsonArray unparsedParticipants = inputJson.get("participantInfo").getAsJsonArray();

      // initialize data that came from the JSON
      initData(unparsedSessions, unparsedRounds, unparsedParticipants);
    } catch (IOException e) {
      throw new IOException();
    }

  }

  // converts model information into a JSON file to be used for a POST request
  private static void serialize() {
    List<Statistic> statistics = new ArrayList<>();

    for (Participant p : participants) {
      statistics.add(p.getStats());
    }

    String response = new Gson().toJson(statistics);
  }

  // initializes data from a JSON and inputs it into the model
  private static void initData(JsonArray unparsedSessions,
                        JsonArray unparsedRounds, JsonArray unparsedParticipants) {

    // convert unparsed data to arrays that the model can manipulate
    Session[] sessions = new Gson().fromJson(unparsedParticipants, Session[].class);
    Round[] rounds = new Gson().fromJson(unparsedParticipants, Round[].class);
    Participant[] inputParticipants =
            new Gson().fromJson(unparsedParticipants, Participant[].class);

    // link the rounds to the sessions
    for (Session s : sessions) {
      s.initRounds(rounds);
    }

    // link the sessions to the participants
    for (Participant p : participants) {
      p.initSessions(sessions);
    }

    participants = inputParticipants;
  }

}
