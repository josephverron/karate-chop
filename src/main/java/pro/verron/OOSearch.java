package pro.verron;

import java.util.List;
import java.util.OptionalInt;

public class OOSearch<T extends Comparable<T>> implements BinarySearch<T> {
    @Override
    public OptionalInt findIndex(T searchedValue, List<T> sortedValues) {
        var dichotomy = new Dichotomy<>(0, sortedValues.size() - 1, searchedValue);
        while (dichotomy.ongoing())
            dichotomy.readPivotValue(sortedValues::get);
        return dichotomy.result();
    }

}
