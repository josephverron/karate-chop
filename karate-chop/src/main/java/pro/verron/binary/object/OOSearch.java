package pro.verron.binary.object;

import pro.verron.utils.Range;
import pro.verron.binary.BinarySearch;

import java.util.List;
import java.util.OptionalInt;
import java.util.function.IntFunction;

public class OOSearch<T extends Comparable<T>> implements BinarySearch<T> {
    @Override
    public OptionalInt findIndex(T searchedValue, List<T> sortedValues) {
        IntFunction<T> indexAccessor = sortedValues::get;
        var range = Range.ofSize(sortedValues.size());
        var dichotomy = new Dichotomy<>(searchedValue, range);
        while (dichotomy.ongoing()) {
            dichotomy.assessTargetPosition(indexAccessor);
        }
        return dichotomy.result();
    }

}