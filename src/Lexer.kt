package com.beust.klaxon

import java.io.File
import java.util.regex.Pattern

public enum class Type {
    VALUE;
    LEFT_BRACE;
    RIGHT_BRACE;
    LEFT_BRACKET;
    RIGHT_BRACKET;
    COMMA;
    COLON;
    STRING;
    END;
}

class Token(val tokenType: Type, val value: String?) {
    fun toString() : String {
        val v = if (value != null) { " (" + value + ")" } else {""}
        val result = tokenType.toString() + v
        return result
    }
}

public class Lexer(val fileName: String) {
    val bytes = File(fileName).readBytes()
    val END = Token(Type.END, null)
    var index = 0

    fun isSpace(c: Char): Boolean {
        return c == ' ' || c == '\r' || c == '\n' || c == 't'
    }

    private fun nextChar() : Char {
        return bytes[index++].toChar()
    }

    private fun isDone() : Boolean {
        return index >= bytes.size
    }

    fun nextToken() : Token {

        if (isDone()) {
            return END
        }

        var tokenType = Type.END
        var c = nextChar()
        var currentValue = StringBuilder()

        while (! isDone() && isSpace(c)) {
            c = nextChar()
        }

        if ('"' == c) {
            tokenType = Type.STRING
            if (! isDone()) {
                c = nextChar()
                while (index < bytes.size && c != '"') {
                    currentValue.append(c)
                    if (c == '\\' && index < bytes.size) {
                        c = nextChar()
                        currentValue.append(c)
                    }
                    c = nextChar()
                }
            } else {
                throw RuntimeException("Unterminated string")
            }
        } else if ('{' == c) {
            tokenType = Type.LEFT_BRACE
        } else if ('}' == c) {
            tokenType = Type.RIGHT_BRACE
        } else if ('[' == c) {
            tokenType = Type.LEFT_BRACKET
        } else if (']' == c) {
            tokenType = Type.RIGHT_BRACKET
        } else if (':' == c) {
            tokenType = Type.COLON
        } else if (',' == c) {
            tokenType = Type.COMMA
        } else if (! isDone()) {
            while (index < bytes.size && c == '-' || c == '+' || c == '.'
                    || c.isDigit()) {
                currentValue.append(c)
                c = nextChar()
            }
            tokenType = Type.VALUE
        }

        val value = currentValue.toString()
        return Token(tokenType, if (value.length() > 0) value else null)
    }
}
