package com.moises.test.backendtest.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompraDTO {
	ProdutoDTO produto;
	CondicaoPagamentoDTO condicaoPagamento;
}
