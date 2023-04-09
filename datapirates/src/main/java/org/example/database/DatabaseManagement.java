package org.example.database;

import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.Document;
import org.example.spriteClasses.Player;
import org.example.spriteClasses.SpriteStat;
import org.json.simple.parser.ParseException;
import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.*;
//import ArrayList
/**
 * Uses MongoDB and maybe with JSON
 */
public class DatabaseManagement {


  private JSONData localFile;

  /**
   * Temporary Variables.
   */

  private static Player player;

  private static String name;
  private static final int VERYCOOLNUMBER = 8;

  private static final String[] identifiers = {"id", "name", "highscore"};
  private String playerID;
  MongoDatabase database;


  public DatabaseManagement() {
    try {
      localFile = new JSONData("config.json");
      setUp();
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
//    new Thread(() -> {
      while (isInDatabase()/* && JSONdata has playerID*/)
        setPlayerID();
//      if (localFile.getData() != null)
//        playerID = (String) localFile.getData().get(0).getValue();

//      else {
        save();
//      }
        try {
          localFile.getData();
          localFile.writeData(identifiers , playerID);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
          System.out.println(playerID);
      /* loadData() */
//    }).start();
  }
  public static void setPlayer(Player player) {
    DatabaseManagement.player = player;
  }

  public void setUp() throws IOException, ParseException {
    ConnectionString connectionString = new ConnectionString("mongodb+srv://Riel:arcturus@cluster0.biec9ck.mongodb.net/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .serverApi(ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build())
            .build();
    MongoClient mongoClient = MongoClients.create(settings);
    database = mongoClient.getDatabase("Data_Pirates");

    if (database.getCollection("players") == null)
      database.createCollection("players");

  }

  public void save() {
//    if (email != null && password != null) {
      Document doc = new Document();
      doc.append("id", playerID);
      doc.append("name", playerID);
      database.getCollection("players").insertOne(doc);

//      doc.append("email", email);
//      doc.append("password", email);

//    }

  }

  public void loadData() {

  }

  private boolean isInDatabase() {
    Document playerData = database.getCollection("players").find(eq("id", playerID)).first();
    return playerData != null;
  }
  public void setPlayerID() {
    Random randomNumberGenerator = new Random();
    new Thread(() -> {
      String id = "";
      id += "dpt0";
      for (int i = 0; i < VERYCOOLNUMBER; i++) {
        String chooser = "";
        chooser += randomNumberGenerator.nextInt(10);
        chooser += (char) (randomNumberGenerator.nextInt(26) + 'a');
        id += chooser.charAt(randomNumberGenerator.nextInt(2));
      }
//      System.out.println(id);
      playerID = id;
    }).start();
  }

  public FindIterable<Document> displayLeaderboard() {
    ArrayList<Integer> stats = new ArrayList<>();
    FindIterable<Document> leaderBoard = database.getCollection("players").find();
//    for (Document doc : leaderBoard) {
//      System.out.println(doc.get("Name")  + "\t" + doc.get("Score"));
//    }
    return leaderBoard;
  }
}
