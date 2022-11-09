package pro.verron.datamunging;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

public class Weather {
    public static void main(String[] args) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get("weather.dat"))) {
            String day = lines
                    .skip(2)
                    .limit(30)
                    .map(toWeatherRow())
                    .min(comparing(WeatherRow::tempRange))
                    .map(WeatherRow::day)
                    .map(String::valueOf)
                    .orElse("None");
            System.out.println("Day with lowest temperature range: " + day);
        }
    }

    private static Function<String, WeatherRow> toWeatherRow() {
        return str -> new WeatherRow(
                extract(str, 0, 4, Integer::parseInt),
                extract(str, 4, 10, Double::parseDouble),
                extract(str, 10, 16, Double::parseDouble)
        );
    }

    private static <T> T extract(String str, int beginIndex, int endIndex, Function<String, T> function) {
        var dayString = str.substring(beginIndex, endIndex).trim().replace("*", "");
        return function.apply(dayString);
    }

    private record WeatherRow(
            int day,
            double maxTemp,
            double minTemp
    ) {
        public double tempRange() {
            return maxTemp - minTemp;
        }
    }
}
