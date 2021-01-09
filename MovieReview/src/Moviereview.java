import java.util.ArrayList;

public class Moviereview {
    public static void main(String argv[]) throws Exception {
        Movie movie = new Movie();
        movie.addMovie("Don","2006","Actions&Comedy");
        movie.addMovie("Tiger","2008","Drama");
        movie.addMovie("Lunchbox","2021","Drama");
        movie.addMovie("Guru","2006","Actions");
        movie.addMovie("Padmaavat","2006","Comedy");
        movie.addMovie("Metro","2006","Romance");
        System.out.println(movie.getMovieList());
        System.out.println(movie.getMovieListwithGenres());
        User user = new User();
        user.addUser("SRK");
        user.addUser("Salman");
        user.addUser("Deepika");
        System.out.println(user.userList);
        Review reviews = new Review(movie,user);
        reviews.addReview("SRK","Don",2);
        reviews.addReview("SRK","Padmaavat",8);
        reviews.addReview("Salman","Don",5);
        reviews.addReview("Deepika","Don",9);
        reviews.addReview("Deepika","Guru",6);
//        r1.addReview("SRK","Don",10);   --- it will throw an Exception because multiple reviews not aallowed
//        r1.addReview("Deepika","Lunchbox",5);  ----it will throw an Excetpion because Exception movie yet to be released
        reviews.addReview("SRK","Tiger",5);
        reviews.addReview("SRK","Metro",7);
        System.out.println(reviews.getReviewList());
        reviews.getMovieBaseOnYear(3,"2006",userType.VIEWER.name());
        reviews.getMovieBaseOnGenre(1,"Drama",userType.CRITIC.name());
        reviews.getAverageReviewScoreBasisOnYear("2006");
        reviews.getAverageReviewScoreBasisOnMovie("Don");
    }
}
