package com.beust.klaxon

import jet.MutableMap

open public class JsonObject {
    val map = hashMapOf<String, JsonObject>()

    fun error() : String {
        throw RuntimeException("Can't convert ${map} to string")
    }

    fun put(key: String, value: JsonObject) {
        map.put(key, value)
    }

    open fun asString() : String {
        return error()
    }
}

public class JsonString(val value: String) : JsonObject() {
    override fun asString() : String {
        return value
    }

    fun toString() : String {
        return "{String: \"$value\"}"
    }
}

public class JsonInteger(val value: Int) : JsonObject() {
    fun toString() : String {
        return "{Integer: $value}"
    }
}

public class JsonDouble(val value: Double): JsonObject() {
    fun toString() : String {
        return "{Double: $value}"
    }
}

public class JsonBoolean(val value: Boolean) : JsonObject() {
    fun toString() : String {
        return "{Boolean: $value}"
    }
}

public class JsonArray(value: Array<JsonObject>) : JsonObject() {
}