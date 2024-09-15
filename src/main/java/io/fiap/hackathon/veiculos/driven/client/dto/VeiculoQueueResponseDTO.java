package io.fiap.hackathon.veiculos.driven.client.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.fge.jsonpatch.JsonPatch;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableVeiculoQueueResponseDTO.class)
@JsonDeserialize(as = ImmutableVeiculoQueueResponseDTO.class)
@Value.Immutable
@Value.Style(privateNoargConstructor = true, jdkOnly = true)
public abstract class VeiculoQueueResponseDTO {
    public abstract String getId();
    public abstract JsonPatch getPatch();
}