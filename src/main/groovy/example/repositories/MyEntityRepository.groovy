package example.repositories


import example.domain.MyEntity
import groovy.transform.CompileStatic
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@CompileStatic
@Repository
interface MyEntityRepository extends CrudRepository<MyEntity, Long> {
}
