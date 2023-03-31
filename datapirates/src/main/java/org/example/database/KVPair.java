package org.example.database;

public class KVPair<T> {
  private String field;
  private T value;

  public KVPair(String key, T value) {
   field = key;
   this.value = value;
  }

  public String getField() {
    return field;
  }

  public T getValue() {
    return value;
  }
}
