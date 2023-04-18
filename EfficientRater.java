
/**
 * Write a description of EfficientRater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String,Rating> myRatings;
    
    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    
    public void addRating(String item, double rating) {
        Rating newRate = new Rating(item,rating);
        myRatings.put(newRate.getItem(),new Rating(item,rating));
    }
    
    public HashMap<String,Rating> getaRating(){
        return myRatings;
    }

    public boolean hasRating(String item) {
        if(myRatings.containsKey(item)){
            return true;
        }
        return false;
    }

    public String getID() {
        return myID;
    }

    
     public double getRating(String item) {
        return (myRatings.get(item) != null) ? myRatings.get(item).getValue() : -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    
    
    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>(myRatings.keySet());
        return list;
    }
    
    
    

}
