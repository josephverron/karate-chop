package pro.verron.datamunging;

public class Parsers {
    public static final Parser<Integer> INTEGER_PARSER = of(Integer::parseInt);
    public static final Parser<Double> DOUBLE_PARSER = of(Double::parseDouble);
    public static final Parser<String> STRING_PARSER = of(String::trim);
    public static <U> Parser<U> of(Parser<U> parser) {
        return line -> line.trim().transform(parser::parse);
    }
}
