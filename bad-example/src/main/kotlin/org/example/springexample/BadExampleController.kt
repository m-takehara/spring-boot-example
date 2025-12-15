package org.example.springexample

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClient
import org.springframework.web.client.body

@RestController
@RequestMapping("/bad-example")
class BadExampleController(
    private val client: RestClient
) {

    @GetMapping
    fun getSomething(): ResponseEntity<String> {
        val result = client.get().uri("/").retrieve().body<String>()
        return ResponseEntity.ok(result)
    }

}
