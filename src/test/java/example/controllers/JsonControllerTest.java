package example.controllers;

import javax.inject.Inject;

import example.domain.EntityClass;
import example.domain.RegularClass;
import example.repositories.EntityClassRepository;
import io.micronaut.core.util.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;

import java.util.Arrays;

@MicronautTest
class JsonControllerTest {

    @Inject
    @Client("/")
    RxHttpClient client;

    @Inject
    EntityClassRepository repository;

    @Test
    void testGetRegular() {
        RegularClass instance = client.retrieve(HttpRequest.GET("/regular"), RegularClass.class).blockingFirst();

        Assertions.assertEquals(instance.getNumbers(), Arrays.asList(1, 2, 3));
        Assertions.assertEquals(instance.getMap(), CollectionUtils.mapOf("string", "hello", "number", 1, "boolean", true));
    }

    @Test
    void testGetEntity() {
        EntityClass instance = new EntityClass();
        instance.setNumbers(Arrays.asList(1, 2, 3));
        instance.setMap(CollectionUtils.mapOf("string", "hello", "number", 1, "boolean", true));

        repository.save(instance);

        EntityClass fromDbInstance = repository.findById(instance.getId()).orElse(null);
        Assertions.assertNotNull(fromDbInstance);

        EntityClass fromControllerInstance = client.retrieve(HttpRequest.GET("/entity/" + instance.getId()), EntityClass.class).blockingFirst();
        Assertions.assertEquals(fromControllerInstance.getNumbers(), Arrays.asList(1, 2, 3));
        Assertions.assertEquals(fromControllerInstance.getMap(), CollectionUtils.mapOf("string", "hello", "number", 1, "boolean", true));
    }

    @Test
    void testGetNotPersistedEntity() {
        EntityClass instance = client.retrieve(HttpRequest.GET("/notPersistedEntity"), EntityClass.class).blockingFirst();

        Assertions.assertEquals(instance.getNumbers(), Arrays.asList(1, 2, 3));
        Assertions.assertEquals(instance.getMap(), CollectionUtils.mapOf("string", "hello", "number", 1, "boolean", true));
    }

}