package example.util

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper

import javax.persistence.AttributeConverter
import java.util.List

class ListToJsonConverter implements AttributeConverter<List, String> {

    private static ObjectMapper mapper = new ObjectMapper()

    @Override
    String convertToDatabaseColumn(List list) {
        try {
            return mapper.writeValueAsString(list)
        } catch (JsonProcessingException e) {
            e.printStackTrace()
            return null
        }
    }

    @Override
    List convertToEntityAttribute(String s) {
        try {
            return mapper.readValue(s, List.class)
        } catch (JsonProcessingException e) {
            e.printStackTrace()
            return null
        }
    }

}

