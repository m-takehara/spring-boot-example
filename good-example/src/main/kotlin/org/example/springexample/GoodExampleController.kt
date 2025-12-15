package org.example.springexample

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@RestController
@RequestMapping("/good-example")
class GoodExampleController(
    private val client: WebClient,
) {
    @GetMapping
    suspend fun getSomething(): ResponseEntity<String> {
        val result = client.get()
            .uri("http://localhost:8080")
            .retrieve()
            .awaitBody<String>()
        return ResponseEntity.ok("Good Example Response: $result")
    }
}
