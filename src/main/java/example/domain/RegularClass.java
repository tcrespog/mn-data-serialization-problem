package example.domain;

import java.util.List;
import java.util.Map;

public class RegularClass {

    protected List<Integer> numbers;

    protected Map<String, Object> map;


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
