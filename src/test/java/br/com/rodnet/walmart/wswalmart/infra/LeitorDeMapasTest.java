package br.com.rodnet.walmart.wswalmart.infra;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;

import br.com.rodnet.walmart.wswalmart.dominio.Mapa2;

public class LeitorDeMapasTest {
	
	private final LeitorDeMapas leitorDeMapas = new LeitorDeMapas();
	
	@Test(expected=FormatoInvalidoException.class)
	public void lancarExcecaoAoLerArquivoInconsistente() throws FormatoInvalidoException{
		leitorDeMapas.lerNovoMapa("mapaInvalido.txt", "conteudo invalido de arquivo de mapa");
	}
	
	@Test
	public void lerMapaSemRotas() throws FormatoInvalidoException{
		Mapa2 mapaVazio = leitorDeMapas.lerNovoMapa("mapa vazio.txt", "");
		
		assertThat(mapaVazio.lerCaminhoMaisCurtoEntre("A", "B"), is(empty()));
		assertThat(mapaVazio.lerNome(), is(equalTo("mapa vazio")));
	}
	
	@Test
	public void lerMapaComUmaRota() throws FormatoInvalidoException{
		Mapa2 mapa = leitorDeMapas.lerNovoMapa("mapa com uma rota", "A B 10");
		
		assertThat(mapa.lerCaminhoMaisCurtoEntre("A", "B"), is(equalTo(Arrays.asList("A", "B"))));
		assertThat(mapa.lerNome(), is(equalTo("mapa com uma rota")));
	}
	
	@Test
	public void lerMapaComTresRotas() throws FormatoInvalidoException{
		Mapa2 mapa = leitorDeMapas.lerNovoMapa("mapa com tres rotas", "A B 5\nB C 6\nA C 3");
		
		assertThat(mapa.lerCaminhoMaisCurtoEntre("A", "C"), is(equalTo(Arrays.asList("A", "C"))));
	}
}
