/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.net.*;
import java.io.*;

public class URLReader {

    public static void main(String[] args) throws Exception {

        String csvFile = "C:/ecommerce_code/csv/urls_F_B.csv"; // Add the csv file path with 2nd column containing the url
        String line = "";
        String cvsSplitBy = ",";

        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        br.readLine();
        while ((line = br.readLine()) != null) {
            try {
                // use comma as separator
                String[] urls = line.split(cvsSplitBy);
                URL oracle = new URL(urls[1]);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(oracle.openStream()));
                System.out.println("URL OK " + urls[1]);

            } catch (IOException e) {
                System.out.println(e);
                continue;
            }
        }
    }
}
