package pro.verron.binary.sublist;

import pro.verron.binary.BinarySearch;

import java.util.List;
import java.util.OptionalInt;

/**
 * Sublist approach
 */
public class SublistSearch<T extends Comparable<T>> implements BinarySearch<T> {
    @Override
    public OptionalInt findIndex(T searchedValue, List<T> sortedValues) {
        Sublist<T> list = new Sublist<>(0, sortedValues);

        while (!list.isEmpty()) {
            var currentIndex = list.size() / 2;
            var currentValue = list.get(currentIndex);
            int comparison = searchedValue.compareTo(currentValue);

            if (comparison > 0)
                list = list.subList(currentIndex + 1, list.size());
            else if(comparison < 0)
                list = list.subList(0, currentIndex);
            else
                return OptionalInt.of(list.rootLevelIndex() + currentIndex);
        }
        return OptionalInt.empty();
    }

}
