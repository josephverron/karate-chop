package pro.verron.datamunging;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static pro.verron.datamunging.Parsers.INTEGER_PARSER;
import static pro.verron.datamunging.Parsers.STRING_PARSER;

public class Football {
    record FootballTeamRow(String team, int scoredFor, int scoredAgainst) {}

    enum FootballRowParser implements Parser<FootballTeamRow> {
        INSTANCE;

        @Override
        public FootballTeamRow parse(String str) {
            return new FootballTeamRow(
                    STRING_PARSER.parse(str.substring(7, 23)),
                    INTEGER_PARSER.parse(str.substring(43, 47)),
                    INTEGER_PARSER.parse(str.substring(48, 52))
            );
        }
        boolean canParse(String line) {
            return '.' == line.charAt(5);
        }

    }

    public static void main(String[] args) throws IOException {
        FootballRowParser parser = FootballRowParser.INSTANCE;
        try (Stream<String> lines = Files.lines(Paths.get("football.dat"))) {
            String team = lines
                    .filter(parser::canParse)
                    .map(parser::parse)
                    .min(comparing(footballTeamRow -> Math.abs(footballTeamRow.scoredFor() - footballTeamRow.scoredAgainst())))
                    .map(FootballTeamRow::team)
                    .map(String::valueOf)
                    .orElse("None");
            System.out.println("Team with minimal diff between goal taken and goal given: " + team);
        }
    }
}
