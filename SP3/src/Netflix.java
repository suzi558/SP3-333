import java.lang.reflect.Array;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Netflix {

    private ArrayList<Movies>  moviesList;
    private ArrayList<Series> seriesList;


    Netflix(){
        this.moviesList = new ArrayList<>();
        this.seriesList = new ArrayList<>();
    }



    

        //Test, 100 skal være længden af alle movies i tekstfilen
    void moviesAddList() {
        for (int i = 0; i < 100; i++) {
            moviesList.add(i, new Movies(io.readData));
        }
    }



}