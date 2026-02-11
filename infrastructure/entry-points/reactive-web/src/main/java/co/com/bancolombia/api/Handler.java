package co.com.bancolombia.api;

import lombok.RequiredArgsConstructor;

import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {
//private  final UseCase useCase;
//private  final UseCase2 useCase2;

    public Mono<ServerResponse> listenGETUseCase(ServerRequest serverRequest) {
        // useCase.logic();
        try {
            Files.write(Path.of("/app/data_good/text.txt"), "Hello world".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.badRequest().bodyValue(e.getMessage());
        }
        return ServerResponse.ok().bodyValue("");
    }

    public Mono<ServerResponse> listenGETOtherUseCase(ServerRequest serverRequest) {
        // useCase2.logic();
        return ServerResponse.ok().bodyValue("");
    }

    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
        // useCase.logic();
        return ServerResponse.ok().bodyValue("");
    }
}
