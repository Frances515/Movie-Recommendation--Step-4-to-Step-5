
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class DirectorsFilter implements Filter {
    
    private String directorName;
    /*public DirectorsFilter(String directors){
        directorName = directors;
    }
    
    @Override
    public boolean satisfies(String id){
        return directorName.contains(MovieDatabase.getDirector(id));
    }*/
    
    private String[] directorsList;
    
    public DirectorsFilter(String directors) {
        directorsList = directors.split(",");
    }
    
    @Override
    public boolean satisfies(String id) {
        String dirsOfMovie = MovieDatabase.getDirector(id);
        for(String d : directorsList) {
            if(dirsOfMovie.contains(d)) {
                return true;
            }
        }
        return false;
    }
}
