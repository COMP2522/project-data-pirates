package org.example;

public class Timer {

  int currTime;
  int endTime;

  public Timer() {
    currTime = 0;
    endTime = 30;
  }

  public void run() {
    currTime++;
  }
}
