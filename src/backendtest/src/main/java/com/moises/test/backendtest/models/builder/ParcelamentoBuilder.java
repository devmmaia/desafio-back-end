package com.moises.test.backendtest.models.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.moises.test.backendtest.models.dto.ParcelaDTO;

/**
 * Usado para construir uma lista de parcelas.
 * 
 *
 */
public class ParcelamentoBuilder {
	private List<ParcelaDTO> parcelamentos;
	public ParcelamentoBuilder(){
		this.parcelamentos = new ArrayList<ParcelaDTO>();
	}
	
	/**
	 * Adiciona uma parcela 
	 * @param valor
	 * @param taxa
	 */
	public  void add (BigDecimal valor, BigDecimal taxa){
		ParcelaDTO parcela = new ParcelaDTO(parcelamentos.size() + 1, valor.setScale(2, BigDecimal.ROUND_UP), taxa);
		this.parcelamentos.add(parcela);
	}
	
	public List<ParcelaDTO> build(){
		return this.parcelamentos;
	}
	
	/**
	 * 
	 * @param qtd - a quantidade de parcelas que devem ser adicionadas
	 * @param valor - o valor da parcela
	 * @param taxa - a taxa de juros
	 */
	public void addMany(Integer qtd, BigDecimal valor, BigDecimal taxa ){
		for (int x = 0; x < qtd; x ++){
			this.add(valor, taxa);
		}
	}
	
	
}
