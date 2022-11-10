package pro.verron.datamunging;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

public class Football {
    public static void main(String[] args) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get("football.dat"))) {
            String team = lines
                    .skip(1)
                    .filter(line -> !line.startsWith("   ----"))
                    .map(toFootballRow())
                    .min(comparing(footballRow -> Math.abs(footballRow.scoredFor() - footballRow.scoredAgainst())))
                    .map(FootballRow::team)
                    .map(String::valueOf)
                    .orElse("None");
            System.out.println("Team with minimal diff between goal taken and goal given: " + team);
        }
    }

    private static Function<String, FootballRow> toFootballRow() {
        return str -> new FootballRow(
                extract(str, 7, 23, Function.identity()),
                extract(str, 43, 47, Integer::parseInt),
                extract(str, 48, 52, Integer::parseInt)
        );
    }

    private static <T> T extract(String str, int beginIndex, int endIndex, Function<String, T> function) {
        var dayString = str.substring(beginIndex, endIndex)
                .trim()
                .replace("*", "");
        return function.apply(dayString);
    }

    private record FootballRow(
            String team,
            int scoredFor,
            int scoredAgainst
    ) {
    }
}
