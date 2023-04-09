package org.example.topLIst;

import java.io.IOException;
import java.util.Random;
import org.json.simple.parser.ParseException;

public class testTopList {

  public static void main(String[] args) throws IOException, ParseException {
    TopPlayerList list = new TopPlayerList();

    String[] names = {"Alice", "Bob", "Charlie", "David", "Emily", "Frank",
        "Gina", "Harry", "Isabel", "Jack"};

    //for generate scores
    Random rd = new Random(); //for generate scores
    for(int i = 1010; i > 1000; i--){
      int scores = 1100 + rd.nextInt(900);
      KVPair p = new KVPair(names[i-1001], scores);
      list.addRecord(p);
    }
    System.out.println("Test finished!");

    String[]testlist = list.toStringList();

    for(int i =0; i < testlist.length; i ++){
      System.out.println(testlist[i]);
    }
  }

}
