package de.goldmensch.commanddispatcher.util;

import java.util.HashSet;
import java.util.Set;

public class ArraySetUtils {

    public static <T> T[] getBiggest(Set<T[]> set) {
        T[] biggest = (T[]) new Object[0];
        for(T[] c : set) {
            if(c.length > biggest.length) {
                biggest = c;
            }
        }
        return biggest;
    }

    public static <T> Set<T[]> getAllWithLength(int length, Set<T[]> set){
        Set<T[]> validArrays = new HashSet<>();
        for(T[] c : set) {
            if(c.length == length) {
                validArrays.add(c);
            }
        }
        return validArrays;
    }

    public static <T> Set<T[]> getAllBiggerOrEqualThan(int length, Set<T[]> set) {
        Set<T[]> validArrays = new HashSet<>();
        for(T[] c : set) {
            if(length <= length) {
                validArrays.add(c);
            }
        }
        return validArrays;
    }

}
