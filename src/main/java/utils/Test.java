package utils;

import com.opencsv.CSVReader;

import java.io.*;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        int check = 0;
        String username = "hungnd";
        String password = "hungnd";
        try {
            CSVReader reader2 = new CSVReader(new FileReader("/home/thinhvn/Documents/java/assignment/assignment1/src/main/resources/database/assignment1.csv"));
            List<String[]> allElements = reader2.readAll();
            System.out.println(allElements.size());
            FileWriter csvWriter = new FileWriter("/home/thinhvn/Documents/java/assignment/assignment1/src/main/resources/database/assignment1.csv");
            BufferedReader csvReader =
                    new BufferedReader(new FileReader("/home/thinhvn/Documents/java/assignment/assignment1/src/main/resources/database/assignment1.csv"));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                if(data[0].equals(username)) {
                    data[1] = password;
                    check = 1;
                    break;
                }
            }
            csvWriter.flush();
            csvWriter.close();
            csvReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
