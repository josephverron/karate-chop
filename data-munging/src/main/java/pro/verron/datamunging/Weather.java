package pro.verron.datamunging;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.lang.Character.isDigit;
import static java.util.Comparator.comparing;
import static pro.verron.datamunging.Parsers.DOUBLE_PARSER;
import static pro.verron.datamunging.Parsers.INTEGER_PARSER;

public class Weather {
    record WeatherRow(int day, double maxTemp, double minTemp) {
        public double tempRange() {
            return maxTemp - minTemp;
        }
    }

    enum WeatherRowParser implements Parser<WeatherRow> {
        INSTANCE;

        @Override
        public WeatherRow parse(String line) {
            return new WeatherRow(
                    INTEGER_PARSER.parse(line.substring(0, 4)),
                    DOUBLE_PARSER.parse(line.substring(4, 8)),
                    DOUBLE_PARSER.parse(line.substring(10, 14))
            );
        }
        public boolean canParse(String line) {
            return line.length() > 3 && isDigit(line.charAt(3));
        }

    }

    public static void main(String[] args) throws IOException {
        WeatherRowParser parser = WeatherRowParser.INSTANCE;
        try (Stream<String> lines = Files.lines(Paths.get("weather.dat"))) {
            String day = lines
                    .filter(parser::canParse)
                    .map(parser::parse)
                    .min(comparing(WeatherRow::tempRange))
                    .map(WeatherRow::day)
                    .map(String::valueOf)
                    .orElse("None");
            System.out.println("Day with lowest temperature range: " + day);
        }
    }
}
