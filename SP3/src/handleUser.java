import Netflix.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class handleUser {
    private ArrayList<User> userList;        //liste over brugere
    private File userFile;                  // fil til at gemme brugernes oplysninger


    public handleUser(String fileName) {
        userList = new ArrayList<>(); //initialiseres userlist som en tom list når en handleuser instans oprettes
        userFile = new File(fileName); // userFile oprettes med det angivne filnavn
    }


    public boolean loginCode(String username, String password) {        //metoden tjekker om et brugnavn og adgangskode matcher nogen af brugerne i uselist
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) { //for hver brugere i listen sammenlignes brugnavnet og adgangskode.
                return true; //hvis de matcher retuneres true
            }
        }
        return false;   //login mislykkedes
    }


    public boolean registerNewUser(String fullName, String username, String password) {     //opretter nye brugere
        if (chekUsernameAvailability(username) || !validatePasswordsStrength(password)) {   // før brugeren oprettesm tjekker loppet om brugnavnet allerede er taget og om adgangskode er stærk.
            return false; //brugeren kunne ikke oprettes
        }
        userList.add(new User(fullName, password, username)); // hvis betingelser er opfyls så tilføjes brugeren til listen
        return true;   //brugeren er oprettet
    }


    private boolean chekUsernameAvailability(String username) { //tjekker om username allerede findes i listen
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return true; //brugernavnet er taget
            }
        }
        return false; //ledigt
    }


     private boolean validatePasswordsStrength(String password) { //tjekker om code er mellem 5 til 10
        return password != null && password.length() >= 5 && password.length() <= 10;
    }



    public void loadUsersFromFile() {       // indlæser brugere fra en fil og tilføjer den til userlist
        try (Scanner scanner = new Scanner(userFile)){  // læser indholdet af userfile
            while (scanner.hasNextLine()) {   //løkken fortsætter så længe der er flere linjer i filen
                String line = scanner.nextLine();   // læser næste linje fra filen og gemmer den i line
                String[] parts = line.split(",");       // deler linjer op i dele vha."," resultatet gemmes i array kaldet parts
                userList.add(new User(parts[0], parts[1], parts[2])); // oprettet for et nyt user ved at bruge de første tre elementer fra parts-arrayet som argumenter (fullname,username,password) og tilføjer til liste
            }
        }catch (FileNotFoundException e) { // hvis filen ikke findes
            System.out.println("File not found"+e.getMessage());
        }
    }


    public void saveUsersToFile() {     // metoden gemmer brugerne fra userlist til userfile
        try (FileWriter writer = new FileWriter(userFile)){ // opretter et filewriter til at skrive til userfile.
            for (User user : userList){ //løkken der itererer gennem hver user instans i userlist
                writer.write(user.getFullName() + "," + user.getUsername() + "," + user.getPassword() + "\n"); // for hver brug skrives brugerens fulname username og password til filen, adskilt af , efterfulgt af et linkeskift
            }
        }catch (IOException e) {    // hvis der opstår en fejl uderskrivning til filen 
            System.out.println("Error saving users"+e.getMessage());
        }
    }
}
