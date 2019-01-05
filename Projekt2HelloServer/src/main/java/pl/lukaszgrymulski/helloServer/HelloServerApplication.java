package pl.lukaszgrymulski.helloServer;

import reactor.core.publisher.Flux;
import reactor.netty.http.server.HttpServer;
import reactor.netty.http.server.HttpServerRoutes;

import java.time.Duration;

public class HelloServerApplication {

	public static void main(String[] args) {
		//tworzenie serwera


        HttpServer.create()
                .host("localhost")
                .port(8080)
                .handle((req, res) -> res.sendObject(Flux.just("hello")))
                .bind()
                .block(Duration.ofMinutes(60));
    }
}
