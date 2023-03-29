package org.example.music;

import processing.core.PImage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class MusicManager {

  private static MusicManager moosic;

  private Clip clip;
  boolean isPlaying = false;
  private MusicManager() {

  }
  public static MusicManager getInstance() {
    if (moosic == null)
      moosic = new MusicManager();
    return moosic;
    }

  public void play(int worldID) {
    // Convert MP3 TO WAV.
//    try {
//      File file = new File(getBackgroundMusic(worldID));
//      AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
//      clip = AudioSystem.getClip();
//      clip.open(audioStream);
//      clip.start();
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
  }

  private String getBackgroundMusic(int worldID) {
    String path = "datapirates\\src\\main\\java\\org\\example\\music\\";
    switch (worldID) {
      case 0:
        return path + "Devourer.wav";
    }
    return null;
  }

  public void stop() {
    clip.stop();
  }
}
