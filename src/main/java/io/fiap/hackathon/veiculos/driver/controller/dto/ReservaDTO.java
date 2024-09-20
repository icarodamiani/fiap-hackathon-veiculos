package io.fiap.hackathon.veiculos.driver.controller.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.fiap.hackathon.veiculos.driven.domain.ImmutableReserva;
import java.time.LocalDate;
import java.util.Map;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableReservaDTO.class)
@JsonDeserialize(as = ImmutableReservaDTO.class)
@Value.Immutable
@Value.Style(privateNoargConstructor = true, jdkOnly = true)
public abstract class ReservaDTO {
    public abstract String getId();
    public abstract String getVeiculoId();
    public abstract LocalDate getReservadoEm();
    public abstract LocalDate getExpiraEm();
}