package Netflix;

import java.util.Scanner;

public class TextUI {
    private Scanner scan;
    private UserHandler userHandler;
    private MovieHandler movieHandler;
    private SeriesHandler seriesHandler;

    public TextUI(UserHandler userHandler, MovieHandler movieHandler, SeriesHandler seriesHandler) {
        this.userHandler = userHandler;
        this.movieHandler = movieHandler;
        this.seriesHandler = seriesHandler;
        this.scan = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to Netflix Stream!");
        System.out.println("1: Log in");
        System.out.println("2: Sign up");
        String choice = scan.nextLine();

        switch (choice) {
            case "1" -> userLogin();
            case "2" -> createUser();
            default -> System.out.println("Invalid input. Please try again.");
        }
    }

    private void userLogin() {
        System.out.println("Enter your username:");
        String username = scan.nextLine();

        System.out.println("Enter your password:");
        String password = scan.nextLine();

        if (userHandler.login(username, password)) {
            System.out.println("Welcome to Netflix Stream, " + username + "!");
            showMediaMenu();
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    private void createUser() {
        System.out.println("Enter your full name:");
        String fullname = scan.nextLine();

        System.out.println("Create a username:");
        String username = scan.nextLine();

        System.out.println("Create a password:");
        String password = scan.nextLine();

        if (userHandler.createUser(username, password, fullname)) {
            userHandler.saveUsers();
            System.out.println("User created successfully. Welcome, " + username + "!");
            showMediaMenu();
        } else {
            System.out.println("Failed to create user. Please try again.");
        }
    }

    private void showMediaMenu() {
        System.out.println("Choose an option:");
        System.out.println("1: Movies");
        System.out.println("2: Series");
        System.out.println("3: Saved Movies");
        System.out.println("4: Saved Series");

        String choice = scan.nextLine();
        switch (choice) {
            case "1" -> movieHandler.showMovies();
            case "2" -> {
                seriesHandler.showSeries();      // Viser serierne
                seriesHandler.chooseSeries();    // Giver brugeren mulighed for at vælge og handle på en serie
            }
            case "3" -> movieHandler.showSavedMovies();
            case "4" -> seriesHandler.showSavedSeries();
            default -> System.out.println("Invalid input. Please try again.");
        }
    }
}
