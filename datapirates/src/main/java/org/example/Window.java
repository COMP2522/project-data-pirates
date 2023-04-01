package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;
import javax.sound.sampled.*;
import static com.mongodb.client.model.Filters.eq;

/**
 *
 */
public class Window extends PApplet {

  /** Only one player allowed. Single player. */
  protected static Player player;

  private Timer clock;

  private int numEnemies = 1;
  private final int numEnemiesUnChanged = numEnemies;

  /* Min size of entities. */
  private final static int MINSIZE = 9;

  /* Max size of entities. */
  private final static int MAXSIZE = 20;
  int width = 1080;
  int height = 520;
  DataPiratesCollection dpC;
  Score score;

  private final int appendNum = 1;

  private boolean travelBoolean = false;

  private Menu menu;

  private boolean gameRunning = false;

  Weapon weapon;

  private PImage playerImage;
  private PImage enemyImage;

  MongoDatabase database;

  private DatabaseHandler dbHandler;



  public void settings() {
    size(width, height);
  }

  public void setup() {
//    settings();
    numEnemies = numEnemiesUnChanged;
    dpC = DataPiratesCollection.getInstance();
    score = Score.getInstance();
    EntityColor.setColors();
    fetchGameDataAsync();
    loadImages();
    menu = new Menu(this);
    weapon = new Weapon("Basic", EntityColor.getSpriteColors().get("Bullet"), 100);
    clock = new Timer();
    this.init();
    clock.start();
    dbHandler = new DatabaseHandler();
  }

  public void setUpEnemies() {
    for (int i = 0; i < numEnemies; i++) {
      PVector enemy = new PVector(random(0, this.width), random(0, this.height));
      PVector enemyDirection = SpriteManager.calculateDirection(enemy, player.getPosition());
      Sprite e = new Enemy(
              enemy,
              enemyDirection,
              random(MINSIZE, MAXSIZE),
              random(0,2),
              EntityColor.getSpriteColors().get("Enemy"),
              this,
                enemyImage
      );
      dpC.getEnemies().add(e);
    }

    dpC.getSprites().addAll(dpC.getEnemies());
  }

  public void init() {
    Player p = Player.getInstance(
            new PVector( this.width / 2,this.height / 2),
            new PVector(0,1),
            40,
            10,
            EntityColor.getSpriteColors().get("Player"),
            this, playerImage);
//    dpC.getEnemies().add(e);
    player = p;
    Weapon basic = new Weapon("Basic", EntityColor.getSpriteColors().get("Bullet"), 100);
    player.setWeapon(basic);
//    player.setHealth(100);
    dpC.getSprites().add(p);

//    PVector plyer = new PVector(player.getPosition().x, player.getPosition().y);
    setUpEnemies();

  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKey() == 'r')
      player.getWeapon().reload();
    player.move(e);

