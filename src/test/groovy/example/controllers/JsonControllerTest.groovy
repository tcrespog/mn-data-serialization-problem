package example.controllers

import example.domain.EntityClass
import example.domain.RegularClass
import example.repositories.EntityClassRepository
import io.micronaut.core.util.CollectionUtils
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class JsonControllerTest extends Specification {

    @Inject
    @Client("/")
    RxHttpClient client

    @Inject
    EntityClassRepository repository

    void 'test get regular class'() {
        given:
        RegularClass instance = client.retrieve(HttpRequest.GET("/regular"), RegularClass).blockingFirst()

        expect:
        instance.numbers == [1, 2, 3]
        instance.map == [string: 'hello', number: 1, boolean: true]
    }

    void 'test get entity class'() {
        given:
        EntityClass instance = new EntityClass(numbers: [1, 2, 3], map: [string: 'hello', number: 1, boolean: true])

        when:
        repository.save(instance)

        then:
        EntityClass fromDbInstance = repository.findById(instance.getId()).orElse(null)
        fromDbInstance

        when:
        EntityClass fromControllerInstance = client.retrieve(HttpRequest.GET("/entity/" + instance.getId()), EntityClass.class).blockingFirst()

        then:
        fromControllerInstance.numbers == [1, 2, 3]
        fromControllerInstance.map == [string: 'hello', number: 1, boolean: true]
    }

    void 'test get not persisted entity'() {
        given:
        EntityClass instance = client.retrieve(HttpRequest.GET("/notPersistedEntity"), EntityClass).blockingFirst()

        expect:
        instance.numbers == [1, 2, 3]
        instance.map == [string: 'hello', number: 1, boolean: true]
    }

}