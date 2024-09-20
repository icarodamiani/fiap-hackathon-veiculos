package io.fiap.hackathon.veiculos.driven.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDate;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableReserva.class)
@JsonDeserialize(as = ImmutableReserva.class)
@Value.Immutable
@Value.Style(privateNoargConstructor = true, jdkOnly = true)
public abstract class Reserva {
    public abstract String getId();
    public abstract String getVeiculoId();
    public abstract Pessoa getPessoa();
    public abstract String getVeiculoPlaca();
    public abstract String getVeiculoRenavam();
    public abstract LocalDate getReservadoEm();
    public abstract LocalDate getExpiraEm();
}
