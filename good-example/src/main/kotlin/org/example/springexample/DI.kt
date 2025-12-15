package org.example.springexample

import io.netty.channel.ChannelOption
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.resources.ConnectionProvider
import java.time.Duration

@Configuration
class WebClientConfiguration {
    @Bean
    fun webClient(): WebClient {
        // コネクションプールの設定 - bad-exampleと同じ50に制限
        val provider = ConnectionProvider.builder("custom")
            .maxConnections(500)  // 最大コネクション数（bad-exampleと同じ制限）
            .maxIdleTime(Duration.ofSeconds(30))  // アイドル時間
            .maxLifeTime(Duration.ofSeconds(60))  // 最大ライフタイム
            .pendingAcquireTimeout(Duration.ofSeconds(60))  // 接続取得タイムアウト
            .evictInBackground(Duration.ofSeconds(120))  // バックグラウンドでの削除間隔
            .build()

        val httpClient = HttpClient.create(provider)
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
            .responseTimeout(Duration.ofSeconds(60))

        return WebClient.builder()
            .baseUrl("http://localhost:8080")
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .build()
    }
}
