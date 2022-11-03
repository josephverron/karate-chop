package pro.verron;

public class Main {

    public static final int VALUE_NOT_FOUND = -1;

    static int linearSearch(int value, int[] sortedArray) {
        for (int i = 0; i < sortedArray.length; i++) {
            if (sortedArray[i] == value)
                return i;
        }
        return VALUE_NOT_FOUND;
    }

    /**
     * Procedural
     */
    static int proceduralBinarySearch(int searchedValue, int[] sortedValues) {
        if (sortedValues.length == 0)
            return VALUE_NOT_FOUND;

        int lowestIndex = 0;
        int highestIndex = sortedValues.length;

        while (highestIndex > lowestIndex) {
            int currentIndex = (lowestIndex + highestIndex) / 2;
            int currentValue = sortedValues[currentIndex];

            if (currentValue == searchedValue)
                return currentIndex;

            if (currentIndex == highestIndex)
                return VALUE_NOT_FOUND;

            if (currentIndex == lowestIndex)
                return VALUE_NOT_FOUND;

            if (currentValue > searchedValue)
                highestIndex = currentIndex;
            else
                lowestIndex = currentIndex;
        }
        return VALUE_NOT_FOUND;
    }

    /**
     * Dichotomy Object
     */

    /**
     * Recursive
     */
    static int recursiveBinarySearch(int searchedValue, int[] sortedValues) {
        return recursiveBinarySubSearch(searchedValue, sortedValues, 0, sortedValues.length - 1);
    }

    static int recursiveBinarySubSearch(int searchedValue, int[] sortedValues, int lowestIndex, int highestIndex) {
        if (highestIndex < lowestIndex)
            return VALUE_NOT_FOUND;

        if (highestIndex == lowestIndex) {
            return sortedValues[lowestIndex] == searchedValue
                    ? lowestIndex
                    : VALUE_NOT_FOUND;
        }

        int currentIndex = (lowestIndex + highestIndex) / 2;
        int currentValue = sortedValues[currentIndex];

        if (currentValue > searchedValue)
            return recursiveBinarySubSearch(searchedValue, sortedValues, lowestIndex, currentIndex);

        if (currentValue < searchedValue)
            return recursiveBinarySubSearch(searchedValue, sortedValues, currentIndex + 1, highestIndex);

        return currentIndex;
    }

    /**
     * Sublist approach
     */
}