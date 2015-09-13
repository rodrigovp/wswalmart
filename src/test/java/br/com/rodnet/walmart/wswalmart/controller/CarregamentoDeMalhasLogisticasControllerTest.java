package br.com.rodnet.walmart.wswalmart.controller;

import static br.com.rodnet.walmart.wswalmart.controller.ResultadoREST.SUCESSO;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

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
import br.com.rodnet.walmart.wswalmart.dominio.Mapa2;
import br.com.rodnet.walmart.wswalmart.dominio.RepositorioMapas;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class CarregamentoDeMalhasLogisticasControllerTest {
	
	private final RestTemplate restTemplate = new RestTemplate();
	private static final String URL_CARREGAMENTO_MALHA = "http://localhost:8080/carregarMalhaLogistica";
	
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
		Mapa2 mapaCarregado = repositorioMapas.findByNome("mapaVazio");
		
		assertThat(resultado, is(equalTo(SUCESSO)));
		assertNotNull(mapaCarregado);
	}
	
	@Test
	public void carregarMapaComum(){
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.add("mapa", new FileSystemResource("src/test/resources/mapa comum"));
		
		ResultadoREST resultado = restTemplate.postForObject(URL_CARREGAMENTO_MALHA, parts, ResultadoREST.class);
		Mapa2 mapaCarregado = repositorioMapas.findByNome("mapa comum");
		
		assertThat(resultado, is(equalTo(SUCESSO)));
		assertNotNull(mapaCarregado);
	}
}
