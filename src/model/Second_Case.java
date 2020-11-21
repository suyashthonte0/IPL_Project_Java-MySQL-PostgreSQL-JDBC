package model;

import Service.Match;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Second_Case {
    public static void findTheNumberOfMatchesWon(List<Match> matchesList) {
        TreeMap<String,Integer> noOfMatchesWon = new TreeMap<String, Integer>();
        int count = 0;
        for(Match match : matchesList)
        {
            String team = match.getTeam1();
            String winner = match.getWinner();
            noOfMatchesWon.remove("team1");
            if(noOfMatchesWon.containsKey(team))
            {
                count = noOfMatchesWon.get(team);
                if(team.equals(winner))
                {
                    count += 1;
                }
            }
            else{
                if(team.equals(winner))
                {
                    count = 1;
                }
            }
            noOfMatchesWon.put(team,count);
        }
        for (Map.Entry matchesPlayed : noOfMatchesWon.entrySet())
        {
            System.out.println("'" + matchesPlayed.getKey() + "'" + " won number of matches : " + matchesPlayed.getValue());
        }
    }
}
