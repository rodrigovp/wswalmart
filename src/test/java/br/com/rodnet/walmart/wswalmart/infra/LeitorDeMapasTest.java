package br.com.rodnet.walmart.wswalmart.infra;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import br.com.rodnet.walmart.wswalmart.dominio.Mapa;

public class LeitorDeMapasTest {
	
	private final LeitorDeMapas leitorDeMapas = new LeitorDeMapas();
	
	@Test(expected=FormatoInvalidoException.class)
	public void lancarExcecaoAoLerArquivoInconsistente() throws FormatoInvalidoException{
		leitorDeMapas.lerNovoMapa("mapaInvalido.txt", "conteudo invalido de arquivo de mapa");
	}
	
	@Test
	public void lerMapaSemRotas() throws FormatoInvalidoException{
		Mapa mapaVazio = leitorDeMapas.lerNovoMapa("mapaVazio.txt", "");
		
		assertThat(mapaVazio.lerCaminhoMaisCurtoEntre("A", "B").toString(), is("[]"));
		assertThat(mapaVazio.lerNome(), is(equalTo("mapaVazio")));
	}
	
	@Test
	public void lerMapaComUmaRota() throws FormatoInvalidoException{
		Mapa mapa = leitorDeMapas.lerNovoMapa("mapaComUmaRota", "A B 10");
		
		assertThat(mapa.lerCaminhoMaisCurtoEntre("A", "B").toString(), is(equalTo("[A, B]")));
		assertThat(mapa.lerNome(), is(equalTo("mapaComUmaRota")));
	}
	
	@Test
	public void lerMapaComTresRotas() throws FormatoInvalidoException{
		Mapa mapa = leitorDeMapas.lerNovoMapa("mapaComTresRotas", "A B 5\nB C 6\nA C 3");
		
		assertThat(mapa.lerCaminhoMaisCurtoEntre("A", "C").toString(), is(equalTo("[A, C]")));
	}
}
