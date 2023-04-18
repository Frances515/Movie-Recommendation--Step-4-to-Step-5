
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class GenreFilter implements Filter {
    private String newGenre;
    public GenreFilter(String genre){
        newGenre = genre;
    }
    
    @Override
    public boolean satisfies(String id){
        return MovieDatabase.getGenres(id).contains(newGenre);
    }
}
