package de.goldmensch.commanddispatcher.util;

import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ArraySets {
    public static @NotNull <T> T[] getBiggest(@NotNull Set<T[]> set) {
        T[] biggest = null;
        for(T[] c : set) {
            if(c.length > biggest.length) {
                biggest = c;
            }
        }

        return biggest == null
                ? (T[]) new Object[0]
                : biggest;
    }

}
