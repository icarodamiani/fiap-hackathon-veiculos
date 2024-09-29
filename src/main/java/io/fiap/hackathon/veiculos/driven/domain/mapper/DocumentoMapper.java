package io.fiap.hackathon.veiculos.driven.domain.mapper;

import io.fiap.hackathon.veiculos.driven.domain.Documento;
import io.fiap.hackathon.veiculos.driver.controller.dto.DocumentoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DocumentoMapper.class})
public interface DocumentoMapper extends BaseMapper<DocumentoDTO, Documento> {
}
