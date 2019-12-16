package dao;

import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDAOImpl implements UserDAO{
    @Override
    public User getUserByUsername(String username) {

        User user = null;
        try {
            BufferedReader csvReader =
                    new BufferedReader(new InputStreamReader(
                            getClass().getClassLoader().getResourceAsStream("database/assignment1.csv"))
                    );
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                if(data[0].equals(username)) {
                    user = new User(data[0], data[1], data[2], data[3], data[4], data[5], data[6]);
                    break;
                }
            }
            csvReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return user;

    }

    @Override
    public int addAccountToDB(User user) {
        int result = 0;
        try {
            FileWriter csvWriter = new FileWriter(
                    String.valueOf(getClass().getClassLoader().getResource(
                            "database/assignment1.csv")), true);
            List<String> userString = Arrays.asList(
                    user.getUsername(), user.getPassword(),
                    user.getName(), user.getPhone(),
                    user.getEmail(), user.getAddress(), user.getDateOfBirth());
            csvWriter.append(String.join(",", userString));
            csvWriter.append("\n");
            csvWriter.flush();
            csvWriter.close();
            result = 1;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int changePassword(String username, String password) {
        int check = 0;
        try {
            BufferedReader csvReader =
                    new BufferedReader(new InputStreamReader(
                            getClass().getClassLoader().getResourceAsStream("database/assignment1.csv"))
                    );
            String row;
            List<String[]> strings = new ArrayList<String[]>();
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                if(data[0].equals(username)) {
                    data[1] = password;
                    check = 1;
                }
                strings.add(data);
            }
            FileWriter csvWriter = new FileWriter(
                    String.valueOf(getClass().getClassLoader().getResourceAsStream(
                            "database/assignment1.csv")));
            for(String[] userString: strings) {
                csvWriter.append(String.join(",", userString));
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
            csvReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return check;
    }
}
