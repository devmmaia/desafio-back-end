package com.moises.test.backendtest.controllers;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.moises.test.backendtest.models.dto.TaxaSelicDTO;

@RestController
@RequestMapping("/api")
public class JurosSelicController {
	private static final String URL = "https://api.bcb.gov.br/dados/serie/bcdata.sgs.11/dados/ultimos/30?formato=json";
	@Autowired
	private RestTemplate restTemplate;

	
	
	/**
	 * 
	 * @return - soma das ultimas 30 taxas encontradas na api bcb
	 */
	@GetMapping(path = "/taxasSelic", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public BigDecimal consultaSelicAcumulada() {
		ResponseEntity<TaxaSelicDTO[]> response = restTemplate.getForEntity(URL, TaxaSelicDTO[].class);
		BigDecimal soma = Arrays.stream(response.getBody()).map(taxa -> new BigDecimal(taxa.getValor()))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		return soma;
	}
}