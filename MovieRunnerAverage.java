
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.lang.Object;

public class MovieRunnerAverage {
    
    public void printAverageRatings(){
        int minimalRaters = 12;
        SecondRatings obj = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        System.out.println("Number of Movies " + obj.getMovieSize());
        System.out.println("Number of Raters " + obj.getRaterSize());
        
        ArrayList<Rating> moviesList = obj.getAverageRatings(minimalRaters);
        for(Rating movie_Ratings : moviesList){
            String item = movie_Ratings.getItem();
            String title = obj.getTitle(item);
            System.out.println(movie_Ratings.getValue() + " " + title);
        }
    }
    
    public void getAverageRatingOneMovie(){
        SecondRatings obj = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        String movie_title = "Vacation";
        String movie_id = obj.getID(movie_title);
        if(movie_id.equals("No Movie with such title can be found.")){
            System.out.println(movie_id);
        }
        else{
             System.out.println(movie_id);
             double avgRating = obj.getAverageByID(movie_id,1);
             System.out.println("The average rating of " + movie_title + " is " + avgRating);
        }
       
    }
    
}
