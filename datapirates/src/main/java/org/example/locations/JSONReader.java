package org.example.locations;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JSONReader {

  JSONParser parser = new JSONParser();

  JSONArray theArray;

  public JSONReader(String fileName) throws IOException, ParseException {
    String fileN = "./" + fileName;
    this.theArray = (JSONArray) parser.parse(new FileReader(fileN));
  }

  public JSONArray getTheArray(){
    return theArray;
  }

  public ArrayList<KVPair> convertToKVList (){
    if (theArray == null) return null;

    ArrayList<KVPair> theList = new ArrayList<>();
    for(Object o : theArray) {
      JSONObject option = (JSONObject) o;
      String color = (String) option.get("player");
      String value = (String) option.get("scores");
      KVPair<String> kv = new KVPair<>(color, value);
      theList.add(kv);
    }
    return  theList;
  }

}
