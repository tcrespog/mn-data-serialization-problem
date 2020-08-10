package example.controllers

import example.domain.EntityClass
import example.domain.RegularClass
import example.repositories.EntityClassRepository
import io.micronaut.core.util.CollectionUtils
import io.micronaut.data.model.Pageable
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

import javax.inject.Inject
import java.util.Arrays

@Controller("/")
class JsonController {

    @Inject
    protected EntityClassRepository repository

    @Get("/entity/{id}")
    EntityClass getEntity(Long id) {
        EntityClass instance = repository.findById(id).orElse(null)
        return instance
    }

    @Get("/notPersistedEntity")
    EntityClass getEntity() {
        EntityClass instance = new EntityClass(numbers: [1, 2, 3], map: [string: 'hello', number: 1, boolean: true])
        return instance
    }

    @Get("/regular")
    RegularClass getRegular() {
        RegularClass instance = new RegularClass(numbers: [1, 2, 3], map: [string: 'hello', number: 1, boolean: true])

        return instance
    }

}