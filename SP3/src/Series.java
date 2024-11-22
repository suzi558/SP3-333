import java.util.ArrayList;

public class Series extends Media{

    int season;
   // ArrayList<Integer> seasonList;

    int episode;
   // ArrayList<Integer> episodeList;


    Series(String titel, int releaseDate, ArrayList<Media> categories, double rating, int season, int episode){
        super(titel, releaseDate, categories, rating);
        this.season = season;
        this.episode = episode;

 //       seasonList.add(season);
 //       episodeList.add(episode);
    }






}
