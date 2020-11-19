package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;
import java.util.*;
import java.lang.*;
import java.text.DecimalFormat;

public class Fourth_Case {


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

                if(season.equals("2015"))
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
            HashMap<String,Double> totalBallsMap = new HashMap<String, Double>();
            HashMap<String,Double> runsConcededMap = new HashMap<String, Double>();
            HashMap<String,Double> economyMap = new HashMap<>();
            double noOfBalls = 0;
            double runsConceded = 0.0;
            while((line = br.readLine()) != null)
            {
                String[] rowOfDelivery = line.split(",");
                for(String ids : idList)
                {
                    if(ids.equals(rowOfDelivery[0]))
                    {
                        //String team = rowOfDelivery[3];
                        String bowler = rowOfDelivery[8];

                        //runsConcededMap.remove("bowler");

                        if(runsConcededMap.get(bowler)!=null) {
                            runsConceded = runsConcededMap.get(bowler) + Integer.parseInt(rowOfDelivery[10]) + Integer.parseInt(rowOfDelivery[13]) + Integer.parseInt(rowOfDelivery[15]);
                        }

                       // totalBallsMap.remove("bowler");
                        if(totalBallsMap.get(bowler)!=null) {
                            noOfBalls = totalBallsMap.get(bowler) + 1;
                        }

                        runsConcededMap.put(bowler,runsConceded);
                        totalBallsMap.put(bowler,noOfBalls);
                    }
                }
            }

            for (Map.Entry runs : runsConcededMap.entrySet())
            {
                for(Map.Entry balls : totalBallsMap.entrySet())
                {
                    if(runs.getKey().equals(balls.getKey()))
                    {
                        double totalRuns = (double)runs.getValue();
                        double totalBalls = (double)balls.getValue();
                        double economyOfBowler = (totalRuns*6.0)/totalBalls;
                        String str = String.format("%.2f",economyOfBowler);
                        economyOfBowler = Double.valueOf(str);
                        String bowler = (String)runs.getKey();
                        economyMap.put(bowler,economyOfBowler);
                    }
                }
            }
            Map<String, Double> result = sortEconomyMap(economyMap);

            for (Map.Entry<String, Double> entry : result.entrySet())
            {
                System.out.print("Key: " + entry.getKey());
                System.out.println(" Value: " + entry.getValue());
            }

            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    public static LinkedHashMap<String, Double> sortEconomyMap(HashMap<String, Double> map) {
        List<Map.Entry<String, Double>> economyList = new LinkedList<>(Collections.unmodifiableSet(map.entrySet()));

        Collections.sort(economyList, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));

        LinkedHashMap<String, Double> sortedEconomy = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : economyList)
        {
            sortedEconomy.put(entry.getKey(), entry.getValue());
        }

        return sortedEconomy;
    }

}
