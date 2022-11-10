package pro.verron.datamunging;

import static java.lang.Character.isDigit;

enum WeatherRowParser implements Parser<WeatherRow> {
    INSTANCE;

    @Override
    public WeatherRow apply(String str) {
        return new WeatherRow(
                Parser.of(Integer::parseInt).apply(str.substring(0, 4)),
                Parser.of(Double::parseDouble).apply(str.substring(4, 8)),
                Parser.of(Double::parseDouble).apply(str.substring(10, 14))
        );
    }

    public boolean canParse(String line) {
        return line.length() > 3 && isDigit(line.charAt(3));
    }
}
