package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.json.simple.parser.ParseException;

public class TopPlayerList {

  final static int MAX_SIZE = 11;
  final static int MAX_SHOW = 10;
  final static String FILE_NAME = "topPlayers.json";
  ArrayList<KVPair> arlist = new ArrayList<>();

  public TopPlayerList() throws IOException, ParseException {
    File file = new File("./" + FILE_NAME);
    if(file.exists()){
      readFile();
    }else {
      KVPair record = new KVPair("Jim", 0);
      for (int i = 0; i < MAX_SHOW; i++) {
        arlist.add(record);
      }
    }
  }

  public ArrayList<KVPair> getArlist() {
    return arlist;
  }

  public void addRecord(String playerName, int scores){
    KVPair record = new KVPair(playerName,scores);
    arlist.add(record);
    sortList();
    while (arlist.size() > MAX_SIZE){
      arlist.remove(arlist.size()-1);
    }
    writeToFile();
  }

  public void addRecord(KVPair p){
    arlist.add(p);
    sortList();
    while (arlist.size() > MAX_SIZE){
      arlist.remove(arlist.size()-1);
    }
    writeToFile();
  }

  public void sortList(){
    Collections.sort(arlist, new Comparator<KVPair>() {
      @Override
      public int compare(KVPair o1, KVPair o2) {
        Integer int1 = Integer.valueOf(o1.getValue().toString());
        Integer int2 = Integer.valueOf(o2.getValue().toString());
        return int2.compareTo(int1);
      }
    });
  }

  public String[] toStringList(){
    String[] list = new String[MAX_SHOW];
    for(int i = 0; i < MAX_SHOW; i++){
      KVPair p = arlist.get(i);
      list[i] = p.key + " : " + p.value;
    }
    return  list;
  }

  public void writeToFile (){
    JSONWriter writer = new JSONWriter(arlist);
    writer.writeToFile(FILE_NAME);
  }

  public void readFile() throws IOException, ParseException {
    JSONReader reader = new JSONReader(FILE_NAME);
    this.arlist = reader.convertToKVList();
  }

}
