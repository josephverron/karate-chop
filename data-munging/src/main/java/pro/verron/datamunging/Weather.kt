package pro.verron.datamunging

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.Comparator.comparing

class WeatherRow(val day: Int, private val maxTemp: Double, private val minTemp: Double) {
    fun tempRange(): Double {
        return maxTemp - minTemp
    }
}
private fun parser(line: String): WeatherRow {
    return WeatherRow(
            Parsers.INTEGER_PARSER.apply(line.substring(0, 4)),
            Parsers.DOUBLE_PARSER.apply(line.substring(4, 8)),
            Parsers.DOUBLE_PARSER.apply(line.substring(10, 14)),
    )
}
private fun canParse(line: String): Boolean {
    return line.length > 3 && Character.isDigit(line[3])
}
@Throws(IOException::class)
fun main(args: Array<String>) {
    Files.lines(Paths.get("weather.dat")).use { lines ->
        val day = lines
                .filter(::canParse)
                .map(::parser)
                .min(comparing(WeatherRow::tempRange))
                .map(WeatherRow::day)
                .map(Int::toString)
                .orElse("None")
        println("Day with lowest temperature range: $day")
    }
}