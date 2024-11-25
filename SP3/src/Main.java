public class Main {
    public static void main(String[] args) {
        UserHandler userHandler = new UserHandler("SP3/data/UserLogin.csv");
        TextUI textUI = new TextUI(userHandler);
        userHandler.loadUsers();
        String userinput = textUI.getUserInput();

        switch(userinput){
            case "1":
                textUI.UserLogin();
                break;
            case "2":
                textUI.CreatAUser();
                userHandler.saveUsers();
                break;
            default:
                System.out.println("Goodbye");
        }

        //Here we call the method to read the movie data so the user can pick the specific media they want.
        FileIO fileIO = new FileIO();

        MovieAndSeriesLab.movies = fileIO.readMovieData();
        TextUI text = new TextUI(userHandler);
        text.pickMedia(MovieAndSeriesLab.movies);

        MovieAndSeriesLab.series = fileIO.readSeries();
        TextUI text2 = new TextUI(userHandler);
        text.pickMedia(MovieAndSeriesLab.series);
    }
}