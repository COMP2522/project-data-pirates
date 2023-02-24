package org.example;

public class Score {

  private int value;

  private static Score score;

  private Score(int val) {
    value = val;
  }

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
