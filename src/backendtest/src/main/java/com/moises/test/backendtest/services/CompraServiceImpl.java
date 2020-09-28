package com.moises.test.backendtest.services;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.moises.test.backendtest.models.builder.ParcelamentoBuilder;
import com.moises.test.backendtest.models.dto.CompraDTO;
import com.moises.test.backendtest.models.dto.ParcelaDTO;

@Controller
public class CompraServiceImpl implements CompraService {
	
	
	@Override
	public List<ParcelaDTO> comprar(CompraDTO compra) {

		Integer qtdParcelas = compra.getCondicaoPagamento().getQtdeParcelas();

		if (qtdParcelas > 6) {
			return comprarComJuros(compra);
		}

		return comprarSemJuros(compra);

	}

	private List<ParcelaDTO> comprarComJuros(CompraDTO compra) {
		ParcelamentoBuilder parcelamento = new ParcelamentoBuilder();
		BigDecimal taxa = getValorTaxa();
		BigDecimal entrada = compra.getCondicaoPagamento().getValorEntrada();
		BigDecimal valorAParcelar = compra.getProduto().getValor().subtract(entrada);
		Integer qtdParcelas = compra.getCondicaoPagamento().getQtdeParcelas();
		if (entrada.compareTo(BigDecimal.ZERO) > 0) {
			parcelamento.add(entrada, taxa);
			qtdParcelas--;
		}

		if (qtdParcelas > 0) {
			BigDecimal valorDasParcelas = calculaParcelaComJuros(valorAParcelar, qtdParcelas, taxa);
			parcelamento.addMany(qtdParcelas, valorDasParcelas, taxa);
		}

		return parcelamento.build();
	}

	private List<ParcelaDTO> comprarSemJuros(CompraDTO compra) {
		ParcelamentoBuilder parcelamento = new ParcelamentoBuilder();
		BigDecimal taxa = BigDecimal.ZERO;
		BigDecimal entrada = compra.getCondicaoPagamento().getValorEntrada();
		BigDecimal valorAParcelar = compra.getProduto().getValor().subtract(entrada);
		Integer qtdParcelas = compra.getCondicaoPagamento().getQtdeParcelas();
		if (entrada.compareTo(BigDecimal.ZERO) > 0) {
			parcelamento.add(entrada, taxa);
			qtdParcelas--;
		}

		if (qtdParcelas > 0) {
			BigDecimal valorDasParcelas = calculaParcelaSemJuros(valorAParcelar, qtdParcelas);
			parcelamento.addMany(qtdParcelas, valorDasParcelas, taxa);
		}

		return parcelamento.build();
	}

	private BigDecimal getValorTaxa() {

		return BigDecimal.valueOf(1.15 / 100);
	}

	private BigDecimal calculaParcelaComJuros(BigDecimal valor, Integer qtdParcelas, BigDecimal taxa) {
		// PMT = VALOR / (PART1 / PART2)
		
		// ((taxa + 1) ** parcelas) - 1
		BigDecimal part1 = taxa.add(new BigDecimal(1)).pow(qtdParcelas).subtract(new BigDecimal(1));
		// ((taxa + 1) ** parcelas) * taxa
		BigDecimal part2 = taxa.add(new BigDecimal(1)).pow(qtdParcelas).multiply(taxa); 

		return valor.divide(part1.divide(part2, MathContext.DECIMAL32), MathContext.DECIMAL32);
	}

	private BigDecimal calculaParcelaSemJuros(BigDecimal valor, Integer qtdParcelas) {
		return valor.divide(new BigDecimal(qtdParcelas), MathContext.DECIMAL32);
	}
}
