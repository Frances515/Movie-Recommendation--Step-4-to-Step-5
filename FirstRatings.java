
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
public class FirstRatings {
    public ArrayList loadMovies(String filename){
        ArrayList<Movie> movieRecord = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record : parser){
            Movie movieData = new Movie(record.get("id"),record.get("title"),record.get("year"), 
            record.get("genre"),record.get("director"),record.get("country"),record.get("poster"),
            Integer.parseInt(record.get("minutes")));
            movieRecord.add(movieData);
   
        }
        return movieRecord;
    }
    
    public void testLoadMovies(){
        ArrayList<Movie> data = new ArrayList<Movie>();
        data = loadMovies("ratedmoviesfull.csv");
        int numMovies = 0;
        int numComedy = 0;
        int minutes = 0;
        int max =0;
        for(Movie entry : data){
            numMovies += 1;
            System.out.println(entry);
            
            if(entry.getGenres().contains("Comedy")){
                numComedy += 1;
            }
            
            if(entry.getMinutes() > 150){
                minutes += 1;
            }
        }
        System.out.println("Number of Movies:" + " " + numMovies); 
        System.out.println("Number of Comedy Movies:" + " " + numComedy); 
        System.out.println("Number of Movies longer than 150 mins:" + " " + minutes); 
        
        
        HashMap<String, Integer> directorList = new HashMap<String, Integer>();
        ArrayList <String> temp = new ArrayList<String>();
      
        
        for(Movie entry: data){
            if(entry.getDirector().contains(",")){
                String[] newEntry = entry.getDirector().split(",");
                for(int i = 0; i< newEntry.length; i++){
                    temp.add(newEntry[i]);
                }
            }
            else{
                temp.add(entry.getDirector());
            }
        }
        
        System.out.println(temp);
        
        for(String i : temp){
            Integer value = directorList.get(i);
            // if value is null value should equal 1 otherwise increment value by 1
            directorList.put(i, (value == null) ? 1 : value + 1);   
         
        }
        System.out.println(directorList);
        System.out.println("Total Directors: " + directorList.size());   
        
        for(int entry : directorList.values()){
            if(max < entry){
                max = entry;
            }
        }
        System.out.println("Maximum number of movies by any director is " + max);
        
        for(String entry : directorList.keySet()){
            if(directorList.get(entry) == max){
                System.out.println("Directors with " + max + "directed movies are: " + entry);
            }
        }
    }
    
    
    public HashMap<String, HashMap<String, Rating>> loadRaters(String filename){
        ArrayList<Rater> raterData = new ArrayList<Rater>();
        HashMap<String,HashMap<String,Rating>> raterRecord = new HashMap<String,HashMap<String, Rating>>();
        FileResource fr = new FileResource("data/" + filename);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record : parser){
            Rater newRater = new EfficientRater(record.get("rater_id"));
            //raterRecord.put(record.get("rater_id"), newRater.addRating(record.get("movie_id"), 
            //Double.parseDouble(record.get("rating"))));
            raterData.add(newRater);
            newRater.addRating(record.get("movie_id"),Double.parseDouble(record.get("rating")));
            
            for(String entry : newRater.getaRating().keySet()){
                if(!raterRecord.containsKey(newRater.getID())){
                    raterRecord.put(newRater.getID(),newRater.getaRating());
                }
                else{
                    raterRecord.get(newRater.getID()).put(entry, newRater.getaRating().get(entry));
                }
            }
            
        }
        
        return raterRecord;
    }
    
    
    
    public void testLoadRaters(){
       HashMap<String, HashMap<String,Rating>> data = new HashMap<String, HashMap<String,Rating>>();
       data = loadRaters("ratings_short.csv");
       String rater_id = "193";
       int max = 0;
       int count = 0;
       String movieID= "1798709";
       int numRatings = 0;
       ArrayList<String> movieList = new ArrayList<String>();
       System.out.println("Total numbers of raters: "+ data.size());
       
       for(String entry : data.keySet()){
           if(entry.equals(rater_id)){
               System.out.println("The rater with id " + rater_id + " has " + data.get
               (entry).size() + " ratings");
            }
        }
        
       for(String entry: data.keySet()){
            if(max < data.get(entry).size()){
                max = data.get(entry).size();
            }    
        }
       System.out.println("The maximum number of ratings are " + max);
        
       for(String entry : data.keySet()){
           if(data.get(entry).size() == max){
               count+=1;
           System.out.println("The rater with max movies rated are : " + entry);
           }
        }
       System.out.println(count + " number of raters have " + max + " ratings");
       
       for(HashMap<String,Rating> entry: data.values()){
           for(Rating movie : entry.values()){
               if(movie.getItem().equals(movieID)){
                   numRatings+=1;
            }
            for(Rating movies : entry.values()){
                if(!movieList.contains(movies.getItem())){
                    movieList.add(movies.getItem());
                }
            }
            }   
        }
        
        
        System.out.println("The movie with ID " + movieID + " has " + numRatings); 
        System.out.println(movieList.size() + " total movies were rated.");
        
        
        
        
    }
    
    
    
    
    
    
    
}



