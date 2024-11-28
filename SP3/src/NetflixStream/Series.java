package NetflixStream;

import java.util.ArrayList;

public class Series extends Media {
    private ArrayList<Integer> seasonsList;
    private ArrayList<Integer> episodeList;

    Series(String titel, int releaseDate, ArrayList<String> categories, double rating,  ArrayList<Integer> seasonsList, ArrayList<Integer> episodeList) {
        super(titel, releaseDate, categories, rating);
        this.seasonsList = seasonsList;
        this.episodeList = episodeList;
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


    public ArrayList<Integer> getSeasonsList() {
        return seasonsList;
    }

    public ArrayList<Integer> getEpisodeList() {
        return episodeList;
    }

    @Override
    public String toString() {

        String seaeps = "";
        for(int i = 0; i<seasonsList.size(); i++) {
            seaeps += "|-|"+" S"+ seasonsList.get(i)+": "+episodeList.get(i)+" episodes ";
        }
        return ". " + titel + " - " + releaseDate + " - " + categories + " - " + "IMDB Rating: " + rating + " - " + seaeps;
    }
}