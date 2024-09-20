package io.fiap.hackathon.veiculos.driven.domain.mapper;

import io.fiap.hackathon.veiculos.driven.domain.Reserva;
import io.fiap.hackathon.veiculos.driver.controller.dto.PessoaDTO;
import io.fiap.hackathon.veiculos.driver.controller.dto.ReservaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PessoaMapper.class, DocumentoMapper.class})
public interface ReservaMapper extends BaseMapper<ReservaDTO, Reserva> {
}
