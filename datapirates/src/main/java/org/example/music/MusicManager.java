package org.example.music;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * Data Pirates' Music Manager.
 * Plays music, sprite's music and background music.
 *
 * @author Data Pirates Team.
 *
 * @version JDK 18.
 */
public class MusicManager {

  /* The audio object. */
  private static Clip bgClip;

  /* Check if it is playing. */
  private static boolean isPlaying = false;
  private static int objectType;

  /**
   * Plays the music based on the id.
   *
   * @param id worldID or unique id that identifies a sprite type.
   *
   */
  public static void play(int id) {
    if (id >= 0 && id <= 3)
      playBG(getBackgroundMusic(id));
    else
      playSprite(getSpriteMusic(id));
    /*
      TODO {Warning}: Before Running this,
       the files must be in WAV not in MP3,
       convert MP3 TO WAV!
    */
  }

  /**
   * Plays the background music.
   *
   * @param path file path.
   *
   */
  public static void playBG(String path) {
    try {
      File file = new File(path);
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
      bgClip = AudioSystem.getClip();
      bgClip.open(audioStream);

      bgClip.loop(Clip.LOOP_CONTINUOUSLY);
      isPlaying = true;

      bgClip.start();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Play the music for sprites.
   * Player shoot, enemy shoot and player damaged.
   * Got help with ChatGPT.
   *
   * @param path file path.
   *
   */
  public static void playSprite(String path) {
    try {
      Clip spriteClip;
      File file = new File(path);
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
      spriteClip = AudioSystem.getClip();
      spriteClip.open(audioStream);

      // Get the FloatControl object associated with the Clip
      FloatControl gainControl = (FloatControl)
              spriteClip.getControl(FloatControl.Type.MASTER_GAIN);

      // Set the gain to -6dB, which corresponds to half the full volume
      float gain = 0.25f;
      float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
      gainControl.setValue(dB);
      spriteClip.start();


    } catch (Exception e) {
      System.out.println("Played at a wrong time.");
    }
  }

  /**
   * Check if it is playing.
   *
   * @return true if it is playing, false otherwise.
   *
   */
  public static boolean isPlaying() {
    return isPlaying;
  }

  /**
   * Get the sprite music path.
   *
   * @param sprite unique id that identifies a sprite type.
   *
   * @return sprite music file path, otherwise null.
   *
   *
   */
  private static String getSpriteMusic(int sprite) {
    String path = "datapirates\\src\\main\\java\\org\\example\\music\\sprites\\";
    switch (sprite) {
      case 4 -> {
        return path + "playerShoot.wav";
      }
      case 5 -> {
        return path + "enemyShoot.wav";
      }
      default -> {
        return path + "playerDamage.wav";
      }
    }
  }

  /**
   * Get the background music path.
   *
   * @param worldID world number.
   *
   * @return audio file path.
   *
   */
  private static String getBackgroundMusic(int worldID) {
    objectType = worldID;
    String path = "datapirates\\src\\main\\java\\org\\example\\music\\";
    final int mainRoom = 0;
    final int spawnRoom = 3;
    return switch (worldID) {
      case mainRoom -> path + "arena.wav";
      case spawnRoom -> path + "spawn.wav";
      default ->
              /* Placeholders. Lil lorem ipsum */
              null;
    };
  }

  /**
   * Used on background music. Helps to stop the current world music
   * and transit to the next one.
   *
   * @return objectType.
   *
   */
  public static int getObjectType() {
    return objectType;
  }

  /**
   * Used when travelling to different scenes.
   */
  public static void stop() {
    bgClip.stop();
  }
}
