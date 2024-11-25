import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {



    ArrayList<Media> series = new ArrayList<>(); //A list to store Media objects representing TV series

    public static ArrayList<Media> readData() {
        ArrayList<Media> movies = new ArrayList<>(); //A list to store Media objects representing movies.

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

}
