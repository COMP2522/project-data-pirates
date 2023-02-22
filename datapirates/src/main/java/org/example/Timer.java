package org.example;

public class Timer {

  long currTime;
  long endTime = 5;

  double estimated;
  boolean flippySwitch = false;
  public Timer() {
  }

  public void start() {
    flippySwitch = true;
    currTime = System.currentTimeMillis();

//    double seconds = (end - start) / 1000.0;
  }

  public boolean stop() {
    if (flippySwitch) {
      long end = System.currentTimeMillis();
      estimated = (end - currTime) / 1000.0;
      if (estimated >= endTime) {
        currTime = 0;
        flippySwitch = false;
        return true;
      }
    } else {
      estimated = 0;
    }
    return false;
//      System.out.println(estimated);
  }

  public void reset() {
    currTime = 0;
    estimated = 0;
  }
  public void setEstimated(double estimated) {
    this.estimated = estimated;
  }

  public double getEstimated() {
    return estimated;
  }

}
