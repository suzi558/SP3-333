package NetflixStream;

import java.util.ArrayList;
import java.util.Scanner;

//Netflix er ansvarlig for at håndtere interaktionen mellem brugeren og systemet.
// Den fungerer som en brugergrænseflade, der:
//Giver adgang til login og brugeroprettelse.
//Forbinder brugerinput med de relevante handler-klasser: UserHandler, MovieHandler, og SeriesHandler.
//Viser menuer og muligheder, så brugeren kan vælge handlinger.


public class Netflix {
    private Scanner scan;   // Bruges til at læse brugerinput fra konsollen.
    private UserHandler userHandler; //Håndterer brugere (f.eks. login og oprettelse).
    private MovieHandler movieHandler; //Håndterer filmdata (f.eks. visning og valg af film).
    private SeriesHandler seriesHandler; // Håndterer seriedata (f.eks. visning og valg af serier).
    private ArrayList<Media> movies;
    private ArrayList<Media> series;

    public Netflix(UserHandler userHandler, ArrayList<Media> movies, ArrayList<Media>series) {
        //Initialiserer attributterne, så netflix kan kommunikere med de andre klasser.
        this.userHandler = userHandler;
        this.movies = movies;
        this.series = series;
        this.scan = new Scanner(System.in); //Opretter en Scanner til at læse brugerinput.
        this.movieHandler = new MovieHandler(movies,this);
        this.seriesHandler = new SeriesHandler(series,this);
    }

    //Formål: Starter programmet og giver brugeren mulighed for at logge ind eller oprette en ny bruger.
    public void start() { //Viser en velkomstbesked og valgmuligheder for login (1) eller oprettelse (2).
        System.out.println("Welcome to Netflix Stream!");
        System.out.println("1: Log in");
        System.out.println("2: Sign up");
        String choice = scan.nextLine();

        switch (choice) {
            case "1" -> userLogin(); //kalder metoden
            case "2" -> createUser(); //kalder metoden
            default -> System.out.println("Invalid input. Please try again.");
        }
    }

    //Formål: Giver brugeren mulighed for at logge ind.
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

    //Formål: Håndterer oprettelse af nye brugere.
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
            showMediaMenu(); //Viser en velkomstbesked og kalder showMediaMenu().
        } else {
            System.out.println("Failed to create user. Please try again.");
        }
    }

    //Formål: Viser hovedmenuen for medier og giver brugeren mulighed for at vælge mellem film,
    // serier eller gemte medier.
    public void showMediaMenu() {
        System.out.println("Choose an option:");
        System.out.println("1: Movies");
        System.out.println("2: Series");
        System.out.println("3: Saved Movies");
        System.out.println("4: Saved Series");

        String choice = scan.nextLine();
        //Læser brugerens valg og kalder de relevante metoder

        switch (choice) { //For film: Kalder movieHandler.showMovies() og movieHandler.chooseMovie().
            case "1" -> {
                movieHandler.showMovies(); //viser film
                movieHandler.chooseMovie();  // kalder  chooseMovie() efter vising af movies
            }
            case "2" -> { //For serier: Kalder seriesHandler.showSeries() og seriesHandler.chooseSeries().
                seriesHandler.showSeries();      // Viser serierne
                seriesHandler.chooseSeries();    // Giver brugeren mulighed for at vælge og handle på en serie
            }
            case "3" -> movieHandler.showSavedSeries(); //For gemte medier: Viser enten gemte film eller serier.
            case "4" -> seriesHandler.showSavedSeries(); //For andet input: Informerer om ugyldigt valg.
            default -> System.out.println("Invalid input. Please try again.");
        }
    }
}

//Kort opsummering
//Netflix fungerer som bindeleddet mellem brugerinput og systemets funktionalitet.
//Den bruger:
//UserHandler til at logge ind eller oprette brugere.
//MovieHandler og SeriesHandler til at vise og håndtere film og serier.
//Fejlhåndtering sikrer, at programmet ikke crasher ved ugyldigt input.
