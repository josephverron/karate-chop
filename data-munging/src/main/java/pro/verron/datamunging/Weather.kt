package pro.verron.datamunging;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;
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
    private static final Function<String, WeatherRow> PARSER = line -> new WeatherRow(
            INTEGER_PARSER.apply(line.substring(0, 4)),
            DOUBLE_PARSER.apply(line.substring(4, 8)),
            DOUBLE_PARSER.apply(line.substring(10, 14))
    );
    public static boolean canParse(String line) {
        return line.length() > 3 && isDigit(line.charAt(3));
    }
    public static void main(String[] args) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get("weather.dat"))) {
            String day = lines
                    .filter(Weather::canParse)
                    .map(PARSER)
                    .min(comparing(WeatherRow::tempRange))
                    .map(WeatherRow::day)
                    .map(String::valueOf)
                    .orElse("None");
            System.out.println("Day with lowest temperature range: " + day);
        }
    }
}