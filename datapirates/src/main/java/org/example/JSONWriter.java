package org.example;

import java.io.FileWriter;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONWriter {
    JSONArray theList;

    public JSONWriter(ArrayList<KVPair> list){
        theList = new JSONArray();
        for(int i = 0; i < list.size(); i++){
            JSONObject kvDetails = new JSONObject();
            KVPair kvt = list.get(i);
            kvDetails.put(kvt.key, kvt.value);
            theList.add(kvDetails);
        }
    }

    public boolean writeToFile (String fileName){
        if(theList == null) {
            System.out.println("The writing data are not exist!");
            return false;
        }

        try{
            FileWriter output = new FileWriter("./" + fileName);

            for(int i = 0; i < theList.size(); i++){
                output.write(theList.get(i).toString());
            }

            output.close();
        }
        catch (Exception e){
            e.getStackTrace();
        }
        return true;
    }
}
