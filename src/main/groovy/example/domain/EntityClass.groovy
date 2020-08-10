package example.domain

import example.util.ListToJsonConverter
import example.util.MapToJsonConverter

import javax.persistence.*
import java.util.List
import java.util.Map

@Entity
class EntityClass {

    @Id
    @GeneratedValue
    Long id

    @Convert(converter = ListToJsonConverter.class)
    List<Integer> numbers

    @Convert(converter = MapToJsonConverter.class)
    Map<String, Object> map
}
