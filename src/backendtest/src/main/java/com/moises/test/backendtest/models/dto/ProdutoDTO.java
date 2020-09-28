package com.moises.test.backendtest.models.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProdutoDTO {
	private Integer codigo;
	private String nome;
	private BigDecimal valor;
}
