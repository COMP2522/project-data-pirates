package org.example.music;

import processing.core.PImage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

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
  private Clip clip;

  /* Check if it is playing. */
  private boolean isPlaying = false;

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
    String path = "";
    boolean mustLoop = false;
    if (id >= 0 && id <= 3) {
      path = getBackgroundMusic(id);
      mustLoop = true;
    }
    else
      path = getSpriteMusic(id);
    /* TODO {Warning}: Before Running this, the files must be in WAV not in MP3, convert MP3 TO WAV! */
    try {
      File file = new File(path);
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
      clip = AudioSystem.getClip();
      clip.open(audioStream);
      clip.start();
      if (mustLoop)
        clip.loop(Clip.LOOP_CONTINUOUSLY);
      isPlaying = true;
    } catch (Exception e) {
      e.printStackTrace();
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
        return path + "";
      }
      default -> {
        return null;
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
    String path = "datapirates\\src\\main\\java\\org\\example\\music\\";
    switch (worldID) {
      case 0: return path + "Devourer.wav";
      case 1: return path + "Devourer.wav";
      case 2: return path + "Devourer.wav";
      case 3: return path + "Devourer.wav";
      /* Placeholders. Lil lorem ipsum */
    }
    return null;
  }

  /**
   * Used when travelling to different scenes.
   */
  public void stop() {
    clip.stop();
  }
}
