package example.controllers

import javax.inject.Inject

import example.domain.MyEntity
import example.repositories.MyEntityRepository
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification
/**
 *
 * @author Paolo Di Tommaso <paolo.ditommaso@gmail.com>
 */
@MicronautTest
class MyJsonControllerTest extends Specification {


    @Inject
    @Client("/")
    RxHttpClient client

    @Inject MyEntityRepository repo

    void 'test get entity class'() {
        given:
        def instance = new MyEntity(data:['foo','bar'])

        when:
        repo.save(instance)

        then:
        def fromDbInstance = repo.findById(instance.getId()).orElse(null)
        fromDbInstance

        when:
        def fromControllerInstance = client.retrieve(HttpRequest.GET("/my/entity/" + instance.getId()), MyEntity.class).blockingFirst()

        then:
        fromControllerInstance.data == ['foo','bar']
    }

}
