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

            if (currentValue == searchedValue)
                return OptionalInt.of(currentIndex);

            if (currentValue.compareTo(searchedValue) > 0)
                highestIndex = currentIndex - 1;
            else
                lowestIndex = currentIndex + 1;
        }
        return empty();
    }
}
