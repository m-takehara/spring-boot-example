# Spring Boot Example

spring-boot-starter-web (bad-example) と、spring-boot-starter-webflux (good-example) の比較をするためのリポジトリ。

前者は Tomcat が使える最大スレッド数を [application.properties](./bad-example/src/main/resources/application.properties) で設定し、  
後者は Netty が使えるワーカースレッド数を起動時コマンド (下記) で設定し、  
なんかそれっぽく「同時に50並列しか受け付けられない」みたいな状況になるようにした。

bad-example は100並列でリクエストを送っても50並列までしか処理能力が無いが、  
good-example は100並列でリクエストを送ると100並列で処理をしてレスポンスをする処理能力がある。

※ WireMock のレスポンス能力の限界に直面したため、WireMock 自体が高いスレッド数で動けるように設定してある  
※ 上記に加え、それぞれ使っている HTTP Client も、最大コネクション数などを気にしないでいいようにしてある

## common - run wiremock

```bash
cd environments
skaffold dev --port-forward
```

## run bad-example

```bash
cd bad-example
./mvnw clean spring-boot:run
```

```bash
cd ../
./run-bad-example.sh
```

## run good-example

```bash
cd good-example
./mvnw clean spring-boot:run -Dspring-boot.run.jvmArguments="-Dreactor.netty.ioWorkerCount=50"
```

```bash
cd ../
./run-good-example.sh
```
