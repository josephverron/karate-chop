package pro.verron;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    static int chop(int value, int[] sortedArray) {
        for (int i = 0; i < sortedArray.length; i++) {
            if(sortedArray[i] == value)
                return i;
        }
        return -1;
    }
}