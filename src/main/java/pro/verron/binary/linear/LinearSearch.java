package pro.verron.binary.linear;

import pro.verron.binary.BinarySearch;

import java.util.List;
import java.util.OptionalInt;

import static java.util.OptionalInt.empty;

public class LinearSearch<T extends Comparable<T>> implements BinarySearch<T> {
    @Override
    public OptionalInt findIndex(T searchedValue, List<T> sortedValues) {
        for (var i = 0; i < sortedValues.size(); i++)
            if (sortedValues.get(i) == searchedValue)
                return OptionalInt.of(i);
        return empty();
    }
}