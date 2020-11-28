package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import Service.Delivery;
import Service.Match;

public class Main {
    public static final int MATCH_ID = 1;
    public static final int MATCH_SEASON = 2;
    public static final int MATCH_CITY = 3;
    public static final int MATCH_DATE = 4;
    public static final int MATCH_TEAM1 = 5;
    public static final int MATCH_TEAM2 = 6;
    public static final int MATCH_TOSS_WINNER = 7;
    public static final int MATCH_TOSS_DECISION = 8;
    public static final int MATCH_RESULT = 9;
    public static final int MATCH_DL_APPLIED = 10;
    public static final int MATCH_WINNER = 11;
    public static final int MATCH_WIN_BY_RUNS = 12;
    public static final int MATCH_WIN_BY_WICKETS = 13;
    public static final int MATCH_PLAYER_OF_MATCH = 14;
    public static final int MATCH_VENUE = 15;
    public static final int MATCH_UMPIRE1 = 16;
    public static final int MATCH_UMPIRE2 = 17;
    public static final int MATCH_UMPIRE3 = 18;

    public static final int DELIVERY_MATCH_ID = 1;
    public static final int DELIVERY_INNING = 2;
    public static final int DELIVERY_BATTING_TEAM = 3;
    public static final int DELIVERY_BOWLING_TEAM = 4;
    public static final int DELIVERY_OVER = 5;
    public static final int DELIVERY_BALL = 6;
    public static final int DELIVERY_BATSMAN = 7;
    public static final int DELIVERY_NON_STRIKER = 8;
    public static final int DELIVERY_BOWLER = 9;
    public static final int DELIVERY_IS_SUPER_OVER = 10;
    public static final int DELIVERY_WIDE_RUNS = 11;
    public static final int DELIVERY_BYE_RUNS = 12;
    public static final int DELIVERY_LEG_BYE_RUNS = 13;
    public static final int DELIVERY_NOBALL_RUNS = 14;
    public static final int DELIVERY_PENALTY_RUNS = 15;
    public static final int DELIVERY_BATSMAN_RUNS = 16;
    public static final int DELIVERY_EXTRA_RUNS = 17;
    public static final int DELIVERY_TOTAL_RUNS = 18;
    public static final int DELIVERY_PLAYER_DISMISSED = 19;
    public static final int DELIVERY_DISMISSAL_KIND = 20;
    public static final int DELIVERY_FIELDER = 21;

    private static String URL = "jdbc:postgresql://localhost:5432/ipl_project_postgresql";
    private static String user = "postgres";
    private static String password = "suya9780";
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    private static List<Match> getMatchesData() throws SQLException {
        String csvFile = "matches.csv";
        List<Match> matches = new ArrayList<>();
            resultSet = statement.executeQuery("select * from matches");
            while(resultSet.next())
            {
                Match match = new Match();
                match.setId(resultSet.getString(MATCH_ID));
                match.setSeason(resultSet.getString(MATCH_SEASON));
                match.setCity(resultSet.getString(MATCH_CITY));
                match.setDate(resultSet.getString(MATCH_DATE));
                match.setTeam1(resultSet.getString(MATCH_TEAM1));
                match.setTeam2(resultSet.getString(MATCH_TEAM2));
                match.setTossWinner(resultSet.getString(MATCH_TOSS_WINNER));
                match.setTossDecision(resultSet.getString(MATCH_TOSS_DECISION));
                match.setResult(resultSet.getString(MATCH_RESULT));
                match.setDlApplied(resultSet.getString(MATCH_DL_APPLIED));
                match.setWinner(resultSet.getString(MATCH_WINNER));
                match.setWinByRuns(resultSet.getString(MATCH_WIN_BY_RUNS));
                match.setWinByWickets(resultSet.getString(MATCH_WIN_BY_WICKETS));
                match.setPlayerOfMatch(resultSet.getString(MATCH_PLAYER_OF_MATCH));
                match.setVenue(resultSet.getString(MATCH_VENUE));

                matches.add(match);
            }
        return matches;
    }

    private static List<Delivery> getDeliveriesData() throws SQLException {
        String csvFile = "deliveries.csv";
        List<Delivery> deliveries = new ArrayList<>();
            resultSet = statement.executeQuery("select * from deliveries");
            while(resultSet.next())
            {
                Delivery delivery = new Delivery();
                delivery.setId(resultSet.getString(DELIVERY_MATCH_ID));
                delivery.setInning(resultSet.getString(DELIVERY_INNING));
                delivery.setBattingTeam(resultSet.getString(DELIVERY_BATTING_TEAM));
                delivery.setBowlingTeam(resultSet.getString(DELIVERY_BOWLING_TEAM));
                delivery.setOver(resultSet.getString(DELIVERY_OVER));
                delivery.setBall(resultSet.getString(DELIVERY_BALL));
                delivery.setBatsman(resultSet.getString(DELIVERY_BATSMAN));
                delivery.setNonStriker(resultSet.getString(DELIVERY_NON_STRIKER));
                delivery.setBowler(resultSet.getString(DELIVERY_BOWLER));
                delivery.setIsSuperOver(resultSet.getString(DELIVERY_IS_SUPER_OVER));
                delivery.setWideRuns(resultSet.getString(DELIVERY_WIDE_RUNS));
                delivery.setByeRuns(resultSet.getString(DELIVERY_BYE_RUNS));
                delivery.setLegByeRuns(resultSet.getString(DELIVERY_LEG_BYE_RUNS));
                delivery.setNoBallRuns(resultSet.getString(DELIVERY_NOBALL_RUNS));
                delivery.setPenaltyRuns(resultSet.getString(DELIVERY_PENALTY_RUNS));
                delivery.setBatsmanRuns(resultSet.getString(DELIVERY_BATSMAN_RUNS));
                delivery.setExtraRuns(resultSet.getString(DELIVERY_EXTRA_RUNS));
                delivery.setTotalRuns(resultSet.getString(DELIVERY_TOTAL_RUNS));
                delivery.setPlayerDismissed(resultSet.getString(DELIVERY_PLAYER_DISMISSED));
                delivery.setDismissalKind(resultSet.getString(DELIVERY_DISMISSAL_KIND));
                delivery.setFielder(resultSet.getString(DELIVERY_FIELDER));

                deliveries.add(delivery);
            }
        return deliveries;
    }

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver" );
            connection = DriverManager.getConnection(URL, user, password);
            statement = connection.createStatement();
            List<Match> matchesList = getMatchesData();
            List<Delivery> deliveriesList = getDeliveriesData();
            findTheNumberOfMatchesPlayedPerYear(matchesList);
            findTheNumberOfMatchesWon(matchesList);
            findExtraRunsConcededInYear2016(matchesList, deliveriesList);
            findTopEconomicalBowlersInYear2015(matchesList, deliveriesList);
            findNumberOfWicketsByEachBowlerInAllSeasons(deliveriesList);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void findTheNumberOfMatchesPlayedPerYear(List<Match> matchesList) {
        TreeMap<String, Integer> noOfMatchesPlayedPerYear = new TreeMap<>();
        int count = 0;
        for (Match match : matchesList) {
            String year = match.getSeason();
            if (noOfMatchesPlayedPerYear.containsKey(year)) {
                count += 1;
                noOfMatchesPlayedPerYear.put(year, noOfMatchesPlayedPerYear.get(year) + 1);
            } else {
                count = 1;
                noOfMatchesPlayedPerYear.put(year, count);
            }
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
