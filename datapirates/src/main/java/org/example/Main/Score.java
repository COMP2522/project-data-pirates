package org.example.Main;

/**
 * Score program for the player.
 *
 * @author Data Pirates Team
 *
 * @version JDK 18
 */
public class Score {

  /* Singleton class. */
  private static Score score;

  /* For leaderboard. */
  private int maxValue = 0;

  /* Player's score value. */
  private int value;

  /**
   * Create a Score object.
   *
   * @param val score value
   */
  private Score(int val) {
    value = val;
  }

  /**
   * Sets the only Score object.
   *
   * @return the only Score object.
   */
  public static Score getInstance() {
    if (score == null)
      score = new Score(0);
    score.value = 0;
    return score;
  }

  /**
   * Set the score value.
   * @param v new score value
   */
  public void setValue(int v) {
    value = v;
    if (value > maxValue) {
      maxValue = value;
    }
  }

  public int getHighScore() {
    return maxValue;
  }

  public void getHighScoreData(int maxValue) {
    this.maxValue = maxValue;
  }

  /**
   * Get the score value.
   * @return value
   */
  public int getValue() {
    return value;
  }
}
