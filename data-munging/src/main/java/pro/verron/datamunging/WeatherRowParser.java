package pro.verron.datamunging;

import static java.lang.Character.isDigit;
import static pro.verron.datamunging.Parsers.DOUBLE_PARSER;
import static pro.verron.datamunging.Parsers.INTEGER_PARSER;

enum WeatherRowParser implements Parser<WeatherRow> {
    INSTANCE;

    @Override
    public WeatherRow apply(String str) {
        return new WeatherRow(
                INTEGER_PARSER.apply(str.substring(0, 4)),
                DOUBLE_PARSER.apply(str.substring(4, 8)),
                DOUBLE_PARSER.apply(str.substring(10, 14))
        );
    }

    public boolean canParse(String line) {
        return line.length() > 3 && isDigit(line.charAt(3));
    }
}
