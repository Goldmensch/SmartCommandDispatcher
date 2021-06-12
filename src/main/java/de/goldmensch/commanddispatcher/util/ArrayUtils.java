package de.goldmensch.commanddispatcher.util;

public class ArrayUtils {

    public static <T> boolean startWith(T[] r, T[] s) {
        if(s.length > r.length) return false;
        for(int i = 0; i < s.length; i++) {
            if(!r[i].equals(s[i])) {
                return false;
            }
        }
        return true;
    }

    public static String[] toLowerCase(String[] a) {
        String[] lowerArray = new String[a.length];
        for(int i = 0; i < a.length; i++) {
            lowerArray[i] = a[i].toLowerCase();
        }
        return lowerArray;
    }

    public static <T> T[] getBiggest(T[]... arrays) {
        T[] biggest = arrays[0];
        for(T[] c : arrays) {
            if(arrays.length > biggest.length) {
                biggest = c;
            }
        }
        return biggest;
    }

    public static String buildString(String[] a) {
        StringBuilder builder = new StringBuilder();
        for(String c : a) {
            builder.append(a);
            builder.append(" ");
        }
        return builder.toString().trim();
    }

}
