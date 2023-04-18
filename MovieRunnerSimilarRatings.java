
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*; 

public class MovieRunnerSimilarRatings {
    public void printAverageRatings(){
        int minimalRaters =35;
        
        FourthRatings obj = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //System.out.println("Number of Movies " + obj.getMovieSize());
        System.out.println("Total number of Raters " + obj.getRaterSize());
        System.out.println("Total number of Movies " + MovieDatabase.size());
        
        
        ArrayList<Rating> moviesList = obj.getAverageRatings(minimalRaters);
        Collections.sort(moviesList);
        
        for(Rating movie_Ratings : moviesList){
            String item = movie_Ratings.getItem();
            String title = MovieDatabase.getTitle(item);
            System.out.println(movie_Ratings.getValue() + " " + title);
        }
        System.out.println("Found " + moviesList.size() + " movies");
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        int minimalRaters = 8;
        int date = 1990;
        String genre = "Drama";
       
        
        YearAfterFilter yearObj = new YearAfterFilter(date);
        GenreFilter genreObj = new GenreFilter(genre);
        
        AllFilters filters = new AllFilters();
        filters.addFilter(yearObj);
        filters.addFilter(genreObj);
        
        FourthRatings obj = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Total number of Raters " + obj.getRaterSize());
        System.out.println("Total number of movies " + MovieDatabase.size());
        
        ArrayList<Rating> moviesList = obj.getAverageRatingsByFilter(minimalRaters, 
        filters);
        Collections.sort(moviesList);
        
         for(Rating movie_filter: moviesList){
            String item = movie_filter.getItem();
            String title = MovieDatabase.getTitle(item);
            System.out.println(movie_filter.getValue()+" " + title );
            System.out.println("Genres: " + MovieDatabase.getGenres(item));
          
        }
        System.out.println("Found " + moviesList.size() + " movies");
    }

    public void printSimilarRatings(){
        FourthRatings obj = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        String rater_id = "71";
        int numtoprater = 20;
        int minimalRaters = 5;
        
        ArrayList<Rating> similarities = obj.getSimilarRatings(rater_id, numtoprater,minimalRaters);
        for(Rating r: similarities){
             System.out.println(MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printRatingsByGenre(){
        FourthRatings obj = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        String rater_id = "964";
        int minimalRaters = 5;
        int num_top_rater= 20;
        String genre = "Mystery";
        GenreFilter genreObj = new GenreFilter(genre);
        
        
       
        ArrayList<Rating> directors = obj.getSimilarRatingsByFilter
        (rater_id, num_top_rater, minimalRaters,genreObj);
        for(Rating r : directors){
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " ratings: " + r.getValue());
            
        }
    }
    
    
    public void printRatingsByDirector(){
        FourthRatings obj = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        String rater_id = "120";
        int minimalRaters = 2;
        int num_top_rater= 10;
        DirectorsFilter directorName= new DirectorsFilter
        ("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        
       
        ArrayList<Rating> directors = obj.getSimilarRatingsByFilter
        (rater_id, num_top_rater, minimalRaters,directorName);
        for(Rating r : directors){
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " ratings: " + r.getValue());
            System.out.println("Directors: " + MovieDatabase.getDirector(r.getItem()));
        }
    }
    
    //review
    public void printSimilarRatingsByGenreAndMinutes(){
        FourthRatings obj = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        String genre = "Drama";
        int min = 80;
        int max = 160;
        String rater_id = "168";
        int minimalRaters = 3;
        int num_top_rater= 10;
        
        GenreFilter genreObj = new GenreFilter(genre);
        MinutesFilter minutesObj = new MinutesFilter(min,max);
        
        AllFilters filters = new AllFilters();
        filters.addFilter(genreObj);
        filters.addFilter(minutesObj);
        
        ArrayList<Rating> genreAndMinutes = obj.getSimilarRatingsByFilter
        (rater_id, num_top_rater, minimalRaters,filters);
        
        for(Rating r : genreAndMinutes){
            System.out.println(MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByYearAfterMinutes(){
        FourthRatings obj = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        String rater_id = "314";
        int minimalRaters = 5;
        int num_top_rater= 10;
        int year = 1975;
        int min = 70;
        int max = 200;
        
        YearAfterFilter yearObj = new YearAfterFilter(year);
        MinutesFilter minutesObj = new MinutesFilter(min, max);
        
        AllFilters filters = new AllFilters();
        filters.addFilter(yearObj);
        filters.addFilter(minutesObj);
        
        ArrayList<Rating> yearAndMinutes = obj.getSimilarRatingsByFilter
        (rater_id, num_top_rater, minimalRaters,filters);
        
        
        
        for(Rating r : yearAndMinutes){
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " ratings: " + r.getValue());
        }
        
    }
    
     
}


