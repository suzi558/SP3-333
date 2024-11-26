package Netflix;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieHandler {
    private ArrayList<Media> movies;
    private Scanner scan;

    public MovieHandler(ArrayList<Media> movies) {
        this.movies = movies;
        this.scan = new Scanner(System.in);
    }

    public void showMovies() {
        if (movies.isEmpty()) {
            System.out.println("No movies available.");
        } else {
            System.out.println("Available Movies:");
            for (Media movie : movies) {
                System.out.println(movie);
            }
        }
    }

    public void showSavedMovies() {
        System.out.println("Saved Movies:");
        // Tilføj logik til at vise gemte film, hvis nødvendigt
    }

    public void displayMovies() {
        System.out.println("You chose movies, here's some options:");
        for (int i = 0; i < movies.size(); i++) {
            String movieDetails = i + " - " + movies.get(i).getTitel() + " - "
                    + movies.get(i).getReleaseDate() + " - "
                    + movies.get(i).getCategory() + " - "
                    + movies.get(i).getRating();
            System.out.println(movieDetails);
        }
    }

    public void chooseMovie() {
        System.out.println("\nPlease press the number of the movie you want to watch:");
        try {
            int movieNumberToWatch = scan.nextInt();
            scan.nextLine(); // Clear buffer
            System.out.println("You have chosen: " + movies.get(movieNumberToWatch).getTitel());

            System.out.println("What would you like to do?");
            System.out.println("1: Play the movie");
            System.out.println("2: Save the movie to your list");
            System.out.println("Enter your choice:");
            int option = scan.nextInt();
            scan.nextLine();
            movieOption(option);

        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scan.nextLine(); // Clear buffer
            chooseMovie();
        }
    }

    public void movieOption(int input) {
        if (input == 1) {
            System.out.println("The movie is now playing.");
        } else if (input == 2) {
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
