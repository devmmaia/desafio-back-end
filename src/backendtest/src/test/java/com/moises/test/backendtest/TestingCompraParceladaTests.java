package com.moises.test.backendtest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moises.test.backendtest.models.dto.ParcelaDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class TestingCompraParceladaTests {

	@Autowired
	private MockMvc mock;

	@Test
	public void testTaxaSelicAcumuladoEndpoint() throws Exception {
		mock.perform(get("/api/taxasSelic")).andExpect(status().isOk());
	}
	
	@Test
	public void testParcelamentoComJuros() throws Exception{
		String body=  "{\"produto\": { \"codigo\": 123, \"nome\": \"Nome do Produto\", \"valor\": 30000 }, "
				+ "\"condicaoPagamento\": { \"valorEntrada\": 0, \"qtdeParcelas\": 15 } }";
		
		MvcResult result = mock.perform(post("/api/comprar")
				.contentType(MediaType.APPLICATION_JSON).content(body)).andReturn();
		ObjectMapper mapper = new ObjectMapper();
		String jsonresponse = result.getResponse().getContentAsString();
		List<ParcelaDTO> parcelas = mapper.readValue(jsonresponse, new TypeReference<List<ParcelaDTO>>() {});
		assertTrue(parcelas.size() == 15);
		
	}
}
