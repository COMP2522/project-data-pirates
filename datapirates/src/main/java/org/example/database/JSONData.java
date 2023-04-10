package org.example.database;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Procedure for saving and obtaining data from JSON file.
 *
 * @author Teddy Dumam-Ag
 *
 * @version JDK 18
 */
public class JSONData<Data> {

  private boolean fileExists = true;

  /* JSON contents. */
  private Object data;

  /* Directory of the config.json. */
  private final String directory;

  /**
   * Construct a JSON file creator for data pirates.
   *
   * @param fileName the name of the file.
   * @throws IOException when the file is a directory, or not modifiable.
   * @throws ParseException JSON parsing.
   */
  public JSONData(String fileName) throws IOException, ParseException {
    directory = "datapirates\\src\\main\\java\\org\\example\\database\\" + fileName;
    JSONParser convertToArray = new JSONParser();
    File file = new File(directory);
    /* Check if it exists. */
    if (!file.exists()) {
      file.createNewFile();
      fileExists = false;
    } else /* If it exists. */
      data = convertToArray.parse(new FileReader(file));
  }

  public boolean doesFileExists() {
    return fileExists;
  }

  /**
   * Get the JSON contents as an ArrayList.
   *
   * @return JSON contents in an ArrayList.
   */
  public ArrayList<UserData> getData() {
    ArrayList<UserData> tempData = new ArrayList<>();
    if (data == null)
      return null;
    JSONObject parsedJSON = (JSONObject) data;
    final List keys = parsedJSON.keySet().stream().toList();
    final List values = parsedJSON.values().stream().toList();
    for (int i = 0; i < keys.size(); i++)
      tempData.add(new UserData(keys.get(i).toString(), values.get(i)));
    return tempData;
  }

  /**
   * Write the values to the JSON file.
   *
   * @param key column name.
   * @param values values for each column, as a list.
   * @throws IOException throws whenever file is not modifiable.
   */
  public void writeToJson(String[] key, Data... values) throws IOException {
    FileWriter fw = new FileWriter(directory);
    JSONObject j = new JSONObject();

    for (int i = 0; i < key.length; i++)
        j.put(key[i], values[i]);

    fw.write(j.toJSONString());
    fw.close();
  }
}
