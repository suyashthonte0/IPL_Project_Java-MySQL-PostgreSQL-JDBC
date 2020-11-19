package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Data {


    public static void readDeliveries(String deliveriesCSV) {
        try {

            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(deliveriesCSV));

            while ((line = br.readLine()) != null) {
                String[] rowOfDelivery = line.split(",");
                for (int i = 0 ; i < rowOfDelivery.length ; i++) {

                   System.out.print(rowOfDelivery[17]);
                }
                System.out.println();
            }

        }catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    public static void main(String[] args) {
        // csv file to read
        String deliveriesCSV = "deliveries.csv";
        Data.readDeliveries(deliveriesCSV);
    }
}
