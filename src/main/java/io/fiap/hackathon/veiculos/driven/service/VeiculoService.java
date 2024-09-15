package io.fiap.hackathon.veiculos.driven.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import io.fiap.hackathon.veiculos.driven.client.SqsMessageClient;
import io.fiap.hackathon.veiculos.driven.client.dto.VeiculoQueueResponseDTO;
import io.fiap.hackathon.veiculos.driven.domain.Veiculo;
import io.fiap.hackathon.veiculos.driven.repository.VeiculoRepository;
import io.vavr.CheckedFunction1;
import io.vavr.CheckedFunction2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.sqs.model.DeleteMessageResponse;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;

@Service
public class VeiculoService {
    private final String queue;
    private final VeiculoRepository repository;
    private final SqsMessageClient messageClient;
    private final ObjectMapper objectMapper;

    public VeiculoService(@Value("${aws.sqs.veiculosUpdate.queue}")
                          String queue,
                          VeiculoRepository repository,
                          SqsMessageClient messageClient,
                          ObjectMapper objectMapper) {
        this.repository = repository;
        this.messageClient = messageClient;
        this.queue = queue;
        this.objectMapper = objectMapper;
    }

    public Mono<Void> save(Veiculo pessoa) {
        return repository.save(pessoa);
    }

    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }

    public Flux<Veiculo> fetch(Boolean vendido) {
        return repository.fetch(vendido);
    }

    public Mono<Veiculo> fetchById(String id) {
        return repository.fetchById(id);
    }

    public Flux<DeleteMessageResponse> handleVeiculoUpdateEvent() {
        return messageClient.receive(queue)
            .filter(ReceiveMessageResponse::hasMessages)
            .flatMapIterable(ReceiveMessageResponse::messages)
            .flatMap(message ->
                Mono.fromSupplier(() -> {
                        try {
                            return objectMapper.readValue(message.body(), VeiculoQueueResponseDTO.class);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    }).flatMap(veiculoUpdate ->
                        this.fetchById(veiculoUpdate.getId())
                            .map(veiculo -> applyPatch().unchecked().apply(veiculoUpdate.getPatch(), veiculo))
                            .map(node -> convertToVeiculo().unchecked().apply(node))
                    )
                    .flatMap(this::save)
                    .flatMap(unused -> messageClient.delete(queue, message))
            );
    }

    private CheckedFunction2<JsonPatch, Veiculo, JsonNode> applyPatch() {
        return (patch, veiculo) -> patch.apply(objectMapper.convertValue(veiculo, JsonNode.class));
    }

    private CheckedFunction1<JsonNode, Veiculo> convertToVeiculo() {
        return node -> objectMapper.treeToValue(node, Veiculo.class);
    }
}
