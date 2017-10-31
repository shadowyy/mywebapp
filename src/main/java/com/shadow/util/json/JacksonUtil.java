package com.shadow.util.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

/**
 * @author yuyue
 * @version 2017-10-31 0031 16:49
 */
public class JacksonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        // =========================================================================
        // SerializationFeature for changing how JSON is written

        // to enable standard indentation ("pretty-printing"):
        MAPPER.enable(SerializationFeature.INDENT_OUTPUT);

        // to allow serialization of "empty" POJOs (no properties to serialize)
        // (without this setting, an exception is thrown in those cases)
        MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        // to write java.util.Date, Calendar as number (timestamp):
        MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // DeserializationFeature for changing how JSON is read as POJOs:
        // to prevent exception when encountering unknown property:
        MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        // to allow coercion of JSON empty String ("") to null Object value:
        MAPPER.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        // =========================================================================
        // JsonParser.Feature for configuring parsing settings:

        // to allow C/C++ style comments in JSON (non-standard, disabled by default)
        // (note: with Jackson 2.5, there is also `MAPPER.enable(feature)` / `MAPPER.disable(feature)`)
        MAPPER.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        // to allow (non-standard) unquoted field names in JSON:
        MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // to allow use of apostrophes (single quotes), non standard
        MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

        // JsonGenerator.Feature for configuring low-level JSON generation:

        // to force escaping of non-ASCII characters:
        MAPPER.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
    }

    public static String toJson( Object obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T fromJson( String json, Class<T> clazz) {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // return MAPPER.readValue(json, new TypeReference<List<T>>() {
    //});


}
