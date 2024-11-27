package Netflix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SeriesHandler {
    private ArrayList<Media> series; //En liste over serier. Hver serie er repræsenteret som et Media-objekt.
    private Scanner scan; // Bruges til at læse input fra brugeren.
    private TextUI textUI;

    public SeriesHandler(ArrayList<Media> series) { //
        this.series = series; //Initialiserer listen over serier (series), så klassen ved, hvilke serier den skal arbejde med.
        this.scan = new Scanner(System.in); //Opretter en Scanner til at håndtere brugerinput
    }

    public void showSeries() {
        if (series.isEmpty()) { //Hvis listen over serier (series) er tom, udskrives beskeden: "No series available."
            System.out.println("No series available.");
        } else {
            System.out.println("Available Series:");
            for (int i = 0; i < series.size(); i++) { //Itererer gennem listen over serier.
                Media serie = series.get(i);
                // Nummeret i listen starter fra 1 (i + 1)
                System.out.println((i + 1) + serie.toString());
            }
        }
    }

    public void showSavedSeries() {
        System.out.println("Saved Series:");

        ArrayList<String> savedSeries = new ArrayList<>(); // Store the saved series in a list
        try (BufferedReader reader = new BufferedReader(new FileReader("SP3/data/SavedSeriesList.csv"))) {
            String line;
            int index = 1; // Add numbering to the saved series
            while ((line = reader.readLine()) != null) {
                savedSeries.add(line); // Add each series to the list
                System.out.println(index + ": " + line); // Display numbered list
                index++;
            }
            if (savedSeries.isEmpty()) {
                System.out.println("No saved series found.");
            } else {
                showPlayOrBackMenu(savedSeries); // Show options after displaying series
            }
        } catch (IOException e) {
            System.out.println("Error loading saved series: " + e.getMessage());
        }
    }

    private void showPlayOrBackMenu(ArrayList<String> savedSeries) {
        System.out.println("Choose an option:");
        System.out.println("1: Play a saved series");
        System.out.println("2: Back to menu");

        try {
            int choice = scan.nextInt();
            scan.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    System.out.println("Enter the number of the series you want to play:");
                    int seriesChoice = scan.nextInt();
                    scan.nextLine(); // Clear buffer

                    // Validate the user's choice
                    if (seriesChoice > 0 && seriesChoice <= savedSeries.size()) {
                        System.out.println("Playing series: " + savedSeries.get(seriesChoice - 1));
                    } else {
                        System.out.println("Invalid series number. Please try again.");
                        showPlayOrBackMenu(savedSeries); // Show menu again
                    }
                    break;
                case 2:
                    textUI.showMediaMenu(); // Go back to the main menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    showPlayOrBackMenu(savedSeries); // Show menu again
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            scan.nextLine(); // Clear buffer
            showPlayOrBackMenu(savedSeries); // Show menu again
        }
    }

    /*public void displaySeries() {
        System.out.println("You chose series, here's some options:");
        for (int i = 0; i < series.size(); i++) {
            String seriesDetails = i + " - " + series.get(i).getTitel() + " - "
                    + series.get(i).getReleaseDate() + " - "
                    + series.get(i).getCategory() + " - "
                    + series.get(i).getRating();
            System.out.println(seriesDetails);
        }
    }*/

    //Denne metode lader brugeren vælge en serie og beslutte, hvad der skal ske med den.
    public void chooseSeries() {
        //Udskriver en besked, der beder brugeren vælge en serie ved at indtaste nummeret.
        System.out.println("\nPlease press the number of the series you want to watch:");
        try {
            int seriesNumberToWatch = scan.nextInt(); //Finder serien baseret på brugerens valg.
            scan.nextLine(); // Clear buffer
            System.out.println("You have chosen: " + series.get(seriesNumberToWatch).getTitel()); //Udskriver serien, som brugeren har valgt.

            //Præsenterer to muligheder
            System.out.println("What would you like to do?");
            System.out.println("1: Play the series");
            System.out.println("2: Save the series to your list");
            System.out.println("Enter your choice:");
            int option = scan.nextInt();
            scan.nextLine();
            seriesOption(option); //Læser brugerens valg og kalder seriesOption() med valget.

        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scan.nextLine(); // Clear buffer
            chooseSeries();
        }
    }

    //Denne metode håndterer brugerens valg fra chooseSeries().
    public void seriesOption(int input) {
        if (input == 1) { //Hvis brugeren vælger 1 (afspil serien)
            System.out.println("The series is now playing.");
        } else if (input == 2) { //Hvis brugeren vælger 2 (gem serien)
            System.out.println("Enter the name of the series you want to save:"); //Beder brugeren om at indtaste seriens navn
            String seriesName = scan.nextLine();
            //Tilføjer seriens navn til filen SP3/data/SavedSeriesList.csv ved hjælp af FileWriter
            try (FileWriter csvWriter = new FileWriter("SP3/data/SavedSeriesList.csv", true)) {
                csvWriter.append(seriesName).append("\n"); //Bekræfter, at serien er blevet gemt.
                System.out.println("The series has been added to your list.");
            } catch (IOException e) {
                System.out.println("Error saving series: " + e.getMessage());
            }
        } else { //Udskriver en fejlmeddelelse: "Option does not exist."
            System.out.println("Option does not exist, please choose the available options.");
        }
    }
}
