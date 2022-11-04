package pro.verron;

import java.util.List;

public class Main {

    public static final int VALUE_NOT_FOUND = -1;

    static <T extends Comparable<T>> int linearSearch(T value, List<T> sortedValues) {
        for (int i = 0; i < sortedValues.size(); i++) {
            if (sortedValues.get(i) == value)
                return i;
        }
        return VALUE_NOT_FOUND;
    }

    /**
     * Procedural
     */
    static <T extends Comparable<T>> int proceduralBinarySearch(T searchedValue, List<T> sortedValues) {
        if (sortedValues.size() == 0)
            return VALUE_NOT_FOUND;

        int lowestIndex = 0;
        int highestIndex = sortedValues.size() - 1;

        while (highestIndex >= lowestIndex) {
            int currentIndex = (lowestIndex + highestIndex) / 2;
            var currentValue = sortedValues.get(currentIndex);

            if (currentValue == searchedValue)
                return currentIndex;

            if (currentValue.compareTo(searchedValue) > 0)
                highestIndex = currentIndex - 1;
            else
                lowestIndex = currentIndex + 1;
        }
        return VALUE_NOT_FOUND;
    }

    /**
     * Dichotomy Object
     */

    /**
     * Recursive
     */
    static <T extends Comparable<T>> int recursiveBinarySearch(T searchedValue, List<T> sortedValues) {
        return recursiveBinarySubSearch(searchedValue, sortedValues, 0, sortedValues.size() - 1);
    }

    static <T extends Comparable<T>> int recursiveBinarySubSearch(T searchedValue, List<T> sortedValues, int lowestIndex, int highestIndex) {
        if (highestIndex < lowestIndex)
            return VALUE_NOT_FOUND;

        if (highestIndex == lowestIndex) {
            return sortedValues.get(lowestIndex) == searchedValue
                    ? lowestIndex
                    : VALUE_NOT_FOUND;
        }

        int currentIndex = (lowestIndex + highestIndex) / 2;
        var currentValue = sortedValues.get(currentIndex);

        if (currentValue.compareTo(searchedValue) > 0)
            return recursiveBinarySubSearch(searchedValue, sortedValues, lowestIndex, currentIndex - 1);

        if (currentValue.compareTo(searchedValue) < 0)
            return recursiveBinarySubSearch(searchedValue, sortedValues, currentIndex + 1, highestIndex);

        return currentIndex;
    }

    /**
     * Sublist approach
     */

    static <T extends Comparable<T>> int sublistBinarySearch(T searchedValue, List<T> sortedValues){
        var sublist = sortedValues;
        var leftBoundary = 0;
        while (!sublist.isEmpty()) {
            int currentIndex = sublist.size() / 2;
            var currentValue = sublist.get(currentIndex);

            if(currentValue == searchedValue)
                return leftBoundary + currentIndex;

            if (searchedValue.compareTo(currentValue) > 0) {
                leftBoundary += currentIndex + 1;
                sublist = sublist.subList(currentIndex + 1, sublist.size());
            } else
                sublist = sublist.subList(0, currentIndex);

        }
        return VALUE_NOT_FOUND;
    }

}