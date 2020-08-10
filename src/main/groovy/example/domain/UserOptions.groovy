package example.domain

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
@CompileStatic
class UserOptions implements Serializable {
    String token
    Integer maxRuns
    Map<String,Integer> other
}
