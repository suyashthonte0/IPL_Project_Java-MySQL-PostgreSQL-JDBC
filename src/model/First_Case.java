package model;

import Service.Match;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class First_Case {
    public static void findTheNumberOfMatchesPlayedPerYear(List<Match> matchesList) {
        TreeMap<String, Integer> noOfMatchesPlayedPerYear = new TreeMap<>();
        int count = 0;
        for(Match match : matchesList)
        {
            noOfMatchesPlayedPerYear.remove("season");
            String year = match.getSeason();
            if(noOfMatchesPlayedPerYear.containsKey(year))
            {
                count += 1;
            }
            else{
                count = 1;
            }

            noOfMatchesPlayedPerYear.put(year,count);
        }
        for (Map.Entry matchesPlayed : noOfMatchesPlayedPerYear.entrySet())
        {
            System.out.println("Year : " + matchesPlayed.getKey() + " , Number of matches played in " + matchesPlayed.getKey() + ": " + matchesPlayed.getValue());
        }
    }
}
