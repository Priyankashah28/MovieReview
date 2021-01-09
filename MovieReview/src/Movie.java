import java.util.*;

public class Movie {
    private String movieName;
    private String Year;
    private String movieGenres;

    private HashMap<String,String> movieList = new HashMap<>();
    private HashMap<String, List<String>> movieListwithGenres = new HashMap<>();

    public HashMap<String, String> getMovieList() {
        return movieList;
    }

    public void setMovieList(HashMap<String, String> movieList) {
        this.movieList = movieList;
    }

    public HashMap<String, List<String>> getMovieListwithGenres() {
        return movieListwithGenres;
    }

    public void setMovieListwithGenres(HashMap<String, List<String>> movieListwithGenres) {
        this.movieListwithGenres = movieListwithGenres;
    }



    public Movie(){ }
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getMovieGenres() {
        return movieGenres;
    }

    public void setMovieGenres(String movieGenres) {
        this.movieGenres = movieGenres;
    }

    public void addMovie(String movieName,String Year,String movieGenres) throws Exception {
        String[] movieArray;
        if(movieGenres.indexOf("&") != -1) {
            movieArray = movieGenres.split("&");

        }else {
            movieArray = new String[1];
            movieArray[0]= movieGenres;
        }
        Arrays.asList(movieArray);
        if(movieList.get(movieName)!=null){
            throw new Exception("Movie Name"+ movieName + "is already exists in the system");
        }
        else{
            movieList.put(movieName,Year);
        }
      for(String movieGenre: movieArray){
          if(movieListwithGenres.get(movieGenre) != null){
              movieListwithGenres.get(movieGenre).add(movieName);
          }else{
              ArrayList<String> movieList = new ArrayList<>();
              movieList.add(movieName);
              movieListwithGenres.put(movieGenre,movieList);
          }
      }
    }

    public List<String> getMovieNameBaseOnYear(String year){
        ArrayList movieListBaseOnYear = new ArrayList();
        for (Map.Entry<String,String> movieName : movieList.entrySet()){
            if(movieName.getValue().equals(year)){
                movieListBaseOnYear.add(movieName.getKey());
            }
        }
        return movieListBaseOnYear;
    }


    public List<String> getMovieNameBaseOnGenre(String genres) {
        return movieListwithGenres.get(genres);
    }
}
