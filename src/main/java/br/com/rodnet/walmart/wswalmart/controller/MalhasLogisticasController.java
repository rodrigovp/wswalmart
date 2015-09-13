package br.com.rodnet.walmart.wswalmart.controller;

import static br.com.rodnet.walmart.wswalmart.controller.ResultadoREST.erroNoCarregamentoDeMapa;
import static br.com.rodnet.walmart.wswalmart.controller.ResultadoREST.sucesso;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.rodnet.walmart.wswalmart.dominio.Autonomia;
import br.com.rodnet.walmart.wswalmart.dominio.CalculadoraDeCusto;
import br.com.rodnet.walmart.wswalmart.dominio.Combustivel;
import br.com.rodnet.walmart.wswalmart.dominio.Mapa;
import br.com.rodnet.walmart.wswalmart.dominio.RepositorioMapas;
import br.com.rodnet.walmart.wswalmart.dominio.Rota;
import br.com.rodnet.walmart.wswalmart.infra.FormatoInvalidoException;
import br.com.rodnet.walmart.wswalmart.infra.LeitorDeMapas;

@RestController
public class MalhasLogisticasController {

	@Autowired
	private LeitorDeMapas leitorDeMapas;
	
	@Autowired
	private RepositorioMapas repositorioMapas;
	
	@RequestMapping(value="/carregarMalhaLogistica", method=RequestMethod.POST)
	public @ResponseBody ResultadoREST carregarMalhaLogistica(@RequestParam("mapa")MultipartFile arquivoComMalhaLogistica){
		ResultadoREST ret = sucesso();
		try {
			String conteudoDoArquivo = new String(arquivoComMalhaLogistica.getBytes());
			String nomeDoMapa = arquivoComMalhaLogistica.getOriginalFilename();
			
			Mapa novoMapa = leitorDeMapas.lerNovoMapa(nomeDoMapa, conteudoDoArquivo);
			repositorioMapas.save(novoMapa);
		} catch (IOException | FormatoInvalidoException e) {
			e.printStackTrace();
			ret = erroNoCarregamentoDeMapa();
		}
		return ret;
	}
	
	@RequestMapping(value="/buscarRotaOtimizada/mapa/{mapa}/origem/{origem}/destino/{destino}/autonomia/{autonomia}/combustivel/{combustivel}", method=RequestMethod.GET)
	public @ResponseBody ResultadoREST buscaCaminhoOtimizado(
			@PathVariable("mapa")String nomeMapa, 
			@PathVariable("origem")String nomeOrigem, 
			@PathVariable("destino")String nomeDestino, 
			@PathVariable("autonomia")Integer valorAutonomia, 
			@PathVariable("combustivel")BigDecimal valorLitroCombustivel){
		
		Mapa mapa = buscarMapaPor(nomeMapa);
		Rota rota = mapa.lerCaminhoMaisCurtoEntre(nomeOrigem, nomeDestino);
		Autonomia autonomia = new Autonomia(valorAutonomia);
		Combustivel combustivel = new Combustivel(valorLitroCombustivel);
		CalculadoraDeCusto calculadora = new CalculadoraDeCusto();
		BigDecimal custo = calculadora.calcular(autonomia, rota, combustivel);
		
		return sucesso(rota.toString(), custo);
	}
	
	private Mapa buscarMapaPor(String nomeMapa){
		return repositorioMapas.findByNome(nomeMapa) == null ? Mapa.NENHUM : repositorioMapas.findByNome(nomeMapa);
	}
}
