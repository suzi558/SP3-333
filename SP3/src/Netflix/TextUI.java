package Netflix;

import java.util.Scanner;

public class TextUI {
    private Scanner scan;   // Bruges til at læse brugerinput fra konsollen.
    private UserHandler userHandler; //Håndterer brugere (f.eks. login og oprettelse).
    private MovieHandler movieHandler; //Håndterer filmdata (f.eks. visning og valg af film).
    private SeriesHandler seriesHandler; // Håndterer seriedata (f.eks. visning og valg af serier).

    public TextUI(UserHandler userHandler, MovieHandler movieHandler, SeriesHandler seriesHandler) {
        //Initialiserer attributterne, så TextUI kan kommunikere med de andre klasser.
        this.userHandler = userHandler;
        this.movieHandler = movieHandler;
        this.seriesHandler = seriesHandler;
        this.scan = new Scanner(System.in); //Opretter en Scanner til at læse brugerinput.
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
        int passwordAttempts = 0; // Tæller for antal mislykkede loginforsøg

        while (passwordAttempts < 3) { // Brugeren har op til 3 forsøg
            System.out.println("Enter your username:");
            String username = scan.nextLine();

            System.out.println("Enter your password:");
            String password = scan.nextLine();

            if (userHandler.login(username, password)) { //Kalder userHandler.login(username, password) for at validere login.
                System.out.println("Welcome to Netflix Stream, " + username + "!");
                showMediaMenu(); // Gå til mediamenuen, hvis login er succesfuldt/
                return; // Afslut login-metoden og fortsæt til mediamenuen
            } else {
                passwordAttempts++; // Øg tælleren, hvis login mislykkes
                System.out.println("Invalid credentials. Please try again.");
            }
        }

        // Hvis brugeren når 3 mislykkede loginforsøg
        System.out.println("Maximum login attempts have been reached. Shutting down...");
      start();
    }

    //Håndterer oprettelse af nye brugere.
    private void createUser() {
        System.out.println("Enter your full name:");
        String fullname = scan.nextLine();

        System.out.println("Create a username:");
        String username = scan.nextLine();

        System.out.println("Create a password:");
        String password = scan.nextLine();

        if (userHandler.createUser(username, password, fullname)) { //Kalder userHandler.createUser for at oprette brugeren.
            userHandler.saveUsers(); //Kalder userHandler.saveUsers() for at gemme brugerdata.
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
        switch (choice) { //For film: Kalder movieHandler.showMovies() og movieHandler.chooseMovie().
            case "1" -> {
                movieHandler.showMovies();
                movieHandler.chooseMovie();  // Call chooseMovie() after showing movies
            }
            case "2" -> { //For serier: Kalder seriesHandler.showSeries() og seriesHandler.chooseSeries().
                seriesHandler.showSeries();      // Viser serierne
                seriesHandler.chooseSeries();    // Giver brugeren mulighed for at vælge og handle på en serie
            }
            case "3" -> movieHandler.showSavedMovies(); //For gemte medier: Viser enten gemte film eller serier.
            case "4" -> seriesHandler.showSavedSeries(); //For andet input: Informerer om ugyldigt valg.
            default -> System.out.println("Invalid input. Please try again.");
        }
    }
}
