package pro.verron.datamunging

import pro.verron.datamunging.Parsers.INTEGER_PARSER
import pro.verron.datamunging.Parsers.STRING_PARSER
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.Comparator.comparing
import kotlin.math.abs

class FootballTeamRow(val team: String, private val scoredFor: Int, private val scoredAgainst: Int){
    fun ratio(): Int{
        return abs(scoredFor - scoredAgainst)
    }
}
private fun parser(str: String): FootballTeamRow {
    return FootballTeamRow(
            STRING_PARSER.apply(str.substring(7, 23)),
            INTEGER_PARSER.apply(str.substring(43, 47)),
            INTEGER_PARSER.apply(str.substring(48, 52))
    )
}
private fun canParse(line: String): Boolean {
    return '.' == line[5]
}
@Throws(IOException::class)
fun main() {
    Files.lines(Paths.get("data-munging/football.dat")).use { lines ->
        val team = lines
                .filter(::canParse)
                .map(::parser)
                .min(comparing(FootballTeamRow::ratio))
                .map(FootballTeamRow::team)
                .orElse("None")
        println("Team with minimal diff between goal taken and goal given: $team")
    }
}