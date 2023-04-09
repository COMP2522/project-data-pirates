package org.example.topLIst;

import java.io.File;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TopTimeList extends TopPlayerList{

  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

  public TopTimeList() throws IOException, ParseException {
    File file = new File("./" + FILE_NAME);
    Date date = new Date();
    if(file.exists()){
      readFile();
    }else {
      KVPair record = new KVPair(formatter.format(date), 0);
      for (int i = 0; i < MAX_SHOW; i++) {
        arlist.add(record);
      }
    }
  }
}
