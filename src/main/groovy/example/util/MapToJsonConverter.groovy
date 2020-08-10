package example.util

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper

import javax.persistence.AttributeConverter
import java.util.Map

class MapToJsonConverter implements AttributeConverter<Map, String> {

    private static ObjectMapper mapper = new ObjectMapper()

    @Override
    String convertToDatabaseColumn(Map map) {
        try {
            return mapper.writeValueAsString(map)
        } catch (JsonProcessingException e) {
            e.printStackTrace()
            return null
        }
    }

    @Override
    Map convertToEntityAttribute(String s) {
        try {
            return mapper.readValue(s, Map.class)
        } catch (JsonProcessingException e) {
            e.printStackTrace()
            return null
        }
    }

}

