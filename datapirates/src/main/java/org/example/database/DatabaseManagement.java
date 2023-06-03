package org.example.database;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import java.io.IOException;
import java.util.Random;
import org.bson.Document;
import org.example.Main.Score;


/**
 * MongoDB and JSON save method.
 * Save random generated player ID and the high score.
 * If you lose the config.json in this folder, you will lose
 * your high score.
 *
 * @author Teddy Dumam-Ag
 *
 * @version JDK 18
 */
public class DatabaseManagement {

  /* Column names. */
  public static final String[] KEYS = {"id", "highscore"};

  /* Auto save will be performed in n milliseconds.  */
  public static final int MS_PER_AUTOSAVE = 5000;

  /* Amount of letters / characters required to generate the id. */
  public static final int NUM_BYTES = 8;

  /* Used for manipulating the config.json. */
  private JSONData localFile;

  /* Score of the player. */
  private final Score score;

  /* Player's ID. */
  private String playerId;

  /* Auto-Save runs in the background. */
  private Thread autoSave;

  private MongoDatabase database;

  /**
   * Construct a manager for the database.
   *
   * @param scoreObj score object will be used to determine high score.
   */
  public DatabaseManagement(Score scoreObj) {
    score = scoreObj;
    try {
      localFile = new JSONData("config.json");
      setUp();
      autoSave();
    } catch (Exception fileExceptions) {
      fileExceptions.printStackTrace();
    }
  }

  /**
   * Apply the first procedure.
   * Connect to the database, "Data_Pirates" and perform
   * the first save.
   *
   * @throws IOException file format exception. When expected file is not a modifiable file.
   */
  public void setUp() throws IOException {
    /* Connect to Database. */
    ConnectionString connectionString = new ConnectionString(
            "mongodb+srv://Riel:arcturus@cluster0.biec9ck.mongodb.net/?retryWrites=true&w=majority"
    );
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .serverApi(ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build())
            .build();
    MongoClient mongoClient = MongoClients.create(settings);
    mongoClient.getDatabase("Data_Pirates");
    database = mongoClient.getDatabase("Data_Pirates");

    /* Join with JSON data and MongoDB. */
    /* If Exists. */
    if (localFile.doesFileExists()) {
      playerId = ((UserData) localFile.getData().get(1)).getValue().toString();
      score.setValue(Integer.parseInt(
              ((UserData) localFile.getData().get(0)).getValue().toString()));
      updateValues();
    } else { /* If not exists. */
      generateID();
      while (isInDatabase())
        generateID();
      saveToDB();
      localFile.writeToJson(KEYS, playerId, score.getHighScore());
    }

    /* Assign Auto-Save thread. */
    autoSave = new Thread(() -> {
      try {
        while (true) {
          updateValues();
          localFile.writeToJson(KEYS, playerId, score.getHighScore());
          /* Delay time. */
          Thread.sleep(MS_PER_AUTOSAVE);
        }
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });

  }

  /**
   * Save the new playerID to the database.
   */
  private void saveToDB() {
    Document doc = new Document();
    doc.append(KEYS[0], playerId);
    doc.append(KEYS[1], score.getHighScore());
    database.getCollection("players").insertOne(doc);
  }

  /**
   * Update the high score.
   */
  private void updateValues() {
    /* Search for this document. */
    BasicDBObject query = new BasicDBObject();
    query.put(KEYS[0], playerId);

    /* The column to be replaced. */
    BasicDBObject newValues = new BasicDBObject();
    newValues.put(KEYS[1], score.getHighScore());

    /* Create procedure. */
    BasicDBObject updateObj = new BasicDBObject();
    updateObj.put("$set", newValues);

    /* Perform procedure, finding the specific document and changing the values. */
    database.getCollection("players").updateOne(query, updateObj);
  }


  /**
   * Perform a condition where the random generated player ID is in the database.
   * Used for not accidentally assigning new members with old members profile.
   *
   * @return 1 when it's in the database, 0 otherwise.
   */
  private boolean isInDatabase() {
    Document playerData = database.getCollection("players").find(eq(KEYS[0], playerId)).first();
    return playerData != null;
  }

  /**
   * Generates a random player ID for new users.
   */
  private void generateID() {
    Random randomNumberGenerator = new Random();
    new Thread(() -> {
      StringBuilder id = new StringBuilder();
      id.append("dpt0");
      for (int i = 0; i < NUM_BYTES; i++) {
        String chooser = "";
        chooser += randomNumberGenerator.nextInt(10);
        chooser += (char) (randomNumberGenerator.nextInt(26) + 'a');
        id.append(chooser.charAt(randomNumberGenerator.nextInt(2)));
      }
      playerId = id.toString();
    }).start();
  }

  /**
   * Start the Auto-Save.
   */
  public void autoSave() {
    if (!autoSave.isAlive())
      autoSave.start();
  }
}
