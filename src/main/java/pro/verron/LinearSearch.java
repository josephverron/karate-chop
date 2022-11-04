package pro.verron;

import java.util.List;
import java.util.OptionalInt;

import static java.util.OptionalInt.empty;

public class LinearSearch<T extends Comparable<T>> implements BinarySearch<T> {
    @Override
    public OptionalInt findIndex(T value, List<T> sortedValues) {
        for (var i = 0; i < sortedValues.size(); i++)
            if (sortedValues.get(i) == value)
                return OptionalInt.of(i);
        return empty();
    }
}