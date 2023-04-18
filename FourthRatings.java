
/**
 * Write a description of FourthRatings here.
 * 
 * @author Frances E.A.Antwi-Donkor 
 * @version (a version number or a date)
 */

import java.util.*;

public class FourthRatings {
    
    public FourthRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public FourthRatings(String ratingsfile){
        RaterDatabase.addRatings("data/" + ratingsfile);
    }
    
    public Rater getRater(String rater_id){
        return RaterDatabase.getRater(rater_id);
    }
    
    public int getRaterSize(){
        return RaterDatabase.size();
    }
    
    public double getAverageByID(String movie_id, int minimalRaters){
       ArrayList<Rater> Raters = RaterDatabase.getRaters();
       double count = 0;
       double sumAverage = 0;
       double ratingAverage = 0;
        
        if(minimalRaters == 0){
           return 0.0;
       }
       for(Rater entry : Raters){
           HashMap<String,Rating> Ratings = entry.getaRating();
           for(Rating ratings : Ratings.values()){
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
            double avg = getAverageByID(entry, minimalRaters);
            //getAverageByID(entry, minimalRaters);
            Rating list = new Rating(entry,avg);
            
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
            double avg = getAverageByID(entry, minimalRaters);
            //getAverageByID(entry, minimalRaters);
            Rating list = new Rating(entry, avg);
            if(list.getValue() > -1){
                averageFilter.add(list);
            }
        }
        return averageFilter;
    }
    
    /*private double dotProduct(Rater me, Rater r){
        HashMap<String,Rating> myRatings = me.getaRating();
        HashMap<String,Rating> rRatings = r.getaRating();
        
        double dotProduct = 0;
        
        for(Rating entry: myRatings.values()){
            for(Rating rEntry : rRatings.values()){
                if(entry.getItem().equals(rEntry.getItem())){
                    double meRating = entry.getValue()-5;
                    double rRating = rEntry.getValue()-5;
                    dotProduct = dotProduct +  (meRating * rRating);
                }
            }
        }
        return dotProduct;
    }*/
    
    
    private double dotProduct(Rater me, Rater r){
        double dotProduct = 0;
        
        for(String entry: r.getItemsRated()){
            
                if(me.hasRating(entry)){
                    double meRating = me.getRating(entry)-5;
                    double rRating = r.getRating(entry)-5;
                    dotProduct = dotProduct +  (meRating * rRating);
                }
            
        }
        return dotProduct;
    }
    
   
    
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> similar = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for(Rater entry : RaterDatabase.getRaters()){
            if(!entry.getID().equals(me)){
                if(dotProduct(me,entry) > 0){
                    similar.add(new Rating(entry.getID(),dotProduct(me, entry)));
                }
            }
        }
        Collections.sort(similar, Collections.reverseOrder());
        return similar;
    }
    
    
    
    public ArrayList<Rating> getSimilarRatings(String rater_id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> weightedAvg = new ArrayList<Rating>();
        ArrayList<Rating> similarRater = getSimilarities(rater_id);
        
        for(String movie : MovieDatabase.filterBy(new TrueFilter())){
            double totalWeightedRating = 0.0;
            int topraters = 0;
            for(int i=0; i< numSimilarRaters ; i++){
                Rating similar = similarRater.get(i);
                Rater rater = RaterDatabase.getRater(similar.getItem());
                if(RaterDatabase.getRater(rater_id).hasRating(movie)) continue;
                if(rater.hasRating(movie)){
                    totalWeightedRating += rater.getRating(movie)* similar.getValue();
                    topraters++;
                }
            }
            
            if(topraters >= minimalRaters){
                weightedAvg.add(new Rating(movie, totalWeightedRating/topraters));
            }
        }
        Collections.sort(weightedAvg, Collections.reverseOrder());
        return weightedAvg;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String rater_id, int numSimilarRaters, 
    int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> weightedAvg = new ArrayList<Rating>();
        ArrayList<Rating> similarRater = getSimilarities(rater_id);
        
        for(String movie : MovieDatabase.filterBy(filterCriteria)){
            double totalWeightedRating = 0.0;
            int topraters = 0;
            for(int i=0; i< numSimilarRaters ; i++){
                Rating similar = similarRater.get(i);
                Rater rater = RaterDatabase.getRater(similar.getItem());
                if(RaterDatabase.getRater(rater_id).hasRating(movie)) continue;
                if(rater.hasRating(movie)){
                    totalWeightedRating += rater.getRating(movie)* similar.getValue();
                    topraters++;
                }
            }
            
            if(topraters >= minimalRaters){
                weightedAvg.add(new Rating(movie, totalWeightedRating/topraters));
            }
        }
        Collections.sort(weightedAvg, Collections.reverseOrder());
        return weightedAvg;
    }

}
