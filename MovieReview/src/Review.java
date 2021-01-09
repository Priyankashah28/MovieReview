import java.util.*;

public class Review {
    Movie movie;
    User user;

    private HashMap<String,HashMap<String,Integer>> reviewList = new HashMap<>();

    public HashMap<String, HashMap<String, Integer>> getReviewList() {
        return reviewList;
    }


    Review(Movie movie,User user){
        this.movie = movie;
        this.user=user;
    }

    public void addReview(String userName,String movieName,int reviewScore) throws Exception {

        if(reviewList.get(userName)!= null){
            this.validtate(userName,movieName,reviewScore);
        }else {
            HashMap<String,Integer> reviewScoreList = new HashMap();
            reviewScoreList.put(movieName,reviewScore);
            reviewList.put(userName, reviewScoreList );
        }
    }

    private void validtate(String userName, String movieName, int reviewScore) throws Exception {
        if(Integer.parseInt(this.movie.getMovieList().get(movieName)) > 2020){
            throw new Exception("movie yet to be released");
        }else {
            if (reviewList.get(userName).get(movieName) != null) {
                throw new Exception("Multiple Reviews not allowed");
            } else {
                reviewList.get(userName).put(movieName, reviewScore);
            }
        }

        if(reviewList.get(userName).size() > 3){
            this.user.changeUserTypeToCritic(userName);
        }
        if(this.user.getUserList().get(userName) == userType.CRITIC.name()){
            this.reviewList.get(userName).put(movieName,reviewScore*2);
        }
    }

    public void getMovieBaseOnYear(int n,String year,String userTyped){
        List<String> movieNameOnYearBasis  = this.movie.getMovieNameBaseOnYear(year);
        getMovieListBaseOnYearOrGenre(n,movieNameOnYearBasis,userTyped);
    }

    public void getMovieBaseOnGenre(int n,String genres,String userTyped){
        List<String> movieNameOnGenreBasis  = this.movie.getMovieNameBaseOnGenre(genres);
        getMovieListBaseOnYearOrGenre(n,movieNameOnGenreBasis,userTyped);
    }

    public void getMovieListBaseOnYearOrGenre(int n,List<String> movieList,String userTyped){
        HashMap<String,Integer> resultForMovieName = new HashMap<>();
        for(String movieNameOnYear : movieList) {
            int count=0;
            for (Map.Entry<String, HashMap<String, Integer>> movieNames : reviewList.entrySet()){
                if(userType.VIEWER.name().equals(this.user.getUserList().get(movieNames.getKey())) || !(userTyped.equals(userType.VIEWER.name()))&& userType.CRITIC.name().equals(this.user.getUserList().get(movieNames.getKey())) || !(userTyped.equals(userType.VIEWER.name()) && !(userTyped.equals(userType.CRITIC.name())))) {
                    for (Map.Entry<String, Integer> movieName : movieNames.getValue().entrySet()) {
                        if(movieNameOnYear.equals(movieName.getKey())){
                            count+=movieName.getValue();
                        }
                    }
                }
            }
            resultForMovieName.put(movieNameOnYear,count);

        }
        List<Map.Entry<String,Integer>> entryList = new ArrayList<>(resultForMovieName.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return -(o1.getValue().compareTo(o2.getValue()));
            }
        });

        for(int i=0;i<n;i++){
            System.out.println(entryList.get(i));
        }
    }

    public void getAverageReviewScoreBasisOnYear(String year){
        List<String> movieNameOnYearBasis  = this.movie.getMovieNameBaseOnYear(year);
        System.out.println("Average review Base on Year is "+ getAverageReviews(movieNameOnYearBasis));
    }

    public void getAverageReviewScoreBasisOnGenre(String genre){
        List<String> movieNameOnGenreBasis  = this.movie.getMovieNameBaseOnGenre(genre);
        System.out.println("Average review Base on  Genre is "+ getAverageReviews(movieNameOnGenreBasis));
    }

    public void getAverageReviewScoreBasisOnMovie(String movieName){
        System.out.println("Average review Base on Movie Name is "+ getAverageReviews(new ArrayList(Collections.singleton(movieName))));
    }

    public double getAverageReviews(List<String> movieList){
        double sum=0;
        int counter=0;
        for(String movieNameOnYear : movieList) {
            for (Map.Entry<String, HashMap<String, Integer>> movieNames : reviewList.entrySet()){
                for (Map.Entry<String, Integer> movieName : movieNames.getValue().entrySet()) {
                    if(movieNameOnYear.equals(movieName.getKey())){
                        sum+=movieName.getValue();
                        counter++;
                    }
                }
            }
        }
        return (sum/counter);
    }
}

