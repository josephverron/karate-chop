package pro.verron.binary;

import java.util.List;
import java.util.OptionalInt;

@FunctionalInterface
public interface BinarySearch<T extends Comparable<T>> {
    default int getIndex(T searchedValue, List<T> sortedValues) {
        return findIndex(searchedValue, sortedValues)
                .orElse(-1);
    }
    OptionalInt findIndex(T searchedValue, List<T> sortedValues);
}
