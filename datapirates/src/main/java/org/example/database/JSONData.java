package org.example.database;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
//import java.time.Duration;
import java.util.*;

/**
 * Local.
 */
public class JSONData<Data> {

  Object data;

  private String directory;
  public JSONData(String fileName) throws IOException, ParseException {
    directory = "datapirates\\src\\main\\java\\org\\example\\database\\" + fileName;
    JSONParser convertToArray = new JSONParser();
    File file = new File(directory);
    // Check if exists
    if (!file.exists())
      file.createNewFile();
    if (file.length() > 0)
      data = convertToArray.parse(new FileReader(file));



  }

  public ArrayList<UserData> getData() {
    ArrayList<UserData> tempData = new ArrayList<>();
//    for (Object o : data) {
    if (data == null)
      return null;
      JSONObject jsonO = (JSONObject) data;
    final List keys = jsonO.keySet().stream().toList();
    final List values = jsonO.values().stream().toList();
    for (int i = 0; i < keys.size(); i++) {
      tempData.add(new UserData(keys.get(i).toString(), values.get(i)));

    }
    return tempData;
  }

  public void writeData(String[] key, Data... values /* , int[] date */) throws IOException {
    FileWriter fw = new FileWriter(directory);
    JSONObject j = new JSONObject();

    for (int i = 0; i < key.length; i++) {
//      tempDatas.add(new UserData(key[i], value));
      if (i >= values.length)
        j.put(key[i], null);
      else
        j.put(key[i], values[i]);

    }

    fw.write(j.toJSONString());
    fw.close();

  }
}
