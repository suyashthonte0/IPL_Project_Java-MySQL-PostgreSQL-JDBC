package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;
import java.util.*;

public class Third_Case {
    static ArrayList<String> idList = new ArrayList<String >();
    public static void readMatches(String matchesCSV) {
        try {
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(matchesCSV));
            int count = 0;

            while((line = br.readLine()) != null)
            {
                String[] row = line.split(",");
                String season = row[1];
                String id = row[0];
                //map.remove("season");

                if(season.equals("2016"))
                {
                    idList.add(id);
                }
            }
            br.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
    public static void readDeliveries(String deliveriesCSV) {
        try{

            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(deliveriesCSV));
            TreeMap<String,Integer> map = new TreeMap<>();
            while((line = br.readLine()) != null)
            {
                String[] rowDelivery = line.split(",");
                for(String ids : idList)
                {
                    if(ids.equals(rowDelivery[0]))
                    {
                        String team = rowDelivery[3];
                        int totalExtraRuns = 0;
                        int extraRuns = Integer.parseInt(rowDelivery[16]);
                        map.remove("bowling_team");

                        if(map.containsKey(team))
                        {
                            totalExtraRuns = map.get(team);
                            totalExtraRuns += extraRuns;
                        }
                        else
                        {
                            totalExtraRuns = extraRuns;
                        }
                        map.put(team,totalExtraRuns);
                    }
                }
            }
            for (Map.Entry m : map.entrySet())
            {
                System.out.println(m.getKey() + " is " + m.getValue());
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
