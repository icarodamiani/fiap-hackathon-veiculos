package io.fiap.hackathon.veiculos.driven.client.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutablePessoaDataCleanupMessage.class)
@JsonDeserialize(as = ImmutablePessoaDataCleanupMessage.class)
@Value.Immutable
@Value.Style(privateNoargConstructor = true, jdkOnly = true)
public abstract class PessoaDataCleanupMessage {
    public abstract String getId();
    public abstract String getDocumento();
}