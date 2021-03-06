package com.zelkatani.model.map

import com.zelkatani.antlr.ClimateLexer
import com.zelkatani.antlr.ClimateParser
import com.zelkatani.model.ModelBuilder
import com.zelkatani.model.Modifiers
import com.zelkatani.visitor.map.ClimateVisitor
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.File

/**
 * A model for `climate.txt`. Currently unused.
 */
data class Climate(val climateModifiers: Map<String, Modifiers>, val climateProvinces: Map<String, List<Int>>) {
    companion object : ModelBuilder<Climate> {
        override fun from(file: File): Climate {
            val climateLexer = ClimateLexer(CharStreams.fromReader(file.reader()))
            val climateParser = ClimateParser(CommonTokenStream(climateLexer))

            val climateContext = climateParser.climate()
            val climateVisitor = ClimateVisitor()

            return climateVisitor.visitClimate(climateContext)
        }
    }
}