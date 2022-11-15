package pro.verron.datamunging

import java.util.function.Function

object Parsers {
    val INTEGER_PARSER = of { s: String -> s.toInt() }
    val DOUBLE_PARSER = of { s: String -> s.toDouble() }
    val STRING_PARSER = of { s: String -> s }

    private fun <U> of(parser: Function<String, U>): Function<String, U> {
        return Function { line: String -> line.trim().transform(parser) }
    }
}