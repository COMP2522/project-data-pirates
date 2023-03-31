package org.example.database;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.sql.Time;
//import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
/**
 * Local.
 */
public class JSONData {

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

  public ArrayList<KVPair> getData() {
    ArrayList<KVPair> tempData = new ArrayList<>();
//    for (Object o : data) {
    if (data == null)
      return null;
      JSONObject jsonO = (JSONObject) data;
      String playerID = (String) jsonO.get("id");
//      long minutes = (long) jsonO.get("until_chest");
      tempData.add(new KVPair("ID", playerID));
//    }
//    System.out.println(playerID);
    return tempData;
  }

  public void writeData(String id /* , int[] date */) throws IOException {
    FileWriter fw = new FileWriter(directory);
    ArrayList<KVPair> tempDatas = new ArrayList<>();
//    KVPair timeTillChest = new KVPair("until_chest");
//    if (date == null)
//    LocalDateTime time = LocalDateTime.of(date[0], date[1], date[2], date[3], date[4], date[5]);
//    long minutesBetween = Duration.between(LocalDateTime.now(), time).toMinutes();
//    Time a = new Time;
//    LocalDate time1 = LocalDate.of(date[0], date[1], date[2]);
//    LocalDateTime time = LocalDate.of(2023, 1, 30, );
//    ;
    tempDatas.add(new KVPair("id", id));
//    tempDatas.add(new KVPair("until_chest", id));
//    System.out.println(minutesBetween);
    KVPair a = new KVPair("id", id);

    JSONObject j = new JSONObject();
    j.put(a.getField(), a.getValue());
    fw.write(j.toJSONString());
    fw.close();
  }
}
