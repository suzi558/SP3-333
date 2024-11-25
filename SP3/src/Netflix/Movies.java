package Netflix;
import java.util.ArrayList;

public class Movies extends Media {

    Movies(String titel, int releaseDate, ArrayList<String> categories, double rating){
        super(titel, releaseDate, categories, rating);
    }
    public ArrayList<String> getCategories() {
        return categories;
    }

    @Override
    String getTitel(){
        return this.titel;
    }
    @Override
    int getReleaseDate(){
        return this.releaseDate;
    }
    @Override
    ArrayList<String> getCategory(){
        return this.categories;
    }

}