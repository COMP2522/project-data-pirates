package org.example;

public class KVPair<T> {
  String key;
  T value;

  public KVPair(String key, T value) {
    this.key = key;
    this.value = value;
  }
}
