package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Fifth_Case {

    static ArrayList<String> idList = new ArrayList<>();

    public static void readDeliveries(String deliveriesCSV) {
        try{

            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(deliveriesCSV));
            HashMap<String,Integer> totalWicketsMap = new HashMap<>();

            while((line = br.readLine()) != null)
            {
                String[] rowOfDelivery = line.split(",");

                        String bowler = rowOfDelivery[8];
                        int wickets = 0;
                        totalWicketsMap.remove("bowler");
                        if(totalWicketsMap.get(bowler)!=null)
                        {
                            wickets = totalWicketsMap.get(bowler);
                        }
                        String player_dismissed = "";
                        if(rowOfDelivery.length > 18)
                        {
                            player_dismissed = rowOfDelivery[18];
                        }
                        if(player_dismissed.length() > 1)
                        {
                            wickets += 1;
                        }

                totalWicketsMap.put(bowler,wickets);
            }
            for(Map.Entry wickets : totalWicketsMap.entrySet())
            {
                System.out.println("Total Number of Wickets by " + wickets.getKey() + " is : " + wickets.getValue());
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
