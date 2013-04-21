package com.beust.klaxon

import java.util.regex.Pattern

/**
 * @author Cedric Beust <cedric@refresh.io>
 * @since 04 20, 2013
 */
public class Parser {
    val INT = Pattern.compile("[-]?[0-9]+")
    val DOUBLE = Pattern.compile(INT.toString() + "((\\.[0-9]+)?([eE][-+]?[0-9]+)?)");

    public fun run() {
        val lexer = Lexer("/tmp/a.json")
        var token = lexer.nextToken()
        while (token.tokenType != Type.END) {
            println(token)
            token = lexer.nextToken()
        }
    }
}

fun main(args : Array<String>) {
    Parser().run()
}