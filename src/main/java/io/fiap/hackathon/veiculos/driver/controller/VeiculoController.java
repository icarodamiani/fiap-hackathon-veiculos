package io.fiap.hackathon.veiculos.driver.controller;

import io.fiap.hackathon.veiculos.driven.domain.mapper.VeiculoMapper;
import io.fiap.hackathon.veiculos.driven.service.VeiculoService;
import io.fiap.hackathon.veiculos.driver.controller.dto.VeiculoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SecurityRequirement(name = "OAuth2")
@RestController
@RequestMapping(value = "/veiculos", produces = MediaType.APPLICATION_JSON_VALUE)
public class VeiculoController {

    private final VeiculoService pessoaService;
    private final VeiculoMapper veiculoMapper;

    public VeiculoController(VeiculoService pessoaService, VeiculoMapper veiculoMapper) {
        this.pessoaService = pessoaService;
        this.veiculoMapper = veiculoMapper;
    }


    @PostMapping
    @Operation(description = "Cria uma nova veículo")
    public Mono<Void> save(@RequestBody VeiculoDTO pessoa) {
        return Mono.fromSupplier(() -> veiculoMapper.domainFromDto(pessoa))
            .flatMap(pessoaService::save);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Deleta uma veículo por seu ID")
    public Mono<Void> deleteById(@PathVariable String id) {
        return pessoaService.deleteById(id);
    }

    @GetMapping
    @Operation(description = "Busca veículos vendidos ou disponíveis")
    public Flux<VeiculoDTO> fetch(@RequestParam("vendido") Boolean vendido) {
        return pessoaService.fetch(vendido)
            .map(veiculoMapper::dtoFromDomain);
    }

    @GetMapping("/{id}")
    @Operation(description = "Busca um veículo por seu ID")
    public Mono<VeiculoDTO> fetchById(@PathVariable String id) {
        return pessoaService.fetchById(id)
            .map(veiculoMapper::dtoFromDomain);
    }
}
