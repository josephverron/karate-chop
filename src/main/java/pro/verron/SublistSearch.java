package pro.verron;

import java.util.List;
import java.util.OptionalInt;

/**
 * Sublist approach
 */
class SublistSearch<T extends Comparable<T>> implements BinarySearch<T> {
    @Override
    public OptionalInt findIndex(T searchedValue, List<T> sortedValues) {
        var globalMargin = 0;
        var sublist = sortedValues;

        while (!sublist.isEmpty()) {
            var currentIndex = sublist.size() / 2;
            var currentValue = sublist.get(currentIndex);

            if (currentValue == searchedValue)
                return OptionalInt.of(globalMargin + currentIndex);

            if (searchedValue.compareTo(currentValue) > 0) {
                int subMargin = currentIndex + 1;
                globalMargin += subMargin;
                sublist = sublist.subList(subMargin, sublist.size());
            } else
                sublist = sublist.subList(0, currentIndex);
        }
        return OptionalInt.empty();
    }
}
