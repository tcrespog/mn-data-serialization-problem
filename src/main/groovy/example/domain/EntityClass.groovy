package example.domain

import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

import example.util.ListToJsonConverter
import example.util.MapToJsonConverter
import groovy.transform.CompileStatic

@Entity
@CompileStatic
class EntityClass {

    @Id
    @GeneratedValue
    Long id

    @Convert(converter = ListToJsonConverter.class)
    List<Integer> numbers

    @Convert(converter = MapToJsonConverter.class)
    Map<String, Object> map
}
