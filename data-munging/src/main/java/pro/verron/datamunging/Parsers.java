package pro.verron.datamunging;

import java.util.function.Function;

public class Parsers {
    public static final Parser<Integer> INTEGER_PARSER = of(Integer::parseInt);
    public static final Parser<Double> DOUBLE_PARSER = of(Double::parseDouble);
    public static final Parser<String> STRING_PARSER = of(String::trim);

    public static <U> Parser<U> of(Function<String, U> function) {
        return line -> line.trim().transform(function);
    }
}
