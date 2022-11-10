package pro.verron.datamunging;

import java.util.function.Function;

public interface Parser<T> extends Function<String, T> {
    static <U> Parser<U> of(Function<String, U> function) {
        return line -> line.trim().transform(function);
    }
}
