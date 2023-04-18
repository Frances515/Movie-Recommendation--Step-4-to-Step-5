
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        int minimalRaters =35;

        ThirdRatings obj = new ThirdRatings("ratings.csv");
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

    public void printAverageRatingsByYear(){
        int minimalRaters = 20;
        int date = 2000;

        YearAfterFilter yearObj = new YearAfterFilter(date);
        ThirdRatings thirdRObj = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Total number of Raters " + thirdRObj.getRaterSize());
        System.out.println("Total number of movies " + MovieDatabase.size());

        ArrayList<Rating> moviesList = thirdRObj.getAverageRatingsByFilter(minimalRaters, 
                yearObj);
        Collections.sort(moviesList);

        for(Rating movie_filter: moviesList){

            String item = movie_filter.getItem();
            String title = MovieDatabase.getTitle(item);
            System.out.println(movie_filter.getValue() + " " + MovieDatabase.getYear(item) 
                +" " + title);
        }
        System.out.println("Found " + moviesList.size() + " movies");
    }

    public void printAverageRatingsByGenre(){
        int minimalRaters = 20;
        String genre = "Comedy";
        GenreFilter genreObj = new GenreFilter(genre);
        ThirdRatings thirdRObj = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Total number of Raters " + thirdRObj.getRaterSize());
        System.out.println("Total number of movies " + MovieDatabase.size());

        ArrayList<Rating> moviesList = thirdRObj.getAverageRatingsByFilter(minimalRaters, 
                genreObj);
        Collections.sort(moviesList);
        System.out.println("Found " + moviesList.size() + " movies");
        for(Rating movie_filter: moviesList){
            String item = movie_filter.getItem();
            String title = MovieDatabase.getTitle(item);
            System.out.println(movie_filter.getValue()+" " + title);
            System.out.println("Genres: " + MovieDatabase.getGenres(item));
        }
    }

    public void printAverageRatingsByMinutes(){
        int minimalRaters = 5;
        int min = 105;
        int max = 135;

        MinutesFilter minutesObj = new MinutesFilter(min, max);
        ThirdRatings thirdRObj = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Total number of Raters " + thirdRObj.getRaterSize());
        System.out.println("Total number of movies " + MovieDatabase.size());

        ArrayList<Rating> moviesList = thirdRObj.getAverageRatingsByFilter(minimalRaters, 
                minutesObj);
        Collections.sort(moviesList);

        for(Rating movie_filter: moviesList){
            String item = movie_filter.getItem();
            String title = MovieDatabase.getTitle(item);
            System.out.println(movie_filter.getValue()+" " + "Time: " + 
                MovieDatabase.getMinutes(item)+ " " + title);

        }
        System.out.println("Found " + moviesList.size() + " movies");
    }

    public void printAverageRatingsByDirectors(){
        int minimalRaters = 4;
        //String directorName ="Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        DirectorsFilter directorName= new DirectorsFilter
            ("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");

        //DirectorsFilter directorObj = new DirectorsFilter(directorName);
        ThirdRatings thirdRObj = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Total number of Raters " + thirdRObj.getRaterSize());
        System.out.println("Total number of movies " + MovieDatabase.size());

        ArrayList<Rating> moviesList = thirdRObj.getAverageRatingsByFilter(minimalRaters, 
                directorName);
        Collections.sort(moviesList);
        for(Rating movie_filter: moviesList){
            String item = movie_filter.getItem();
            String title = MovieDatabase.getTitle(item);
            System.out.println(movie_filter.getValue()+" " + title );
            System.out.println("Directors: " + MovieDatabase.getDirector(item));

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

        ThirdRatings thirdRObj = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Total number of Raters " + thirdRObj.getRaterSize());
        System.out.println("Total number of movies " + MovieDatabase.size());

        ArrayList<Rating> moviesList = thirdRObj.getAverageRatingsByFilter(minimalRaters, 
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

    public void printAverageRatingsByDirectorsAndMinutes(){
        int minimalRaters = 3;
        int min = 90;
        int max = 180;
        String directorName ="Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";

        DirectorsFilter directorObj = new DirectorsFilter(directorName);
        MinutesFilter minutesObj = new MinutesFilter(min, max);
        AllFilters filters = new AllFilters();
        filters.addFilter(directorObj);
        filters.addFilter(minutesObj);

        ThirdRatings thirdRObj = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Total number of Raters " + thirdRObj.getRaterSize());
        System.out.println("Total number of movies " + MovieDatabase.size());

        ArrayList<Rating> moviesList = thirdRObj.getAverageRatingsByFilter(minimalRaters, 
                filters);
        Collections.sort(moviesList);

        for(Rating movie_filter: moviesList){
            String item = movie_filter.getItem();
            String title = MovieDatabase.getTitle(item);
            System.out.println(movie_filter.getValue()+" " + "Title: " + 
                MovieDatabase.getMinutes(item) + " " + title );
            System.out.println("Directors: " + MovieDatabase.getDirector(item));

        }
        System.out.println("Found " + moviesList.size() + " movies");
    }

    
}
