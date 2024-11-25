package Netflix;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class TextUI {
    Scanner scan;
    UserHandler userHandler;

    public TextUI(UserHandler userHandler){
        this.userHandler = userHandler;
        this.scan = new Scanner(System.in);
    }

    public String getUserInput(){
        System.out.println("Welcome to Netflix stream");
        System.out.println("Select 1 to log in or 2 to sign up");
        return scan.nextLine();
    }

    public void UserLogin(){
        System.out.println("Please enter your username.");
        String username = scan.nextLine();

        System.out.println("Please enter your password");
        String password = scan.nextLine();
        if(userHandler.login(username,password)){
            System.out.println("Welcome to Chills " + username);
            System.out.println("\n\n");
        } else {
            int passwordAttempts = 0; //initialized to 0 to track the number of failed login attempts.
            while(passwordAttempts < 3){ //his allows the user up to four additional login attempts.
                System.out.println("Your password or username is incorect please try again");
                System.out.println("Please enter your username");
                username = scan.nextLine();
                System.out.println("please enter your password");
                password = scan.nextLine();
                if(userHandler.login(username, password)){
                    System.out.println("Welcome to Netflix stream");
                    break;
                } else {
                    passwordAttempts++;
                } if(passwordAttempts == 4) {
                    System.out.println("Maximum login attempts have been reached. Shutting down...");
                    break;
                }
            }
        }
    }
    public void CreatAUser(){
        System.out.println("Please enter your full name: ");
        String fullname = scan.nextLine();
        System.out.println("Please Creat a username");
        String username = scan.nextLine();
        System.out.println("Please create a password");
        String password = scan.nextLine();

        if(userHandler.createUser(fullname, username, password)){
            userHandler.saveUsers();
            System.out.println("Welcome to Chills  " + username);
            System.out.println("\n\n");
        } else{
            System.out.println("Invalid username or password. Please log in or sign up.");
        }
    }

    public void pickMedia(ArrayList<Media> medias) {

        TextUI textUI = new TextUI(userHandler);
        System.out.println("Menu: Choose type of media: ");

        Scanner scan = new Scanner(System.in);
        System.out.println("1-Movies");
        System.out.println("2-Series");
        System.out.println("3-saved movies");
        int input = scan.nextInt();
        scan.nextLine();
        if (input == 1) {
            System.out.println("You choose movies, here's some options");
            for (int i = 0; i < medias.size(); i++) {
                String movies = i + " - " + medias.get(i).getTitel();
                System.out.println(movies);
            }
            chooseMovie();
            youHaveChosenMovie();

        } else if (input == 2) {
            System.out.println("You chose series, here's some options:");
            for (int i = 0; i < medias.size(); i++) {
                System.out.println((i + 1) + ": " + medias.get(i).getTitel());
            }
            chooseSeries();
            youHaveChosenSeries();
        }  else if (input == 3) {
            System.out.println("You choose your saved list: ");
        }
    }

    public void chooseMovie() {
        System.out.println("\n\n");
        System.out.println("Please press the number of the movie you want to watch");

        try {
            int movieNumberToWatch = scan.nextInt(); // Brug den eksisterende Scanner
            System.out.println("You have chosen: " + choseResults(movieNumberToWatch));

            System.out.println("What would you like to do?");
            System.out.println("1: Play the movie");
            System.out.println("2: Save the movie to your list");
            System.out.println("Enter your choice: ");
            int option = scan.nextInt();
            movieOption(option);

        } catch (Exception e) {
            System.out.println("You cannot write letters, write only numbers. You can try again now.");
            scan.nextLine(); // Ryd input-bufferen
            chooseMovie(); // Kald metoden igen for at prøve igen
        }
    }
    public String choseResults ( int movieNumberToWatch){
        ArrayList<Media> wannaWatch = MovieAndSeriesLab.movies;
        return wannaWatch.get(movieNumberToWatch).getTitel();
    }

    public void youHaveChosenMovie() {
        System.out.print("Enter your choice: ");
        if (scan.hasNextInt()) { // Brug samme Scanner
            int choice = scan.nextInt();
            System.out.println("You selected: " + choice);
        } else {
            System.out.println("Invalid input. Please enter a valid number.");
            scan.nextLine(); // Ryd input-bufferen
            youHaveChosenMovie(); // Kald metoden igen
        }
    }

    public void movieOption(int input){
        Scanner scanner = new Scanner(System.in);
        if (input == 1) {
            System.out.println("The movie is now playing ");

        } else if (input == 2) {
            System.out.println("Enter the name of the movie you want to save: ");
            String movieName = scanner.nextLine();
            try {
                FileWriter csvWriter = new FileWriter("SP3/data/SavedMoviesList.csv", true);
                csvWriter.append(movieName);
                csvWriter.append("\n");
                csvWriter.flush();
                csvWriter.close();
                System.out.println("The movie has been added to your list.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Option does not exist, please choose the available options ");
        }
    }

    public void chooseSeries() {
        System.out.println("\n\n");
        System.out.println("Please press the number of the series you want to watch");

        try {
            int seriesNumberToWatch = scan.nextInt();
            System.out.println("You have chosen: " + choseResults(seriesNumberToWatch));

            // Tilføj mulighed for at vælge, hvad man vil gøre med serien
            System.out.println("What would you like to do?");
            System.out.println("1: Play the series");
            System.out.println("2: Save the series to your list");
            System.out.println("Enter your choice: ");

            int option = scan.nextInt();
            scan.nextLine();
            seriesOption(option); // Kalder metoden her

        } catch (Exception e) {
            System.out.println("You cannot write letters, write only numbers. You can try again now.");
            scan.nextLine(); // Ryd input-bufferen
            chooseSeries(); // Prøv igen
        }
    }

    public void seriesOption(int input) {
        Scanner scanner = new Scanner(System.in);
        if (input == 1) {
            System.out.println("The series is now playing.");
        } else if (input == 2) {
            System.out.println("Enter the name of the series you want to save: ");
            String seriesName = scanner.nextLine();
            try (FileWriter csvWriter = new FileWriter("SP3/data/SavedSeriesList.csv", true)) {
                csvWriter.append(seriesName).append("\n");
                System.out.println("The series has been added to your list.");
            } catch (IOException e) {
                System.out.println("Error saving series: " + e.getMessage());
            }
        }
    }

    public void youHaveChosenSeries() {
        System.out.print("Enter your choice: ");
        if (scan.hasNextInt()) { // Brug samme Scanner
            int choice = scan.nextInt();
            System.out.println("You selected: " + choice);
        } else {
            System.out.println("Invalid input. Please enter a valid number.");
            scan.nextLine(); // Ryd input-bufferen
            youHaveChosenMovie(); // Kald metoden igen
        }
    }
    public String choseSeriesResults (int seriesNumberToWatch){
        ArrayList<Media> wannaWatchSeries = MovieAndSeriesLab.series;
        return wannaWatchSeries.get(seriesNumberToWatch).getTitel();
    }
}