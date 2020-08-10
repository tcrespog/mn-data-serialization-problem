package example.repositories

import example.domain.EntityClass
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface EntityClassRepository extends CrudRepository<EntityClass, Long> {
}