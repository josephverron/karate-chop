package pro.verron.datamunging;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

public class Football {
    public static void main(String[] args) throws IOException {
        FootballRowParser parser = FootballRowParser.INSTANCE;
        try (Stream<String> lines = Files.lines(Paths.get("football.dat"))) {
            String team = lines
                    .filter(parser::canParse)
                    .map(parser)
                    .min(comparing(footballTeamRow -> Math.abs(footballTeamRow.scoredFor() - footballTeamRow.scoredAgainst())))
                    .map(FootballTeamRow::team)
                    .map(String::valueOf)
                    .orElse("None");
            System.out.println("Team with minimal diff between goal taken and goal given: " + team);
        }
    }

}
