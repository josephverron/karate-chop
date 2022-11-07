package pro.verron.binary.recursive;

import pro.verron.binary.BinarySearch;

import java.util.List;
import java.util.OptionalInt;

import static java.util.OptionalInt.empty;

/**
 * Recursive
 */
class RecursiveSearch<T extends Comparable<T>> implements BinarySearch<T> {

    @Override
    public OptionalInt findIndex(T searchedValue, List<T> sortedValues) {
        return findIndex(searchedValue, sortedValues, 0, sortedValues.size() - 1);
    }

    private OptionalInt findIndex(T searchedValue, List<T> sortedValues, int lowestIndex, int highestIndex) {
        if (highestIndex < lowestIndex)
            return empty();

        var currentIndex = (lowestIndex + highestIndex) / 2;
        var currentValue = sortedValues.get(currentIndex);
        int comparison = currentValue.compareTo(searchedValue);

        if (comparison > 0)
            return findIndex(searchedValue, sortedValues, lowestIndex, currentIndex - 1);
        else if (comparison < 0)
            return findIndex(searchedValue, sortedValues, currentIndex + 1, highestIndex);
        else
            return OptionalInt.of(currentIndex);
    }
}
