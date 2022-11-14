package pro.verron.datamunging;

import static pro.verron.datamunging.Parsers.*;

enum FootballRowParser implements Parser<FootballTeamRow> {
    INSTANCE;

    boolean canParse(String line) {
        return '.' == line.charAt(5);
    }

    @Override
    public FootballTeamRow apply(String str) {
        return new FootballTeamRow(
                STRING_PARSER.apply(str.substring(7, 23)),
                INTEGER_PARSER.apply(str.substring(43, 47)),
                INTEGER_PARSER.apply(str.substring(48, 52))
        );
    }
}
