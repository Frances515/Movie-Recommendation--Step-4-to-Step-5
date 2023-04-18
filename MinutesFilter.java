
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
public class MinutesFilter implements Filter {
    private int min;
    private int max;
    
    public MinutesFilter(int minMinutes, int maxMinutes){
        min = minMinutes;
        max = maxMinutes;
    }
    
    @Override
    public boolean satisfies(String id){
        return MovieDatabase.getMinutes(id) >= min && MovieDatabase.getMinutes(id) <= max;
    }

}
