package Netflix;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Initialiser UserHandler
        UserHandler userHandler = new UserHandler("C:\\Users\\Daniel\\IdeaProjects\\SP3-333\\SP3\\data\\UserLogin.csv");
        userHandler.loadUsers();

        // Initialiser FileIO for at læse data
        FileIO fileIO = new FileIO();

        // Læs film- og seriedata
        ArrayList<Media> movies = fileIO.readMovieData();
        ArrayList<Media> series = fileIO.readSeries();

        // Initialiser MovieHandler og SeriesHandler med data
        MovieHandler movieHandler = new MovieHandler(movies);
        SeriesHandler seriesHandler = new SeriesHandler(series);

        // Initialiser TextUI med nødvendige handler-klasser
        TextUI textUI = new TextUI(userHandler, movieHandler, seriesHandler);

        // Start brugergrænsefladen
        textUI.start();
    }
}
