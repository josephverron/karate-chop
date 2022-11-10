package pro.verron.datamunging;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

public class Weather {
    public static void main(String[] args) throws IOException {
        WeatherRowParser parser = WeatherRowParser.INSTANCE;
        try (Stream<String> lines = Files.lines(Paths.get("weather.dat"))) {
            String day = lines
                    .filter(parser::canParse)
                    .map(parser)
                    .min(comparing(WeatherRow::tempRange))
                    .map(WeatherRow::day)
                    .map(String::valueOf)
                    .orElse("None");
            System.out.println("Day with lowest temperature range: " + day);
        }
    }

}
