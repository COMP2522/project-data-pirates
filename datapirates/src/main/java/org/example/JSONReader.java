package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader {

  JSONParser parser = new JSONParser();

  JSONArray theArray;

  public JSONReader(String fileName) throws IOException, ParseException {
    String fileN = "./" + fileName;
    Reader reader = new FileReader(fileN);
    JSONObject jsonObject = (JSONObject) parser.parse(reader);
//    this.theArray = (JSONArray) parser.parse(new FileReader(fileN));
    this.theArray = (JSONArray) jsonObject.get("records");
  }

  public JSONArray getTheArray(){
    return theArray;
  }

  public ArrayList<KVPair> convertToKVList (){
    if (theArray == null) return null;

    ArrayList<KVPair> theList = new ArrayList<>();
    ArrayList<String> listStr = new ArrayList<String>();
    for(int i = 0; i < theArray.size(); i++) {
      listStr.add (theArray.get(i).toString());
//      System.out.println(listStr.get(i));
    }

    for(String str : listStr){
      // Extract the key-value pair from the String element
      String[] parts = str.replaceAll("[{}\"]", "").split(":");
      String key = parts[0];
      Integer value = Integer.valueOf(parts[1]);

      theList.add(new KVPair(key,value));
    }
    return  theList;
  }

}
