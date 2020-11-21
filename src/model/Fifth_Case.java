package model;

import Service.Delivery;
import java.util.*;

public class Fifth_Case {
    public static void findNumberOfWicketsByEachBowlerInAllSeasons(List<Delivery> deliveriesList) {
        HashMap<String,Integer> totalWicketsByEachBowler = new HashMap<>();
        for(Delivery delivery : deliveriesList)
        {
            String bowler = delivery.getBowler();
            int wickets = 0;
            if(totalWicketsByEachBowler.get(bowler)!=null)
            {
                wickets = totalWicketsByEachBowler.get(bowler);
            }
            String playerDismissed = "";
            if(delivery.getPlayer_dismissed() != null)
            {
                playerDismissed = delivery.getPlayer_dismissed();
            }
            if(playerDismissed.length() > 1)
            {
                wickets += 1;
            }

            totalWicketsByEachBowler.put(bowler,wickets);
        }
        for(Map.Entry wicketsByBowler : totalWicketsByEachBowler.entrySet())
        {
            System.out.println("Total Number of Wickets by " + wicketsByBowler.getKey() + " is : " + wicketsByBowler.getValue());
        }
    }
}
