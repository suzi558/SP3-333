package Netflix;

import java.util.ArrayList;

public class Series extends Media {
    private ArrayList<String> episodesPerSeason;

    Series(String titel, int releaseDate, ArrayList<String> categories, double rating, ArrayList<String> episodesPerSeason) {
        super(titel, releaseDate, categories, rating);
        this.episodesPerSeason = episodesPerSeason;
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

    @Override
    double getRating(){
        return this.rating;
    }

    public ArrayList<String> getEpisodesPerSeason() {
        return episodesPerSeason;
    }

    @Override
    public String toString() {
        return "Netflix.Series{" +
                "episodesPerSeason=" + episodesPerSeason +
                ", titel='" + titel + '\'' +
                ", rating=" + rating +
                ", releaseDate=" + releaseDate +
                ", categories=" + categories +
                '}';
    }
}