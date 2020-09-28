package com.moises.test.backendtest.services;

import java.math.BigDecimal;
import java.util.List;

import com.moises.test.backendtest.models.dto.CompraDTO;
import com.moises.test.backendtest.models.dto.ParcelaDTO;

public interface CompraService {

	public List<ParcelaDTO> comprar(CompraDTO compra);

	
}
