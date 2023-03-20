package org.example;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.simple.JSONObject;

public class EnemyPositionWriter {
    public void writeEnemyPositionsToJsonFile(String fileName, Enemy[] enemies) {
        // Create a new JSON array to hold the enemy positions
        JSONArray enemyPositions = new JSONArray();

        // Loop through each enemy and add its position to the JSON array
//        for (Enemy enemy : enemies) {
//            JSONObject position = new JSONObject();
//            position.put("x", enemy.getPositionX());
//            position.put("y", enemy.getPositionY());
//            enemyPositions.add(position);
//        }

        // Create a JSON object to hold the enemy positions array
        JSONObject data = new JSONObject();
        data.put("enemyPositions", enemyPositions);

        // Write the JSON object to a file
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(data.toJSONString());
            System.out.println("Successfully wrote enemy positions to file " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing enemy positions to file " + fileName + ": " + e.getMessage());
        }
    }
}
