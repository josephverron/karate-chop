package pro.verron;

import java.util.OptionalInt;
import java.util.function.IntFunction;

class Dichotomy<T extends Comparable<T>> {
    private int start;
    private int end;
    private int pivot;
    private final T target;
    private boolean found;
    private boolean ongoing;

    public Dichotomy(int start, int end, T target) {
        this.start = start;
        this.end = end;
        this.pivot = computePivot();

        this.target = target;

        this.found = false;
        this.ongoing = start <= end;
    }

    void readPivotValue(IntFunction<T> pivotValueReader) {
        var pivotValue = pivotValueReader.apply(pivot);
        var comparison = pivotValue.compareTo(target) ;

        if (comparison > 0)
            end = pivot - 1;
        else if (comparison < 0)
            start = pivot + 1;
        else
            found = true;

        pivot = computePivot();

        if(start > end || found)
            ongoing = false;
    }

    private int computePivot() {
        return (start + end) / 2;
    }

    public OptionalInt result() {
        return found
                ? OptionalInt.of(pivot)
                : OptionalInt.empty();
    }

    boolean ongoing() {
        return ongoing;
    }
}
