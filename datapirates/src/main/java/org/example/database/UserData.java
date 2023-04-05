package org.example.database;

public class UserData<Data> {
  private String field;
  private Data value;

  public UserData(String key, Data value) {
   field = key;
   this.value = value;
  }

  public String getField() {
    return field;
  }

  public Data getValue() {
    return value;
  }
}
