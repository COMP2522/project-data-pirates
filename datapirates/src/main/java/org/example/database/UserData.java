package org.example.database;

/**
 * Data pair. Key is always a string for a column.
 * The value types are String and Integer.
 *
 * @param <Data> any data type, this uses String and Int.
 *
 * @author Data Pirates Team.
 *
 * @version JDK 18
 */
public class UserData<Data> {

  /* Key. */
  private final String field;

  /* Value. */
  private Data value;

  /**
   * Construct a pair.
   *
   * @param key String, column header.
   * @param value column value.
   */
  public UserData(String key, Data value) {
    field = key;
    this.value = value;
  }

  /* TODO: Getters and Setters beyond this point. */
  public Data getValue() {
    return value;
  }
}
