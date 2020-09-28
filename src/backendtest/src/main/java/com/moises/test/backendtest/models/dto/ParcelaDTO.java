package com.moises.test.backendtest.models.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParcelaDTO {
	private Integer numeroParcela;
	private BigDecimal valor;
	private BigDecimal taxaJurosAoMes;
}
