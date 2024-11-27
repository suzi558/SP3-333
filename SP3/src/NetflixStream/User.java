package NetflixStream;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String fullName;
    public ArrayList<Media>watchedMovies;
    public ArrayList<Media>savedMovies;

    public User(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.watchedMovies = new ArrayList<>();
        this.savedMovies = new ArrayList<>();
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getFullName() {
        return fullName;
    }
    public void watchedMovies (Media movie) {
        watchedMovies.add(movie);
    }
    public void savedMovies (Media movie) {
        savedMovies.add(movie);
    }
}
