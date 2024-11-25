package Netflix;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserHandler {
    ArrayList<User> users = new ArrayList<>();
    File file;


    public UserHandler(String filePath) {
        this.file = new File(filePath);
    }

    public boolean login(String username, String password) {
        for (User user : users) { //Iterates through the users list.
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) { //Checks if a user with the given username and password exists.
                return true; //Returns true if a match is found, otherwise returns false
            }
        }
        return false;
    }

    public boolean createUser(String fullName, String username, String password) {
        boolean usernameTaken = false;
        if (!ValidPassword(password)) { //Validates the password using the isPasswordValid method.
            return false; //If the password is invalid, returns false.
        }
        for (User users : users) {
            if (users.getUsername().equals(username)) //Checks if the username is already taken
                return false; //If the username exists, returns false.

        }
        users.add(new User(fullName, password, username)); //Creates a new Netflix.User object with the provided details and adds it to the users list
        return true; //Returns true if the user is successfully created.
    }

    public boolean ValidPassword(String password) {
        if(password == null){
            System.out.println("the password is not meeting conditons ");
            return false;
        }
        else{
            return true ;
        }
    }

    public void loadUsers() {
        try (Scanner scanner = new Scanner(file)) { //Opens the file using a Scanner
            while (scanner.hasNextLine()) { //Reads the file line by line.
                String input = scanner.nextLine();
                String[] values = input.split(","); //Splits each line into parts (comma-separated values).
                users.add(new User(values[0], values[1], values[2])); //Creates a new Netflix.User object for each line and adds it to the users list.
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: Unable to load users."); //Prints an error message if the file is not found.
        }
    }
    public void saveUsers(){
        try(FileWriter writer = new FileWriter(file)){ //Opens the file for writing using a FileWriter.
            for (User users : users){ //Iterates over the users list and writes each user's data to the file
                writer.write(users.getFullName() +"," +users.getUsername() +","+ users.getPassword()+ "\n");
                //Each user is saved in the format: fullName,username,password.
            }
        } catch (IOException e) {
            System.out.println("system is not working currently ");

        }

    }

}