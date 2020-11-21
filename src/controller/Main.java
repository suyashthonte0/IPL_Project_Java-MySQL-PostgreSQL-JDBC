package controller;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import Service.Delivery;
import Service.Match;
import model.*;

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

    private static List<Match> getMatchesData(String csvFile){
        List<Match> matches = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line = "";
            while((line = br.readLine())!= null)
            {
                String[] row = line.split(",");
                Match match = new Match();
                match.setId(row[MATCH_ID]);
                match.setSeason(row[MATCH_SEASON]);
                match.setCity(row[MATCH_CITY]);
                match.setDate(row[MATCH_DATE]);
                match.setTeam1(row[MATCH_TEAM1]);
                match.setTeam2(row[MATCH_TEAM2]);
                match.setToss_winner(row[MATCH_TOSS_WINNER]);
                match.setToss_decision(row[MATCH_TOSS_DECISION]);
                match.setResult(row[MATCH_RESULT]);
                match.setDl_applied(row[MATCH_DL_APPLIED]);
                match.setWinner(row[MATCH_WINNER]);
                match.setWin_by_runs(row[MATCH_WIN_BY_RUNS]);
                match.setWin_by_wickets(row[MATCH_WIN_BY_WICKETS]);
                match.setPlayer_of_match(row[MATCH_PLAYER_OF_MATCH]);
                match.setVenue(row[MATCH_VENUE]);
                if(row.length > MATCH_VENUE + 1) {
                    match.setUmpire1(row[MATCH_UMPIRE1]);
                }
                if(row.length > MATCH_UMPIRE1 + 1) {
                    match.setUmpire2(row[MATCH_UMPIRE2]);
                }//match.setUmpire3(row[MATCH_UMPIRE3]);

                matches.add(match);
            }

            br.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return matches;
    }

    private static List<Delivery> getDeliveriesData(String csvFile) {
        List<Delivery> deliveries = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line = "";
            while((line = br.readLine())!= null)
            {
                String[] row = line.split(",");
                Delivery delivery = new Delivery();
                delivery.setId(row[DELIVERY_MATCH_ID]);
                delivery.setInning(row[DELIVERY_INNING]);
                delivery.setBatting_team(row[DELIVERY_BATTING_TEAM]);
                delivery.setBowling_team(row[DELIVERY_BOWLING_TEAM]);
                delivery.setOver(row[DELIVERY_OVER]);
                delivery.setBall(row[DELIVERY_BALL]);
                delivery.setBatsman(row[DELIVERY_BATSMAN]);
                delivery.setNon_striker(row[DELIVERY_NON_STRIKER]);
                delivery.setBowler(row[DELIVERY_BOWLER]);
                delivery.setIs_super_over(row[DELIVERY_IS_SUPER_OVER]);
                delivery.setWide_runs(row[DELIVERY_WIDE_RUNS]);
                delivery.setBye_runs(row[DELIVERY_BYE_RUNS]);
                delivery.setLeg_bye_runs(row[DELIVERY_LEG_BYE_RUNS]);
                delivery.setNoball_runs(row[DELIVERY_NOBALL_RUNS]);
                delivery.setPenalty_runs(row[DELIVERY_PENALTY_RUNS]);
                delivery.setBatsman_runs(row[DELIVERY_BATSMAN_RUNS]);
                delivery.setExtra_runs(row[DELIVERY_EXTRA_RUNS]);
                delivery.setTotal_runs(row[DELIVERY_TOTAL_RUNS]);
                if(row.length > DELIVERY_TOTAL_RUNS + 1) {
                    delivery.setPlayer_dismissed(row[DELIVERY_PLAYER_DISMISSED]);
                }
                if(row.length > DELIVERY_PLAYER_DISMISSED + 1) {
                    delivery.setDismissal_kind(row[DELIVERY_DISMISSAL_KIND]);
                }
                if(row.length > DELIVERY_DISMISSAL_KIND + 1){
                    delivery.setFielder(row[DELIVERY_FIELDER]);
                }

                deliveries.add(delivery);
            }
            br.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return deliveries;
    }

    public static void main(String[] args)
    {
        String matchesCSV = "matches.csv";
        String deliveriesCSV = "deliveries.csv";
        List<Match> matchesList = getMatchesData(matchesCSV);
        List<Delivery> deliveriesList = getDeliveriesData(deliveriesCSV);

        Scanner sc = new Scanner(System.in);
        boolean option = true;
        while(option)
        {
            System.out.println("\n\n ******************************* Options Menu !!!  **************************** \n\n");
            System.out.println("1) Number of matches played per year of all the years in IPL.");
            System.out.println("2) Number of matches won of all teams over all the years of IPL.");
            System.out.println("3) For the year 2016 get the extra runs conceded per team.");
            System.out.println("4) For the year 2015 get the top economical bowlers.");
            System.out.println("5) Create your own scenario :->> Number of wickets taken by each Bowler for all the years.");
            System.out.println("6) Exit.\n");
            int ch = sc.nextInt();
            System.out.println();

            switch (ch)
            {
                case 1 :    First_Case case1 = new First_Case();
                            case1.findTheNumberOfMatchesPlayedPerYear(matchesList);
                            break;
                case 2 :    Second_Case case2 = new Second_Case();
                            case2.findTheNumberOfMatchesWon(matchesList);
                            break;
                case 3 :    Third_Case case3 = new Third_Case();
                            case3.findExtraRunsConcededInYear2016(matchesList, deliveriesList);
                            break;
                case 4 :    Fourth_Case case4 = new Fourth_Case();
                            case4.findTopEconomicalBowlersInYear2015(matchesList, deliveriesList);
                            break;
                case 5 :    Fifth_Case case5 = new Fifth_Case();
                            case5.findNumberOfWicketsByEachBowlerInAllSeasons(deliveriesList);
                            break;
                case 6 :    option = false;
                            break;
                default:
                    System.out.println("Invalid Choice !!");

            }
        }
    }


}
