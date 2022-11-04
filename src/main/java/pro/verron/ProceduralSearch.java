package pro.verron;

import java.util.List;
import java.util.OptionalInt;

import static java.util.OptionalInt.empty;

public class ProceduralSearch<T extends Comparable<T>> implements BinarySearch<T> {
    @Override
    public OptionalInt findIndex(T searchedValue, List<T> sortedValues) {
        var lowestIndex = 0;
        var highestIndex = sortedValues.size() - 1;

        while (highestIndex >= lowestIndex) {
            var currentIndex = (lowestIndex + highestIndex) / 2;
            var currentValue = sortedValues.get(currentIndex);
            var comparison = currentValue.compareTo(searchedValue);

            if (comparison > 0)
                highestIndex = currentIndex - 1;
            else if (comparison < 0)
                lowestIndex = currentIndex + 1;
            else
                return OptionalInt.of(currentIndex);
        }
        return empty();
    }
}
