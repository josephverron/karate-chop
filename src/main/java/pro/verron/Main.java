package pro.verron;

public class Main {

    public static final int VALUE_NOT_FOUND = -1;

    static int linearSearch(int value, int[] sortedArray) {
        for (int i = 0; i < sortedArray.length; i++) {
            if(sortedArray[i] == value)
                return i;
        }
        return VALUE_NOT_FOUND;
    }

    static int binarySearch(int searchedValue, int[] sortedValues) {
        if(sortedValues.length == 0)
            return VALUE_NOT_FOUND;

        int lowestIndex = 0;
        int highestIndex = sortedValues.length;

        while(highestIndex > lowestIndex){
            int currentIndex = (lowestIndex + highestIndex) / 2;
            int currentValue = sortedValues[currentIndex];

            if(currentValue == searchedValue)
                return currentIndex;

            if (currentIndex == highestIndex)
                return VALUE_NOT_FOUND;

            if (currentIndex == lowestIndex)
                return VALUE_NOT_FOUND;

            if(currentValue > searchedValue)
                highestIndex = currentIndex;
            else
                lowestIndex = currentIndex;
        }
        return VALUE_NOT_FOUND;
    }
}