package NetflixStream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//Denne klasse håndterer film i Netflix-projektet. Den administrerer filmlisten, viser tilgængelige film,
// giver brugeren mulighed for at vælge film, og gemmer valgte film.


public class MovieHandler {
    private ArrayList<Media> movies; //En liste over film. Hver film er repræsenteret som et Media-objekt.
    private Scanner scan; //Bruges til at læse input fra brugeren.
    private Netflix netflix;

    public MovieHandler(ArrayList<Media> movies, Netflix netflix) {
        this.movies = movies; //Initialiserer listen over film (movies), så klassen ved, hvilke film den skal arbejde med.
        this.scan = new Scanner(System.in); //Opretter en Scanner til at håndtere brugerinput.
        this.netflix = netflix;
    }

    //formål: Viser en liste over tilgængelige film.
    public void showMovies() {
        if (movies.isEmpty()) { //Hvis listen movies er tom, vises en besked: "No movies available."
            System.out.println("No movies available.");
        } else { //Ellers udskrives alle film med deres detaljer (bruges toString fra Media).
            System.out.println("Available Movies:");
            for (int i = 0; i < movies.size(); i++) {
                Media movie = movies.get(i);
                System.out.println((i + 1) + "-" + movie.toString());
            }
        }
    }

    //Formål: Viser en liste over gemte film fra en fil.
    public void showSavedMovies() {
        System.out.println("Saved movies:");
        //Læser filen SavedSeriesList.csv linje for linje og udskriver dens indhold.
        try (BufferedReader reader = new BufferedReader(new FileReader("SP3/data/SavedMoviesList.csv"))) { //
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Antager at hver linje er en gemt serie
            }
            showPlayOrBackMenu(); // Efter visning af gemte serier, vis muligheder (kalder på metoden)
        } catch (IOException e) { //Hvis der opstår en fejl ved læsning, vises en fejlbesked.
            System.out.println("Error loading saved Movies: " + e.getMessage());
        }
    }

    //Formål: Viser en menu, hvor brugeren kan vælge at spille en
    // serie eller gå tilbage til hovedmenuen.
    public void showPlayOrBackMenu() {
        System.out.println("Choose an option:");
        System.out.println("1: Play the series");
        System.out.println("2: Back to menu");
        int choice = scan.nextInt();
        scan.nextLine(); // Clear buffer

        switch (choice) {
            case 1:
                //Spørger om serienummeret og udskriver en besked om, at serien spilles.
                System.out.println("Enter the number of the movie you want to play:");
                int movieChoice = scan.nextInt();
                // Kald metoden til at spille serien
                System.out.println("Playing series: " + movieChoice);
                break;
            case 2:
                netflix.showMediaMenu(); // Gå tilbage til menuen
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                showPlayOrBackMenu(); // Vis menuen igen
        }
    }

    //Formål: Giver brugeren mulighed for at vælge en film og vælge en handling.
    public void chooseMovie() {
        //Spørger brugeren om filmens nummer.
        System.out.println("\nPlease press the number of the movie you want to watch:");
        try {
            int movieNumberToWatch = scan.nextInt();
            scan.nextLine(); // Clear buffer
            System.out.println("You have chosen: " + movies.get(movieNumberToWatch).getTitel());

            //Viser valgmuligheder for at afspille filmen eller gemme den.
            System.out.println("What would you like to do?");
            System.out.println("1: Play the movie");
            System.out.println("2: Save the movie to your list");
            System.out.println("Enter your choice:");
            int option = scan.nextInt();
            scan.nextLine();
            movieOption(option); //kalder på metoden som (spil eller gem film)

        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scan.nextLine(); // Clear buffer
            chooseMovie();
        }
    }

    //Formål: Behandler brugerens valg i chooseMovie().
    public void movieOption(int input) {
        if (input == 1) { //Udskriver en besked om, at filmen nu afspilles.
            System.out.println("The movie is now playing.");
        } else if (input == 2) {  //Beder brugeren om filmens navn og gemmer det i SavedMoviesList.csv.
            System.out.println("Enter the name of the movie you want to save:");
            String movieName = scan.nextLine();
            try (FileWriter csvWriter = new FileWriter("SP3/data/SavedMoviesList.csv", true)) {
                csvWriter.append(movieName).append("\n");
                System.out.println("The movie has been added to your list.");
            } catch (IOException e) {
                System.out.println("Error saving movie: " + e.getMessage());
            }
        } else {
            System.out.println("Option does not exist, please choose the available options.");
        }
    }
}

//Kort opsummering
//Klassen håndterer visning og valg af film samt gemning af valgte film i en fil.
//Den bruger TextUI-klassen til at vende tilbage til hovedmenuen og interagerer med brugeren via en Scanner.
//Fejl håndteres løbende, fx ved ugyldigt input eller problemer med filadgang.
