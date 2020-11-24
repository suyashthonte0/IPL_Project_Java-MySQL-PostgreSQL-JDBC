package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import Service.Delivery;
import Service.Match;

public class Main {
    public static final int MATCH_ID = 0;
    public static final int MATCH_SEASON = 1;
    public static final int MATCH_CITY = 2;
    public static final int MATCH_DATE = 3;
    public static final int MATCH_TEAM1 = 4;
    public static final int MATCH_TEAM2 = 5;
    public static final int MATCH_TOSS_WINNER = 6;
    public static final int MATCH_TOSS_DECISION = 7;
    public static final int MATCH_RESULT = 8;
    public static final int MATCH_DL_APPLIED = 9;
    public static final int MATCH_WINNER = 10;
    public static final int MATCH_WIN_BY_RUNS = 11;
    public static final int MATCH_WIN_BY_WICKETS = 12;
    public static final int MATCH_PLAYER_OF_MATCH = 13;
    public static final int MATCH_VENUE = 14;
    public static final int MATCH_UMPIRE1 = 15;
    public static final int MATCH_UMPIRE2 = 16;
    public static final int MATCH_UMPIRE3 = 17;

    public static final int DELIVERY_MATCH_ID = 0;
    public static final int DELIVERY_INNING = 1;
    public static final int DELIVERY_BATTING_TEAM = 2;
    public static final int DELIVERY_BOWLING_TEAM = 3;
    public static final int DELIVERY_OVER = 4;
    public static final int DELIVERY_BALL = 5;
    public static final int DELIVERY_BATSMAN = 6;
    public static final int DELIVERY_NON_STRIKER = 7;
    public static final int DELIVERY_BOWLER = 8;
    public static final int DELIVERY_IS_SUPER_OVER = 9;
    public static final int DELIVERY_WIDE_RUNS = 10;
    public static final int DELIVERY_BYE_RUNS = 11;
    public static final int DELIVERY_LEG_BYE_RUNS = 12;
    public static final int DELIVERY_NOBALL_RUNS = 13;
    public static final int DELIVERY_PENALTY_RUNS = 14;
    public static final int DELIVERY_BATSMAN_RUNS = 15;
    public static final int DELIVERY_EXTRA_RUNS = 16;
    public static final int DELIVERY_TOTAL_RUNS = 17;
    public static final int DELIVERY_PLAYER_DISMISSED = 18;
    public static final int DELIVERY_DISMISSAL_KIND = 19;
    public static final int DELIVERY_FIELDER = 20;

