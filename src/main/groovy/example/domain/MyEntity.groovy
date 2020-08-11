package example.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

import groovy.transform.CompileStatic
import org.hibernate.annotations.Type

/**
 *
 * @author Paolo Di Tommaso <paolo.ditommaso@gmail.com>
 */
@Entity
@CompileStatic
class MyEntity {

    @Id
    @GeneratedValue
    Long id


    @Type(type = 'example.util.H8ListToJsonType')
    List<String> data

    @Type(type = 'example.util.H8UserOptionsType')
    UserOptions options

    @Type(type = 'example.util.H8MapToJsonType')
    Map params

}
