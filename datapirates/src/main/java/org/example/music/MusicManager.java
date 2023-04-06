package org.example.music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Data Pirates' Music Manager.
 * Plays music, sprite's music and background music.
 *
 * @author Data Pirates Team
 */
public class MusicManager {

  /* This program is not a singleton! */
  private static MusicManager moosic;

  /* The audio object. */
  private Clip bgClip;

  private Clip spriteClip;

  /* Check if it is playing. */
  private boolean isPlaying = false;

  private int objectType;

  /**
   * This is not a singleton. Everything is going to be changed to static.
   * @return
   */
  public static MusicManager getInstance() {
    if (moosic == null)
      moosic = new MusicManager();
    return moosic;
  }

  /**
   * Plays the music based on the id.
   *
   * @param id worldID or unique id that identifies a sprite type.
   */
  public void play(int id) {
    if (id >= 0 && id <= 3) {
      playBG(getBackgroundMusic(id), true);
    }
    else
//      path = ;
    playSprite(getSpriteMusic(id));
    /* TODO {Warning}: Before Running this, the files must be in WAV not in MP3, convert MP3 TO WAV! */

  }


  public void playBG(String path, boolean mustLoop) {
    try {
      File file = new File(path);
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
      bgClip = AudioSystem.getClip();
      bgClip.open(audioStream);

      if (mustLoop) {
        bgClip.loop(Clip.LOOP_CONTINUOUSLY);
        isPlaying = true;
      }
      bgClip.start();

    } catch (Exception e) {
      e.printStackTrace();
    }


  }

  public void playSprite(String path) {
    try {
      File file = new File(path);
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
      spriteClip = AudioSystem.getClip();
      spriteClip.open(audioStream);

      // Get the FloatControl object associated with the Clip
      FloatControl gainControl = (FloatControl) spriteClip.getControl(FloatControl.Type.MASTER_GAIN);

      // Set the gain to -6dB, which corresponds to half the full volume
      float gain = 0.25f;
      float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
      gainControl.setValue(dB);
      spriteClip.start();


    } catch (Exception e) {
      System.out.println("Something went wrong, woops~");
    }
  }
  /**
   * Check if it is playing.
   * @return true if it is playing, false otherwise
   */
  public boolean isPlaying() {
    return isPlaying;
  }

  /**
   * Get the sprite music path.
   *
   * @param sprite unique id that identifies a sprite type
   *
   * @return sprite music file path, otherwise null
   *
   */
  private String getSpriteMusic(int sprite) {
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
   * @param worldID world number
   *
   * @return audio file path
   */
  private String getBackgroundMusic(int worldID) {
    objectType = worldID;
    String path = "datapirates\\src\\main\\java\\org\\example\\music\\";
    switch (worldID) {
      case 0: return path + "arena.wav";
      case 3: return path + "spawn.wav";
      /* Placeholders. Lil lorem ipsum */
    }
    return null;
  }

  public int getObjectType() {
    return objectType;
  }

  /**
   * Used when travelling to different scenes.
   */
  public void stop() {
    bgClip.stop();
  }
}
