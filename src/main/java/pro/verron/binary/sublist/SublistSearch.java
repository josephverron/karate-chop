package pro.verron.binary.sublist;

import pro.verron.binary.BinarySearch;

import java.util.List;
import java.util.OptionalInt;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Sublist approach
 */
class SublistSearch<T extends Comparable<T>> implements BinarySearch<T> {
    @Override
    public OptionalInt findIndex(T searchedValue, List<T> sortedValues) {
        var sublist = sortedValues;

        var startIndex = new AtomicInteger();
        SubLister<T> subLister = (list, start, end) -> {
            startIndex.addAndGet(start);
            return list.subList(start, end);
        };

        while (!sublist.isEmpty()) {
            var currentIndex = sublist.size() / 2;
            var currentValue = sublist.get(currentIndex);
            int comparison = searchedValue.compareTo(currentValue);

            if (comparison > 0)
                sublist = subLister.apply(sublist, currentIndex + 1, sublist.size());
            else if(comparison < 0)
                sublist = subLister.apply(sublist,0, currentIndex);
            else
                return OptionalInt.of(startIndex.get() + currentIndex);
        }
        return OptionalInt.empty();
    }
}
