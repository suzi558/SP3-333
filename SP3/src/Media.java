import java.util.ArrayList;

public abstract class Media {
    String titel;
    double rating;
    int releaseDate;
    ArrayList<String> categories;

    Media(String titel, int releaseDate, ArrayList<String> categories, double rating) {
        this.titel = titel;
        this.releaseDate = releaseDate;
        this.categories = categories;
        this.rating = rating;

    }

    void play() {
        // ui.displayMsg(ui.getCurrentMovie+"is playing now...")
    }

    String getTitel(){
        return this.titel;
    }

    double getRating(){
        return this.rating;
    }

    int getReleaseDate(){
        return this.releaseDate;
    }

    ArrayList<String> getCategory(){
        return this.categories;
    }


}
