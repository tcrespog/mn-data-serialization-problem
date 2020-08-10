package example

import io.micronaut.runtime.Micronaut

import javax.inject.Singleton

@Singleton
class Application {

    static void main(String[] args) {
        Micronaut.run(Application.class)
    }

}