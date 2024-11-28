package NetflixStream;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Initialiser UserHandler/ håndterer brugerdata, f.eks. login og brugeroprettelse.
        UserHandler userHandler = new UserHandler("C:\\Users\\elmir\\OneDrive\\Dokumenter\\SP3-333\\SP3\\data\\UserLogin.csv");
        userHandler.loadUsers(); // kaldes for at indlæse brugerne fra filen, så systemet ved, hvilke brugere der findes.

        // FileIO bruges til at håndtere filindlæsning og skriveoperationer for film og serier.
        FileIO fileIO = new FileIO();

        //Indlæse data om film og serier fra filer.
        ArrayList<Media> movies = fileIO.readMovieData(); // kaldes for at indlæse en liste af film (af typen Media).
        ArrayList<Media> series = fileIO.readSeries();    // kaldes for at indlæse en liste af serier (af typen Media).

        // Initialiser MovieHandler og SeriesHandler med data
        MovieHandler movieHandler = new MovieHandler(movies);
        SeriesHandler seriesHandler = new SeriesHandler(series);

        // Initialiser TextUI med nødvendige handler-klasser
        Netflix netflix = new Netflix(userHandler, movieHandler, seriesHandler);

        // Start brugergrænsefladen
        netflix.start();
    }
}
