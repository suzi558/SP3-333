import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {

    ArrayList<Media> movies = new ArrayList<>(); //A list to store Media objects representing movies.

    ArrayList<Media> series = new ArrayList<>(); //A list to store Media objects representing TV series

    public static ArrayList<Media> readMovieData() {
        try {
            Scanner scan = new Scanner(new File("film.txt"));
            while (scan.hasNextLine()) {          //Reads each line in
                String line = scan.nextLine();  // the file until the end.
                String[] lineData = line.split(";"); //Splits each line into an array of strings based on the semicolon.

                String movieName = lineData[0].trim(); //Movie name: lineData[0] (e.g., "The Godfather").
                String movieYear = lineData[1].trim(); //Release year: lineData[1] (e.g., "1972").

                ArrayList<String> MovieCategory = new ArrayList<>();
                String[] categoryArray = lineData[2].split(","); //Splits lineData[2] into individual categories
                for (int i = 0; i < categoryArray.length; i++) {
                    MovieCategory.add(categoryArray[i]); // Adds each category (except the last) to MovieCategory
                }
                String number = lineData[3].trim();
                number = number.replace(',', '.'); //replaces , with ., and converts it to a double
                double movieRating = Double.parseDouble(number);

                Movies movie = new Movies(movieName, movieYear, movieRating); //Constructs a Movies object using the name, year, and rating.
                movies.add(movie); //Adds the Movies object to the movies list.
            }
        } catch(FileNotFoundException e){
            System.out.println(e + "Option do not exist.Try again");
        }
        return movies;
    }

    public static ArrayList<Media> readSeries() {
        Scanner scan = new Scanner(new File("serier.txt"));
        try {
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                String[] lineData = line.split(";");

                String seriesName = lineData[0].trim();
                String seriesYears = lineData[1].trim();

                ArrayList<String> seriesCategories = new ArrayList<>();
                String[] seriesCategory = lineData[2].split(",");
                for(String category: seriesCategory){
                    seriesCategories.add(category.trim());
                }
                double seriesRating = Double.parseDouble(lineData[3].trim().replace(',', '.'));

                ArrayList<String> episodesPerSeason = new ArrayList<>();
                String[] seasonsData = lineData[4].split(",");
                for (String season : seasonsData) {
                    episodesPerSeason.add(season.trim());
                }
                Series series = new Series(seriesName, seriesYears, seriesRating, episodesPerSeason);
                series.add(series); // Populate the existing list
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());

        } return series;
            }

        }