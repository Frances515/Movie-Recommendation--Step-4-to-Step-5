
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class RecommendationRunner implements Recommender{
    public ArrayList<String> getItemsToRate(){
        ArrayList<String> moviesList = new ArrayList<String>();
        
        GenreFilter genre = new GenreFilter("Adventure");
        YearAfterFilter year = new YearAfterFilter(2000);
        
        Random generator = new Random();
        
        AllFilters filters = new AllFilters();
        filters.addFilter(genre);
        filters.addFilter(year);
        
        ArrayList<String> allMovies = MovieDatabase.filterBy(filters);
        
        for(int i=0; i<15;i++){
            int movie = generator.nextInt(allMovies.size());
            moviesList.add(allMovies.get(movie));
        }
        return moviesList;
    }
    
    public void printRecommendationsFor(String webRaterID){
        FourthRatings obj = new FourthRatings();
        ArrayList<Rating> recommendations = obj.getSimilarRatings(webRaterID,5,2);
        if(recommendations.size() == 0){
            System.out.println("<p> Sorry, there are no recommendations.<p>");
        }
        System.out.println("<p> Your recommended movies <p>");
        System.out.println("<style>th{color:purple; border:1px solid #000; padding: 8px; background-color:#ffe} td{border:1px solid #000; padding: 8px} tr:nth-child(even){background-color:#f2f2f2}</style>");
        System.out.println("<table>");
        System.out.println("<tr><th>Rank</th><th>Movie title</th><th>Genre</th></tr>");
        for(int i=0; i <10; i++){
            System.out.println("<tr><td>" + (i+1) + "</td><td>" + MovieDatabase.getTitle(recommendations.get(i)
            .getItem()) + "</td><td>" + MovieDatabase.getGenres(recommendations.get(i)
            .getItem())+ "</td></tr>");
                
        }
        System.out.println("</table>");
       
    }
    
}
