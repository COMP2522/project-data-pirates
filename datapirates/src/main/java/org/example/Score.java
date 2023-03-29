package org.example;

/**
 * Score program for the player.
 * Eventually will be renamed to be the player's
 * stats for the shop feature we will add.
 *
 * @author Teddy Dumam-Ag
 *
 * @version JDK 18
 */
public class Score {

  private int value;

  private static Score score;

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

  public void setValue(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
