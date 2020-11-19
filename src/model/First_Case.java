package model;

import controller.Run;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;
import java.util.*;
public class First_Case extends Run {

    public static void readMatches(String csvFile) {
        try {
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            TreeMap<String,Integer> tMap = new TreeMap<String, Integer>();
            int count = 1;
            String yr = "";
            while((line = br.readLine()) != null)
            {
                String[] row = line.split(",");
                String year = row[1];

                    tMap.remove("season");

                if(tMap.containsKey(year) && year.equals(yr))
                {
                    count += 1;
                }
                else{
                    count = 1;
                }
                tMap.put(year,count);
                yr = year;
            }

            for (Map.Entry m : tMap.entrySet())
            {
                System.out.println("Year : " + m.getKey() + " , Number of matches played in " + m.getKey() + ": " + m.getValue());
            }
            br.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
