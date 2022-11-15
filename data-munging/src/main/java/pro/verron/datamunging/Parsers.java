package pro.verron.datamunging;

import java.util.function.Function;

public final class Parsers {
    private Parsers(){
        throw new RuntimeException("Utility class should not be instantiated.");
    }
    public static final Function<String, Integer> INTEGER_PARSER = of(Integer::parseInt);
    public static final Function<String, Double> DOUBLE_PARSER = of(Double::parseDouble);
    public static final Function<String, String> STRING_PARSER = of(String::trim);
    public static <U> Function<String, U> of(Function<String, U> parser) {
        return line -> line.trim().transform(parser);
    }
}
