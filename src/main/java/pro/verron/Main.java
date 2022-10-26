package pro.verron;

public class Main {
    static int linearSearch(int value, int[] sortedArray) {
        for (int i = 0; i < sortedArray.length; i++) {
            if(sortedArray[i] == value)
                return i;
        }
        return -1;
    }

    static int binarySearch(int value, int[] sortedArray) {
        for (int i = 0; i < sortedArray.length; i++) {
            if(sortedArray[i] == value)
                return i;
        }
        return -1;
    }
}