    if (key == 'j' || key == 'J') {
      if (player.getWeapon().hasAmmo()) {
        player.getWeapon().shoot();
        playSound("datapirates\\src\\main\\shoot.wav");
        PVector mouse = new PVector(mouseX, mouseY);
        PVector dir = SpriteManager.calculateDirection(player.getPosition(), mouse);

        // Shoot ProjectileType1
        ProjectileType1 projectile = new ProjectileType1(player.getPosition().copy(), dir, 10, 5, EntityColor.getSpriteColors().get("Bullet"), this);
        dpC.getSprites().add(projectile);
        dpC.getBullets().add(projectile);
      }
    } else {
      player.move(keyEvent);
    }
  }

  @Override
  public void mousePressed() {
    String clickedButton = menu.getClickedButton(mouseX, mouseY);

    if (clickedButton != null) {
      switch (clickedButton) {
        case "start":
          gameRunning = true;
          break;
        case "highScore":
          // Show high score
          break;
        case "credits":
          // Show credits
          break;
        case "quit":
          exit();
          break;
      }
    } else if (gameRunning) {
      if (player.getWeapon().hasAmmo()) {
        player.getWeapon().shoot();
        playSound("datapirates\\src\\main\\shoot.wav");
        PVector mouse = new PVector(mouseX, mouseY);
        PVector dir = SpriteManager.calculateDirection(player.getPosition(), mouse);

        // Shoot ProjectileType2
        ProjectileType2 projectile = new ProjectileType2(player.getPosition().copy(), dir, 10, 5, EntityColor.getSpriteColors().get("Bullet"), this);
        dpC.getSprites().add(projectile);
        dpC.getBullets().add(projectile);
      } else {
        textSize(60);
        fill(EntityColor.getSpriteColors().get("Reload").getRed(), EntityColor.getSpriteColors().get("Reload").getGreen(), EntityColor.getSpriteColors().get("Reload").getBlue());
        text("Reload!", player.getPosition().x + player.getSize(), player.getPosition().y + player.getSize());
      }
    }
  }


  /**
   * Make this function only available to certain
   * weapons.
   * REASON: IT IS TOO OVERPOWERED
   */
  public void mouseDragged() {
    mousePressed();
  }

  void printDisplayText() {
    // example texts

    textSize(10);
    fill(EntityColor.getSpriteColors().get("Text").getRed(), EntityColor.getSpriteColors().get("Text").getGreen(), EntityColor.getSpriteColors().get("Text").getBlue());
    text("Score: " + score.getValue(), 40, 120);

    textSize(20);
    fill(EntityColor.getSpriteColors().get("Text").getRed(), EntityColor.getSpriteColors().get("Text").getGreen(), EntityColor.getSpriteColors().get("Text").getBlue());
    text("Health: " + player.getHealth(), this.width / 3, 120);

    textSize(30);
    fill(EntityColor.getSpriteColors().get("Text").getRed(), EntityColor.getSpriteColors().get("Text").getGreen(), EntityColor.getSpriteColors().get("Text").getBlue());
    text("Ammo: " + player.getWeapon().getCurrentAmmo() + " / " + player.getWeapon().getAmmoCapacity() + "", 40, 100);

    textSize(30);
    text("Clock " + (int) clock.getEstimated() + "s", 40, 80);
    fill(EntityColor.getSpriteColors().get("Text").getRed(), EntityColor.getSpriteColors().get("Text").getGreen(), EntityColor.getSpriteColors().get("Text").getBlue());

  }

  /**
   * Updates and gets called everytime.
   */
  public void draw() {
    if (!gameRunning) {
      background(0);
        menu.show();
        menu.draw();
    } else {
      menu.hide();

      // used to move to shop
      if (player.getPosition().x >= this.width) {
        travelBoolean = true;
//      clock.reset();
        clock.stop();
        player.getPosition().set(10, player.getPosition().y);
      } else if (player.getPosition().x < 5 && travelBoolean) {
        clock.start();
        player.getPosition().set(this.width - 10, player.getPosition().y);
        setUpEnemies();
        travelBoolean = false;
      }
      if (travelBoolean) {
        background(255);
        for (Sprite s : dpC.getSprites()) {
          if (s instanceof Enemy)
            dpC.getTrash().add(s);
        }

        for (Sprite s : dpC.getTrash()) {
          removeEnemies(s);
        }
      } else {
        background(0);
        if (clock.stop()) {
          // cleans the trash collection every 10 seconds IDK
          // boost performance by resetting the garbage
          // collection
          dpC.setTrash(new ArrayList<Sprite>());
          dpC.setRemove(new HashMap<Projectile, Enemy>());
          numEnemies += appendNum;
          setUpEnemies();
          clock.start();
        }

      }

      printDisplayText();
      dpC.getSprites().get(0).update();
      dpC.getSprites().get(0).draw();
//    while (true) {
      for (Sprite s : dpC.getSprites()) {
        if (!(s instanceof Player)) {
          if (s instanceof Enemy) {
            PVector dir = SpriteManager.calculateDirection(s.getPosition(), player.getPosition());
            s.setDirection(dir);
            textSize(10);
            fill(EntityColor.getSpriteColors().get("Text").getRed(), EntityColor.getSpriteColors().get("Text").getGreen(), EntityColor.getSpriteColors().get("Text").getBlue());
            text("health: " + ((Enemy) s).getHealth(), s.getPosition().x - 10, s.getPosition().y + 20);
          }
          s.update();
          s.draw();
        }
      }

      // bullet vs enemy
      for (Sprite bullet : dpC.getBullets()) {
        for (Sprite enemy : dpC.getEnemies()) {
          if (bullet.collided(enemy)) {
//          if ((Enemy) enemy.)
            ((Enemy) enemy).setHealth(((Enemy) enemy).getHealth() - 10);
            if (((Enemy) enemy).getHealth() <= 0) {
              System.out.println("Killed an enemy!");
              dpC.getRemove().put((Projectile) bullet, (Enemy) enemy);
              score.setValue(score.getValue() + 2);
            } else {
              dpC.getTrash().add(bullet);
            }
          } else if (bullet.getPosition().x >= this.width || bullet.getPosition().y >= this.height) {
            dpC.getTrash().add(bullet);
          }
        }
      }

      // player vs enemy
      for (Sprite enemy : dpC.getEnemies()) {
        if (!(player.getHealth() <= 0)) {
          if (player.collided(enemy)) {
            player.setHealth(player.getHealth() - 2);
            score.setValue(score.getValue() + 1);
            dpC.getTrash().add(enemy);
            System.out.println("collided with enemy");
          }
        } else {
          setup();
        }
      }

      // remove the collision between bullets and enemies
      for (Map.Entry<Projectile, Enemy> mapElement : dpC.getRemove().entrySet()) {
        removeBullet(mapElement.getKey());
        removeEnemies(mapElement.getValue());
      }
      // remove bullets that are out of bounds
      // and enemies that have touched the player.
      for (Sprite s : dpC.getTrash()) {
        if (s instanceof Projectile)
          removeBullet(s);
        removeEnemies(s);
      }
    }
      weapon.update();
        weapon.draw();
      saveGameState();
  }

  public void playSound(String soundFileName) {
    try {
      File soundFile = new File(soundFileName);
      AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
      Clip clip = AudioSystem.getClip();
      clip.open(audioIn);
      clip.start();
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
      e.printStackTrace();
    }
  }

  /**
   * Removes an enemy in the enemy
   * and sprite list.
   *
   * @param s Enemy as a Sprite object.
   */
  public void removeEnemies(Sprite s) {
    dpC.getEnemies().remove(s);
    dpC.getSprites().remove(s);
  }

  /**
   * Removes a bullet from the bullet
   * and sprite list.
   *
   * @param b Bullet as a Sprite object.
   */
  public void removeBullet(Sprite b) {
    dpC.getBullets().remove(b);
    dpC.getSprites().remove(b);
  }

  private String fetchGameData() {
    // Simulate a delay in fetching the data (e.g., 2 seconds)
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // Simulate the game data
    return "Fetched game data";
  }

  public void fetchGameDataAsync() {
    CompletableFuture.supplyAsync(() -> fetchGameData())
            .thenAccept(gameData -> {
              // This block will be executed once the game data is fetched
              System.out.println("Fetched game data: " + gameData);
            });
  }

  public void loadImages() {
    playerImage = loadImage("datapirates\\src\\main\\plane.png");
    enemyImage = loadImage("datapirates\\src\\main\\rocket.png");
  }


  public class DatabaseHandler {
    ConnectionString connectionString = new ConnectionString("mongodb+srv://jliao53:<a123456>@cluster0.eelgl4x.mongodb.net/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .serverApi(ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build())
            .build();
    MongoClient mongoClient = MongoClients.create(settings);
    MongoDatabase database = mongoClient.getDatabase("test");
    public void saveGameState(String playerName, int score, int health) {
      Document document = new Document();
      document.append("playerName", playerName);
      document.append("score", score);
      document.append("health", health);

      new Thread(() -> {
        database.getCollection("gameStates").insertOne(document);
      }).start();
    }
    public void updateGameState(String playerName, int score, int health) {
      new Thread(() -> {
        database.getCollection("gameStates").updateOne(
                Filters.eq("playerName", playerName),
                Updates.combine(
                        Updates.set("score", score),
                        Updates.set("health", health)
                )
        );
      }).start();
    }
  }

  public void put(String key, String value) {
    Document document;
    document = new Document();
    document.append(key, value);
    CompletableFuture.runAsync(() -> {
      MongoDatabase database = null;
      database.getCollection("students").insertOne(document);
    });
    new Thread(() -> {
      database.getCollection("students").insertOne(document);
    }).start();
  }

  public void saveGameState() {
    String playerName = "Player";
    int playerScore = score.getValue();
    int playerHealth = player.getHealth();

    dbHandler.updateGameState(playerName, playerScore, playerHealth);
  }

  /**
   * Drives the program.
   * @param args unused
   */
  public static void main(String[] args) {
    String[] appletArgs = new String[]{"eatBubbles"};
    Window eatBubbles = new Window();
    PApplet.runSketch(appletArgs, eatBubbles);

    ConnectionString connectionString = new ConnectionString("mongodb+srv://jliao53:<a123456>@cluster0.eelgl4x.mongodb.net/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .serverApi(ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build())
            .build();
    MongoClient mongoClient = MongoClients.create(settings);
    MongoDatabase database = mongoClient.getDatabase("test");

    database.createCollection("students");

    Document document;
    document = new Document();
    document.append("name", "Ram");
    document.append("age", 26);
    document.append("city", "Hyderabad");
    database.getCollection("students").insertOne(document);

    Document find = database.getCollection("students").find(eq("name", "Ram")).first();
    System.out.println(find);

    Window window = new Window();
    window.put("name", "Ram");

  }
}
