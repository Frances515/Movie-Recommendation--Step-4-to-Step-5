
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    //private ArrayList<Movie> myMovies;
    private HashMap<String,HashMap<String,Rating>> myRater;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    
    public ThirdRatings(String ratingsfile){
       FirstRatings obj = new FirstRatings();
       myRater = obj.loadRaters(ratingsfile);
        
    }
    
    public int getRaterSize(){
        return myRater.size();
    }
    
    public double getAverageByID(String movie_id, int minimalRaters){
       double count = 0;
       double sumAverage = 0;
       double ratingAverage = 0;
        
        if(minimalRaters == 0){
           return 0.0;
       }
       for(HashMap<String,Rating> entry : myRater.values()){
           for(Rating ratings : entry.values()){
               if(ratings.getItem().equals(movie_id)){
                   double ratingValue = ratings.getValue(); 
                   count++;
                   sumAverage = sumAverage + ratingValue;
                }
               
            }
        }
       
        if(count < minimalRaters){
            return -1;
        }
       else {
           ratingAverage = sumAverage/count;
           return ratingAverage;
       }
              
    }
    
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList <Rating> avgRatingList = new ArrayList<Rating>();
        ArrayList <String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        for(String entry : movies){
            //double avg = getAverageByID(entry, minimalRaters);
            getAverageByID(entry, minimalRaters);
            Rating list = new Rating(entry,getAverageByID(entry, minimalRaters));
            
            if(list.getValue() > -1){
                avgRatingList.add(list);
                //Collections.sort(avgRatingList);
            }
            
        }
        return avgRatingList;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> averageFilter = new ArrayList<Rating>();
        ArrayList <String> moviesList = MovieDatabase.filterBy(filterCriteria);
        for(String entry: moviesList){
            //double avg = getAverageByID(entry, minimalRaters);
            getAverageByID(entry, minimalRaters);
            Rating list = new Rating(entry, getAverageByID(entry, minimalRaters));
            if(list.getValue() > -1){
                averageFilter.add(list);
            }
        }
        return averageFilter;
    }
    
}

