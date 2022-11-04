package pro.verron;

import java.util.OptionalInt;
import java.util.function.IntFunction;

import static java.util.OptionalInt.empty;

class Dichotomy<T extends Comparable<T>> {
    private Range range;
    private final T target;
    private boolean found;

    public Dichotomy(T target, Range range) {
        this.range = range;
        this.target = target;
        this.found = false;
    }

    void assessTargetPosition(IntFunction<T> indexAccessor) {
        int pivotIndex = range.middle();
        var pivotValue = indexAccessor.apply(pivotIndex);
        var comparison = pivotValue.compareTo(target) ;

        if (comparison > 0)
            range = range.endingAt(pivotIndex - 1);
        else if (comparison < 0)
            range = range.startingAt(pivotIndex + 1);
        else
            found = true;
    }

    public OptionalInt result() {
        return !found ? empty() : OptionalInt.of(range.middle());
    }

    boolean ongoing() {
        return !found && !range.empty();
    }
}