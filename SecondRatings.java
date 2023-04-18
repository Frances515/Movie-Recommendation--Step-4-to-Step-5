
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    HashMap<String, HashMap<String,Rating>> myRater;
    
    public SecondRatings() {
        // default constructor
        this("ratedmovies_short.csv", "ratings_short.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile){
        FirstRatings obj = new FirstRatings();
        myMovies = obj.loadMovies(moviefile);
        myRater = obj.loadRaters(ratingsfile);
        
    }
    
    public int getMovieSize(){
        return myMovies.size();
    }
    
    public int getRaterSize(){
        return myRater.size();
    }
    
    public void ratings(){
        System.out.println("ratings " + myRater);
    }
    
    public double getAverageByID(String movie_id, int minimalRaters){
        double count = 0.0;
        double sumAverage = 0.0;
        double ratingValue = 0.0;
        double ratingAverage = 0.0;
       for(HashMap<String,Rating> entry : myRater.values()){
           for(Rating ratings : entry.values()){
               if(ratings.getItem().equals(movie_id)){
                   count += 1;
                   ratingValue = ratings.getValue(); 
                   sumAverage += ratingValue;
                }
            } 
        }
        if(minimalRaters == 0){
           ratingAverage = 0.0;
        }
       else {
           ratingAverage = sumAverage/count;
       }
        return ratingAverage;
    }
    
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList <Rating> avgRatingList = new ArrayList<Rating>();
        
        for(Movie entry : myMovies){
            //System.out.println(entry);
            double avg = getAverageByID(entry.getID(), minimalRaters);
            Rating list = new Rating(entry.getID(),avg);
            
            if(avg > 0.0){
            avgRatingList.add(list);
            Collections.sort(avgRatingList);
           }
        }
        
        
        return avgRatingList;
    }
    
    public String getTitle(String id){
        String idResult = "Sorry, this movie id : " + id + " was not found.";
        for(Movie entry: myMovies){
            if(id.equals(entry.getID())){
                idResult = entry.getTitle();
            }
        }
        return idResult;
    }
    
    public String getID(String title){
        String msg = "No Movie with such title can be found.";
        for(Movie entry : myMovies){
            if(title.equals(entry.getTitle())){
                msg = entry.getID();
            }
        }
        return msg;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*private double getAverageID(String id, int minimalRaters){
       double movieAverage = 0.0;
       double count = 0.0;
       //double numRatings = 0;
       double sumRating = 0.0;
       
       for(ArrayList<Rating> entry : myRater.values()){
           for(Rating rating : entry){
               if(rating.getItem().equals(id)){
                   double ratingValue = rating.getValue();
                   count +=1;
                   sumRating += ratingValue;
                   //count = count + value;
                   //System.out.println(rating.getItem());
               }
            }
        }
       
       if(count == minimalRaters){
           movieAverage = 0.0;
        }
        else{
            //sumRating += rating.getValue();
            movieAverage = sumRating / count;
            //movieAverage = count / sumRating;
        }
       return movieAverage;
       
    }
    
    public void printAll(){
        for(ArrayList<Rating> entry : myRater.values()){
            
            System.out.println("Here " + entry);
        
    }
    }
    
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        ArrayList<String> ids = new ArrayList<String>();
        for(Movie entry : myMovies){
            String id = entry.getID();
            ids.add(id);
            
            //double u = getAverageID(id, minimalRaters);
                
                //System.out.println("id " + id + " ratings " + u);
            
            //String movieID = entry.getID();
            //System.out.println("avg " + getAverageID(movieID, minimalRaters));
            //Rating obj = new Rating(movieID, getAverageID(movieID, minimalRaters));
            
            //if(obj.getValue() > -1){
                //averageRatings.add(obj);   
                //System.out.println("id " + movieID);
            //}
            
        }
        for(int i=0; i<ids.size(); i++){
            for(String entry : ids){
               
            }
                //System.out.println(ids.get(i));
                //double u = getAverageID(ids.get(i), minimalRaters);
                //System.out.println(u);
            }
        return averageRatings;
    }
    
    public String getTitle(String id){
        String idResult = "Sorry, this movie id : " + id + " was not found.";
        for(Movie entry: myMovies){
            if(id.equals(entry.getID())){
                idResult = entry.getTitle();
            }
        }
        return idResult;
    }*/
    
  
    
}
