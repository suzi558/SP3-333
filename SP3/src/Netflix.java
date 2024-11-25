import java.lang.reflect.Array;
import java.util.ArrayList;


public class Netflix {
    private FileIO io;
    private TextUI ui;


    private ArrayList<Media> allList;
    private ArrayList<Movies> moviesList ;
    private ArrayList<Series> seriesList;




    // Movies movie = new Movies(movieName, movieYear, movieRating); //Constructs a Movies object using the name, year, and rating.
    // movies.add(movie); //Adds the Movies object to the movies list.

    Netflix() {
        this.ui = new TextUI();
        this.io = new FileIO();

        this.moviesList = new ArrayList<>();
        this.seriesList = new ArrayList<>();
        this.allList = new ArrayList<>();
    }
    public static void main(String[] args){



    }

    void dataList(){

        ArrayList<Media> allList = io.readData();
       for (Media s : allList){
           System.out.println(s);
        }
    }



    public void moviesAddList() {
    }




}