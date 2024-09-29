package io.fiap.hackathon.veiculos.driven.domain.mapper;

import io.fiap.hackathon.veiculos.driven.domain.Veiculo;
import io.fiap.hackathon.veiculos.driver.controller.dto.VeiculoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VeiculoMapper extends BaseMapper<VeiculoDTO, Veiculo> {
}
