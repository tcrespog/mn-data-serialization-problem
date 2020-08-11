package example.controllers

import javax.inject.Inject

import example.domain.EntityClass
import example.domain.MyEntity
import example.domain.RegularClass
import example.repositories.EntityClassRepository
import example.repositories.MyEntityRepository
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/")
class JsonController {

    @Inject
    protected EntityClassRepository repository

    @Inject
    protected MyEntityRepository myRepo

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


    @Get("/my/entity/{id}")
    MyEntity getMyEntity(Long id) {
        myRepo.findById(id).orElse(null)
    }

}
