package example.controllers

import javax.inject.Inject

import example.domain.MyEntity
import example.domain.UserOptions
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
        def opts = new UserOptions(token: 'xyz', maxRuns: 1, other: [foo:1, baf:2])
        def params = new HashMap(x:1, y:2)
        def instance = new MyEntity(data:['foo','bar'], options: opts, params: params)

        when:
        repo.save(instance)

        then:
        def fromDbInstance = repo.findById(instance.getId()).orElse(null)
        fromDbInstance

        when:
        def fromControllerInstance = client.retrieve(HttpRequest.GET("/my/entity/" + instance.getId()), MyEntity.class).blockingFirst()

        then:
        fromControllerInstance.data == ['foo','bar']
        fromControllerInstance.options == opts
        fromControllerInstance.params == params
    }

}
