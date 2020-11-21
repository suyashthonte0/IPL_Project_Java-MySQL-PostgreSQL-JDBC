package model;

import Service.Delivery;
import Service.Match;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Third_Case {
    public static void findExtraRunsConcededInYear2016(List<Match> matchesList, List<Delivery> deliveriesList) {
        ArrayList<String> idList = new ArrayList<String >();
        for(Match match : matchesList)
        {
            String season = match.getSeason();
            String id = match.getId();
            if(season.equals("2016"))
            {
                idList.add(id);
            }
        }
        TreeMap<String,Integer> extraRunsConceded = new TreeMap<>();
        for(Delivery delivery : deliveriesList)
        {
            for(String ids : idList)
            {
                if(ids.equals(delivery.getId()))
                {
                    String team = delivery.getBowling_team();
                    int totalExtraRuns = 0;
                    int extraRuns = Integer.parseInt(delivery.getExtra_runs());
                    extraRunsConceded.remove("bowling_team");

                    if(extraRunsConceded.containsKey(team))
                    {
                        totalExtraRuns = extraRunsConceded.get(team);
                        totalExtraRuns += extraRuns;
                    }
                    else
                    {
                        totalExtraRuns = extraRuns;
                    }
                    extraRunsConceded.put(team,totalExtraRuns);
                }
            }
        }
        for (Map.Entry extraRuns : extraRunsConceded.entrySet())
        {
            System.out.println(extraRuns.getKey() + " is " + extraRuns.getValue());
        }
    }
}
