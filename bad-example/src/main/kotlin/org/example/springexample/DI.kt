package org.example.springexample

import org.apache.hc.client5.http.impl.classic.HttpClients
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestClient

@Configuration
class RestClientConfiguration {
    @Bean
    fun restClient() = RestClient.builder()
        .requestFactory(createRequestFactory())
        .baseUrl("http://localhost:8080")
        .build()!!
}

fun createConnectionManager() = PoolingHttpClientConnectionManager().apply {
    maxTotal = 500
    defaultMaxPerRoute = 500
}
fun createHttpClients() = HttpClients.custom().setConnectionManager(createConnectionManager()).build()!!
fun createRequestFactory() = HttpComponentsClientHttpRequestFactory(createHttpClients())
