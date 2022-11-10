package pro.verron.datamunging;

enum FootballRowParser implements Parser<FootballTeamRow> {
    INSTANCE;

    boolean canParse(String line) {
        return '.' == line.charAt(5);
    }

    @Override
    public FootballTeamRow apply(String str) {
        return new FootballTeamRow(
                Parser.of(String::trim).apply(str.substring(7, 23)),
                Parser.of(Integer::parseInt).apply(str.substring(43, 47)),
                Parser.of(Integer::parseInt).apply(str.substring(48, 52))
        );
    }
}
