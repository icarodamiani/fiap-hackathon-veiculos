package io.fiap.hackathon.veiculos.driven.domain.mapper;

import io.fiap.hackathon.veiculos.driven.domain.Pessoa;
import io.fiap.hackathon.veiculos.driven.domain.Reserva;
import io.fiap.hackathon.veiculos.driver.controller.dto.PessoaDTO;
import io.fiap.hackathon.veiculos.driver.controller.dto.ReservaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PessoaMapper extends BaseMapper<PessoaDTO, Pessoa> {
}
