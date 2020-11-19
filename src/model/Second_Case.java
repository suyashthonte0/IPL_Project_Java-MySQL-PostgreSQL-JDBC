package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;
import java.util.*;

public class Second_Case {

    public static void readMatches(String csvFile) {
        try {
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            TreeMap<String,Integer> map = new TreeMap<String, Integer>();
            int count = 0;
            while((line = br.readLine()) != null)
            {
                String[] row = line.split(",");
                String team = row[4];
                String winner = row[10];
                map.remove("team1");

                if(map.containsKey(team))
                {
                    count = map.get(team);
                    if(team.equals(winner)) {
                        count += 1;
                    }
                }
                else{
                    if(team.equals(winner)) {
                        count = 1;
                    }
                }

                map.put(team,count);
            }

            for (Map.Entry m : map.entrySet())
            {
                System.out.println(m.getKey() + " " + m.getValue());
            }
            br.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
