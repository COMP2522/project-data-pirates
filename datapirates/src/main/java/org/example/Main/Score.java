package org.example.Main;

/**
 * Score program for the player.
 *
 * @author Data Pirates Team
 *
 * @version JDK 18
 */
public class Score {

  /* Player's score value. */
  private int value;

  /* Singleton class. */
  private static Score score;

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
   * @param value new score value
   */
  public void setValue(int value) {
    this.value = value;
  }

  /**
   * Get the score value.
   * @return value
   */
  public int getValue() {
    return value;
  }
}
