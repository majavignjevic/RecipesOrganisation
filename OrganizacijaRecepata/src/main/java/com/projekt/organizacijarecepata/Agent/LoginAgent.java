package com.projekt.organizacijarecepata.Agent;

import com.projekt.organizacijarecepata.Exceptions.CantReadUsersException;
import com.projekt.organizacijarecepata.entiteti.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Agent class responsible for user login functionality.
 */
public class LoginAgent {

    /**
     * Checks if the provided user credentials are valid.
     *
     * @param user The user object containing the username and password to check.
     * @return True if the user credentials are valid, false otherwise.
     */
    public static Boolean checkUser(User user) {
        List<User> userList = getUsers();
        int count = 0;
        for (User currentUser : userList) {
            if (currentUser.getUsername().equals(user.getUsername()) && currentUser.getPassword().equals(user.getPassword())) {
                count++;
            }
        }

        return count == 1;
    }

    /**
     * Retrieves the list of users from a text file.
     *
     * @return The list of user objects.
     */
    public static List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader("doc/UserList.txt"))) {
            String inputLine;
            int counter = 1;
            User.UserBuilder userInput = new User.UserBuilder();
            while ((inputLine = input.readLine()) != null) {
                switch (counter) {
                    case 1:
                        userInput.setUsername(inputLine);
                        break;
                    case 2:
                        userInput.setPassword(inputLine);
                        userList.add(userInput.build());
                        counter = 0;
                        break;
                }
                counter++;
            }
        } catch (IOException e) {
            System.out.println("File not found!");
            throw new CantReadUsersException("Unable to read users");
        }
        return userList;
    }
}
