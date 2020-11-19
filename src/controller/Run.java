package controller;
import java.util.*;
import model.*;

public class Run {
    public static void main(String[] args)
    {

        Scanner sc = new Scanner(System.in);
        String matchesCSV = "matches.csv";
        String deliveriesCSV = "deliveries.csv";
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
           // Scanner sc = new Scanner(System.in);
            int ch = sc.nextInt();
            System.out.println();
            //String matchesCSV = "matches.csv";
            //String deliveriesCSV = "deliveries.csv";
            switch (ch)
            {
                case 1 :    First_Case case1 = new First_Case();
                            case1.readMatches(matchesCSV);
                            break;
                case 2 :    Second_Case case2 = new Second_Case();
                            case2.readMatches(matchesCSV);
                            break;
                case 3 :    Third_Case case3 = new Third_Case();
                            case3.readMatches(matchesCSV);
                            case3.readDeliveries(deliveriesCSV);
                            break;
                case 4 :    Fourth_Case case4 = new Fourth_Case();
                            case4.readMatches(matchesCSV);
                            case4.readDeliveries(deliveriesCSV);
                            break;
                case 5 :    Fifth_Case case5 = new Fifth_Case();
                            case5.readDeliveries(deliveriesCSV);
                            break;
                case 6 :    option = false;
                            break;
                default:
                    System.out.println("Invalid Choice !!");

            }
        }
        //System
    }
}
