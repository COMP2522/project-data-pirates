package org.example.Main;

/**
 * Contains the score status of the player.
 *
 * @author Data Pirates Team.
 *
 * @version JDK 18.
 */
public class Score {

  /* Singleton class. */
  private static Score score;

  /* High Score. */
  private int maxValue = 0;

  /* Player's score value. */
  private int value;

  /**
   * Create a Score object.
   *
   * @param val score value.
   */
  private Score(int val) {
    value = val;
  }

  /* TODO: Getters and Setters beyond this point. */

  /**
   * Set the High Score value.
   *
   * @param v current score.
   */
  public void setValue(int v) {
    value = v;
    if (value > maxValue) {
      maxValue = value;
    }
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

  public int getHighScore() {
    return maxValue;
  }

  public int getValue() {
    return value;
  }
}
