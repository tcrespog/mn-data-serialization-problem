package example.domain;

import example.util.ListToJsonConverter;
import example.util.MapToJsonConverter;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
public class EntityClass {

    @Id
    @GeneratedValue
    protected Long id;

    @Convert(converter = ListToJsonConverter.class)
    protected List<Integer> numbers;

    @Convert(converter = MapToJsonConverter.class)
    protected Map<String, Object> map;


    public Long getId() {
        return id;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
