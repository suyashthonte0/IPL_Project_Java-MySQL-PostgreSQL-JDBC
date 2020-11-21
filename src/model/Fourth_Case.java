package model;

import Service.Delivery;
import Service.Match;
import java.util.*;

public class Fourth_Case {
    public static void findTopEconomicalBowlersInYear2015(List<Match> matchesList, List<Delivery> deliveriesList) {
        ArrayList<String> idList = new ArrayList<String >();
        for(Match match : matchesList)
        {
            String season = match.getSeason();
            String id = match.getId();
            if(season.equals("2015"))
            {
                idList.add(id);
            }
        }
        HashMap<String,Double> totalNoOfBalls = new HashMap<String, Double>();
        HashMap<String,Double> totalRunsConceded = new HashMap<String, Double>();
        HashMap<String,Double> economyRateOfBowlers = new HashMap<>();
        double noOfBalls = 0;
        double runsConceded = 0;
        for(Delivery delivery : deliveriesList)
        {
            for(String ids : idList)
            {
                if(ids.equals(delivery.getId()))
                {
                    String bowler = delivery.getBowler();
                    if(totalRunsConceded.get(bowler)!=null) {
                        runsConceded = totalRunsConceded.get(bowler) + Integer.parseInt(delivery.getWide_runs()) + Integer.parseInt(delivery.getNoball_runs()) + Integer.parseInt(delivery.getBatsman_runs());
                    }

                    if(totalNoOfBalls.get(bowler)!=null) {
                        noOfBalls = totalNoOfBalls.get(bowler) + 1;
                    }

                    totalRunsConceded.put(bowler,runsConceded);
                    totalNoOfBalls.put(bowler,noOfBalls);
                }
            }
        }
        for (Map.Entry runsMap : totalRunsConceded.entrySet())
        {
            for(Map.Entry ballsMap : totalNoOfBalls.entrySet())
            {
                if(runsMap.getKey().equals(ballsMap.getKey()))
                {
                    double totalRuns = (double)runsMap.getValue();
                    double totalBalls = (double)ballsMap.getValue();
                    double economyOfBowler = (totalRuns*6.0)/totalBalls;
                    String str = String.format("%.2f",economyOfBowler);
                    economyOfBowler = Double.valueOf(str);
                    String bowler = (String)runsMap.getKey();
                    economyRateOfBowlers.put(bowler,economyOfBowler);
                }
            }
        }

        Map<String, Double> sortedEconomyRateOfBowlers = sortEconomyRates(economyRateOfBowlers);
        for (Map.Entry<String, Double> economyRate : sortedEconomyRateOfBowlers.entrySet())
        {
            System.out.print("Key: " + economyRate.getKey());
            System.out.println(" Value: " + economyRate.getValue());
        }
    }

    public static LinkedHashMap<String, Double> sortEconomyRates(HashMap<String, Double> economyRateOfBowlers) {
        List<Map.Entry<String, Double>> economyList = new LinkedList<>(Collections.unmodifiableSet(economyRateOfBowlers.entrySet()));

        Collections.sort(economyList, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));

        LinkedHashMap<String, Double> sortedEconomy = new LinkedHashMap<>();
        for (Map.Entry<String, Double> economy : economyList)
        {
            sortedEconomy.put(economy.getKey(), economy.getValue());
        }
        return sortedEconomy;
    }
}
