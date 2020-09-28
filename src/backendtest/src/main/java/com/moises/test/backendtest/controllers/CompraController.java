package com.moises.test.backendtest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moises.test.backendtest.models.dto.CompraDTO;
import com.moises.test.backendtest.models.dto.ParcelaDTO;
import com.moises.test.backendtest.services.CompraService;

@RestController
@RequestMapping("/api")
public class CompraController {

	@Autowired
	private CompraService compraService;
	@PostMapping(path="/comprar")
	public List<ParcelaDTO> comprar(@RequestBody CompraDTO compra) {

		return compraService.comprar(compra);
	}

}
