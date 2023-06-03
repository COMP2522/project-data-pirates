package org.example.Main;

/**
 * Data Pirates' Timer class.
 * Used for proceeding to the next wave.
 * Meme: Reference to the "Mood Up" timer.
 *
 * @author Data Pirates Team.
 *
 * @version JDK 18.
 */
public class Timer {

  /* Current Time. */
  private long currTime;

  /* Difference between old time (currTime) and new time. */
  private double estimated;

  /* False -> Timer is stopped, True -> Timer is started. */
  private boolean isRunning = false;

  /**
   * Start the timer.
   */
  public void start() {
    isRunning = true;
    currTime = System.currentTimeMillis();
  }

  /**
   * Stops the timer when time is actually above the next wave.
   */
  public void stop() {
    if (isRunning) {
      final long end = System.currentTimeMillis();
      estimated = (end - currTime) / 1000.0;

      /* For each (endTime), go to next wave.
      (Go to next wave if player kills all enemies) */
      final long endTime = 5;

      if (estimated >= endTime) {
        currTime = 0;
        isRunning = false;
      }
    } else
      estimated = 0;
  }

  /**
   * Check if the timer is currently deactivated.
   *
   * @return true if stopped, otherwise false.
   *
   */
  public boolean isStopped() {
    return !isRunning;
  }

  /**
   * Resets the timer.
   */
  public void reset() {
    currTime = 0;
    estimated = 0;
  }

  /**
   * Get the time value. (Seconds)
   *
   * @return estimated.
   *
   */
  public double getEstimated() {
    return estimated;
  }

}
