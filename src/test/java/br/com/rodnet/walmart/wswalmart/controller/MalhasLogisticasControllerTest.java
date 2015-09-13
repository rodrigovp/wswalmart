package br.com.rodnet.walmart.wswalmart.controller;

import static br.com.rodnet.walmart.wswalmart.controller.RetornoREST.ERRO_CARREGAMENTO_MAPA;
import static br.com.rodnet.walmart.wswalmart.controller.RetornoREST.SUCESSO;
import static java.math.BigDecimal.ZERO;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import br.com.rodnet.walmart.wswalmart.Application;
import br.com.rodnet.walmart.wswalmart.dominio.Mapa;
import br.com.rodnet.walmart.wswalmart.dominio.RepositorioMapas;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class MalhasLogisticasControllerTest {
	
	private final RestTemplate restTemplate = new RestTemplate();
	private static final String URL_SISTEMA = "http://localhost:8080";
	private static final String URL_CARREGAMENTO_MALHA = URL_SISTEMA + "/carregarMalhaLogistica";
	private static final String URL_OBTENCAO_ROTA_OTIMIZADA = URL_SISTEMA + "/buscarRotaOtimizada/mapa/mapaComum/origem/A/destino/B/autonomia/10/combustivel/10";

	@Autowired
	private RepositorioMapas repositorioMapas;
	
	@After
	public void tearDown(){
		repositorioMapas.deleteAll();
	}
	
	@Test
	public void carregarMapaVazio(){
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.add("mapa", new FileSystemResource("src/test/resources/mapaVazio.txt"));
		
		ResultadoREST resultado = restTemplate.postForObject(URL_CARREGAMENTO_MALHA, parts, ResultadoREST.class);
		Mapa mapaCarregado = repositorioMapas.findByNome("mapaVazio");
		
		assertThat(resultado.getRetornoRest(), is(equalTo(SUCESSO)));
		assertNotNull(mapaCarregado);
	}
	
	@Test
	public void carregarMapaComum(){
		ResultadoREST resultado = carregarUmMapaComum();
		Mapa mapaCarregado = repositorioMapas.findByNome("mapaComum");
		
		assertThat(resultado.getRetornoRest(), is(equalTo(SUCESSO)));
		assertNotNull(mapaCarregado);
	}
	
	@Test
	public void tentarCarregarMapaInvalido(){
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.add("mapa", new FileSystemResource("src/test/resources/mapaInvalido.txt"));
		
		ResultadoREST resultado = restTemplate.postForObject(URL_CARREGAMENTO_MALHA, parts, ResultadoREST.class);
		Mapa mapaCarregado = repositorioMapas.findByNome("mapaInvalido");
		
		assertThat(resultado.getRetornoRest(), is(equalTo(ERRO_CARREGAMENTO_MAPA)));
		assertNull(mapaCarregado);
	}
	
	@Test
	public void tentarObterRotaOtimizadaSemMapasCarregados(){
		ResultadoREST resultado = restTemplate.getForObject(URL_OBTENCAO_ROTA_OTIMIZADA, ResultadoREST.class);
		
		assertThat(resultado.getRetornoRest(), is(equalTo(SUCESSO)));
		assertThat(resultado.getRota(), is(equalTo("[]")));
		assertThat(resultado.getCusto(), is(equalTo(ZERO)));
	}
	
	@Test
	public void obterRotaOtimizadaDeUmMapaCarregado(){
		carregarUmMapaComum();
		
		ResultadoREST resultado = restTemplate.getForObject(URL_OBTENCAO_ROTA_OTIMIZADA, ResultadoREST.class);
		
		assertThat(resultado.getRetornoRest(), is(equalTo(SUCESSO)));
		assertThat(resultado.getRota(), is(equalTo("[A, B]")));
		assertThat(resultado.getCusto(), is(equalTo(new BigDecimal("10"))));
	}
	
	private ResultadoREST carregarUmMapaComum(){
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.add("mapa", new FileSystemResource("src/test/resources/mapaComum"));
		
		return restTemplate.postForObject(URL_CARREGAMENTO_MALHA, parts, ResultadoREST.class);
	}
}
