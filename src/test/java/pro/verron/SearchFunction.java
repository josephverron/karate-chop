package pro.verron;

import java.util.List;

public interface SearchFunction<T extends Comparable<T>> {
    int search(T searchedValue, List<T> sortedValues);
}