    private static List<Match> getMatchesData() {
        String csvFile = "matches.csv";
        List<Match> matches = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line = "";
            int iteration = 0;
            while ((line = br.readLine()) != null) {
                if (iteration == 0) {
                    iteration++;
                    continue;
                }
                String[] row = line.split(",");
                Match match = new Match();
                match.setId(row[MATCH_ID]);
                match.setSeason(row[MATCH_SEASON]);
                match.setCity(row[MATCH_CITY]);
                match.setDate(row[MATCH_DATE]);
                match.setTeam1(row[MATCH_TEAM1]);
                match.setTeam2(row[MATCH_TEAM2]);
                match.setTossWinner(row[MATCH_TOSS_WINNER]);
                match.setTossDecision(row[MATCH_TOSS_DECISION]);
                match.setResult(row[MATCH_RESULT]);
                match.setDlApplied(row[MATCH_DL_APPLIED]);
                match.setWinner(row[MATCH_WINNER]);
                match.setWinByRuns(row[MATCH_WIN_BY_RUNS]);
                match.setWinByWickets(row[MATCH_WIN_BY_WICKETS]);
                match.setPlayerOfMatch(row[MATCH_PLAYER_OF_MATCH]);
                match.setVenue(row[MATCH_VENUE]);
                if (row.length > MATCH_VENUE + 1) {
                    match.setUmpire1(row[MATCH_UMPIRE1]);
                }
                if (row.length > MATCH_UMPIRE1 + 1) {
                    match.setUmpire2(row[MATCH_UMPIRE2]);
                }

                matches.add(match);
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return matches;
    }

    private static List<Delivery> getDeliveriesData() {
        String csvFile = "deliveries.csv";
        List<Delivery> deliveries = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line = "";
            int iteration = 0;
            while ((line = br.readLine()) != null) {
                if (iteration == 0) {
                    iteration++;
                    continue;
                }
                String[] row = line.split(",");
                Delivery delivery = new Delivery();
                delivery.setId(row[DELIVERY_MATCH_ID]);
                delivery.setInning(row[DELIVERY_INNING]);
                delivery.setBattingTeam(row[DELIVERY_BATTING_TEAM]);
                delivery.setBowlingTeam(row[DELIVERY_BOWLING_TEAM]);
                delivery.setOver(row[DELIVERY_OVER]);
                delivery.setBall(row[DELIVERY_BALL]);
                delivery.setBatsman(row[DELIVERY_BATSMAN]);
                delivery.setNonStriker(row[DELIVERY_NON_STRIKER]);
                delivery.setBowler(row[DELIVERY_BOWLER]);
                delivery.setIsSuperOver(row[DELIVERY_IS_SUPER_OVER]);
                delivery.setWideRuns(row[DELIVERY_WIDE_RUNS]);
                delivery.setByeRuns(row[DELIVERY_BYE_RUNS]);
                delivery.setLegByeRuns(row[DELIVERY_LEG_BYE_RUNS]);
                delivery.setNoBallRuns(row[DELIVERY_NOBALL_RUNS]);
                delivery.setPenaltyRuns(row[DELIVERY_PENALTY_RUNS]);
                delivery.setBatsmanRuns(row[DELIVERY_BATSMAN_RUNS]);
                delivery.setExtraRuns(row[DELIVERY_EXTRA_RUNS]);
                delivery.setTotalRuns(row[DELIVERY_TOTAL_RUNS]);
                if (row.length > DELIVERY_TOTAL_RUNS + 1) {
                    delivery.setPlayerDismissed(row[DELIVERY_PLAYER_DISMISSED]);
                }
                if (row.length > DELIVERY_PLAYER_DISMISSED + 1) {
                    delivery.setDismissalKind(row[DELIVERY_DISMISSAL_KIND]);
                }
                if (row.length > DELIVERY_DISMISSAL_KIND + 1) {
                    delivery.setFielder(row[DELIVERY_FIELDER]);
                }

                deliveries.add(delivery);
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return deliveries;
    }

    public static void main(String[] args) {
        List<Match> matchesList = getMatchesData();
        List<Delivery> deliveriesList = getDeliveriesData();
        findTheNumberOfMatchesPlayedPerYear(matchesList);
        findTheNumberOfMatchesWon(matchesList);
        findExtraRunsConcededInYear2016(matchesList, deliveriesList);
        findTopEconomicalBowlersInYear2015(matchesList, deliveriesList);
        findNumberOfWicketsByEachBowlerInAllSeasons(deliveriesList);
    }

    public static void findTheNumberOfMatchesPlayedPerYear(List<Match> matchesList) {
        TreeMap<String, Integer> noOfMatchesPlayedPerYear = new TreeMap<>();
        int count = 0;
        for (Match match : matchesList) {
            String year = match.getSeason();
            if (noOfMatchesPlayedPerYear.containsKey(year)) {
                count += 1;
            } else {
                count = 1;
            }

            noOfMatchesPlayedPerYear.put(year, count);
        }
        for (Map.Entry matchesPlayed : noOfMatchesPlayedPerYear.entrySet()) {
            System.out.println("Year : " + matchesPlayed.getKey() + " , Number of matches played in " + matchesPlayed.getKey() + ": " + matchesPlayed.getValue());
        }
        System.out.println();
    }

    public static void findTheNumberOfMatchesWon(List<Match> matchesList) {
        TreeMap<String, Integer> noOfMatchesWon = new TreeMap<String, Integer>();
        int count = 0;
        for (Match match : matchesList) {
            String team = match.getTeam1();
            String winner = match.getWinner();
            if (noOfMatchesWon.containsKey(team)) {
                count = noOfMatchesWon.get(team);
                if (team.equals(winner)) {
                    count += 1;
                }
            } else {
                if (team.equals(winner)) {
                    count = 1;
                }
            }
            noOfMatchesWon.put(team, count);
        }
        for (Map.Entry matchesPlayed : noOfMatchesWon.entrySet()) {
            System.out.println("'" + matchesPlayed.getKey() + "'" + " won number of matches : " + matchesPlayed.getValue());
        }
        System.out.println();
    }

    public static void findExtraRunsConcededInYear2016(List<Match> matches, List<Delivery> deliveries) {
        ArrayList<String> idList = new ArrayList<String>();
        for (Match match : matches) {
            if (match.getSeason().equals("2016")) {
                idList.add(match.getId());
            }
        }
        TreeMap<String, Integer> extraRunsConceded = new TreeMap<>();
        for (Delivery delivery : deliveries) {
            for (String ids : idList) {
                if (ids.equals(delivery.getId())) {
                    String team = delivery.getBowlingTeam();
                    int totalExtraRuns = 0;
                    int extraRuns = Integer.parseInt(delivery.getExtraRuns());

                    if (extraRunsConceded.containsKey(team)) {
                        totalExtraRuns = extraRunsConceded.get(team);
                        totalExtraRuns += extraRuns;
                    } else {
                        totalExtraRuns = extraRuns;
                    }
                    extraRunsConceded.put(team, totalExtraRuns);
                }
            }
        }
        for (Map.Entry extraRuns : extraRunsConceded.entrySet()) {
            System.out.println(extraRuns.getKey() + " is " + extraRuns.getValue());
        }
        System.out.println();
    }

    public static void findTopEconomicalBowlersInYear2015(List<Match> matchesList, List<Delivery> deliveriesList) {
        ArrayList<String> idList = new ArrayList<String>();
        for (Match match : matchesList) {
            String season = match.getSeason();
            String id = match.getId();
            if (season.equals("2015")) {
                idList.add(id);
            }
        }
        HashMap<String, Double> totalNoOfBalls = new HashMap<String, Double>();
        HashMap<String, Double> totalRunsConceded = new HashMap<String, Double>();
        HashMap<String, Double> economyRateOfBowlers = new HashMap<>();
        double noOfBalls = 0;
        double runsConceded = 0;
        for (Delivery delivery : deliveriesList) {
            for (String ids : idList) {
                if (ids.equals(delivery.getId())) {
                    String bowler = delivery.getBowler();
                    if (totalRunsConceded.get(bowler) != null) {
                        runsConceded = totalRunsConceded.get(bowler) + Integer.parseInt(delivery.getWideRuns()) + Integer.parseInt(delivery.getNoBallRuns()) + Integer.parseInt(delivery.getBatsmanRuns());
                    }

                    if (totalNoOfBalls.get(bowler) != null) {
                        noOfBalls = totalNoOfBalls.get(bowler) + 1;
                    }

                    totalRunsConceded.put(bowler, runsConceded);
                    totalNoOfBalls.put(bowler, noOfBalls);
                }
            }
        }
        for (Map.Entry runsMap : totalRunsConceded.entrySet()) {
            for (Map.Entry ballsMap : totalNoOfBalls.entrySet()) {
                if (runsMap.getKey().equals(ballsMap.getKey())) {
                    double totalRuns = (double) runsMap.getValue();
                    double totalBalls = (double) ballsMap.getValue();
                    double economyOfBowler = (totalRuns * 6.0) / totalBalls;
                    String str = String.format("%.2f", economyOfBowler);
                    economyOfBowler = Double.valueOf(str);
                    String bowler = (String) runsMap.getKey();
                    economyRateOfBowlers.put(bowler, economyOfBowler);
                }
            }
        }

        Map<String, Double> sortedEconomyRateOfBowlers = sortEconomyRates(economyRateOfBowlers);
        for (Map.Entry<String, Double> economyRate : sortedEconomyRateOfBowlers.entrySet()) {
            System.out.print("Key: " + economyRate.getKey());
            System.out.println(" Value: " + economyRate.getValue());
        }
        System.out.println();
    }

    public static LinkedHashMap<String, Double> sortEconomyRates(HashMap<String, Double> economyRateOfBowlers) {
        List<Map.Entry<String, Double>> economyList = new LinkedList<>(Collections.unmodifiableSet(economyRateOfBowlers.entrySet()));

        Collections.sort(economyList, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));

        LinkedHashMap<String, Double> sortedEconomy = new LinkedHashMap<>();
        for (Map.Entry<String, Double> economy : economyList) {
            sortedEconomy.put(economy.getKey(), economy.getValue());
        }
        return sortedEconomy;
    }

    public static void findNumberOfWicketsByEachBowlerInAllSeasons(List<Delivery> deliveriesList) {
        HashMap<String, Integer> totalWicketsByEachBowler = new HashMap<>();
        for (Delivery delivery : deliveriesList) {
            String bowler = delivery.getBowler();
            int wickets = 0;
            if (totalWicketsByEachBowler.get(bowler) != null) {
                wickets = totalWicketsByEachBowler.get(bowler);
            }
            String playerDismissed = "";
            if (delivery.getPlayerDismissed() != null) {
                playerDismissed = delivery.getPlayerDismissed();
            }
            if (playerDismissed.length() > 1) {
                wickets += 1;
            }

            totalWicketsByEachBowler.put(bowler, wickets);
        }
        for (Map.Entry wicketsByBowler : totalWicketsByEachBowler.entrySet()) {
            System.out.println("Total Number of Wickets by " + wicketsByBowler.getKey() + " is : " + wicketsByBowler.getValue());
        }
        System.out.println();
    }
}
