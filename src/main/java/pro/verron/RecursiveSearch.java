package pro.verron;

import java.util.List;
import java.util.OptionalInt;

import static java.util.OptionalInt.empty;

/**
 * Recursive
 */
class RecursiveSearch<T extends Comparable<T>> implements BinarySearch<T> {

    @Override
    public OptionalInt findIndex(T searchedValue, List<T> sortedValues) {
        return recurse(searchedValue, sortedValues, 0, sortedValues.size() - 1);
    }

    OptionalInt recurse(T searchedValue, List<T> sortedValues, int lowestIndex, int highestIndex) {
        if (highestIndex < lowestIndex)
            return empty();

        var currentIndex = (lowestIndex + highestIndex) / 2;
        var currentValue = sortedValues.get(currentIndex);

        if (currentValue.compareTo(searchedValue) == 0) {
            return OptionalInt.of(currentIndex);
        }

        if (currentValue.compareTo(searchedValue) > 0) {
            var nextHighestIndex = currentIndex - 1;
            return recurse(searchedValue, sortedValues, lowestIndex, nextHighestIndex);
        }

        if (currentValue.compareTo(searchedValue) < 0) {
            var nextLowestIndex = currentIndex + 1;
            return recurse(searchedValue, sortedValues, nextLowestIndex, highestIndex);
        }

        return empty();
    }
}
