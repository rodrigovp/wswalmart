package br.com.rodnet.walmart.wswalmart.dominio;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class MapaTest {
	
	private Mapa mapa;
	
	@Before
	public void setUp(){
		mapa = new Mapa("mapa de teste");
	}
	
	@Test
	public void adicionarUmTrechoEmMapaVazio(){
		mapa.adicionarTrecho("A", "B", 10);
	
		Rota obtido = mapa.lerCaminhoMaisCurtoEntre("A", "B");
		
		assertThat(obtido.toString(), is(equalTo("[A, B]")));
		assertThat(obtido.lerExtensao(), is(equalTo(10)));
	}
	
	@Test
	public void adicionarTresTrechosFormandoUmTrianguloEntreElesSendoQueOCaminhoMaisCurtoEhApenasUmTrecho(){
		mapa.adicionarTrecho("A", "B", 5);
		mapa.adicionarTrecho("B", "C", 6);
		mapa.adicionarTrecho("A", "C", 3);
		
		Rota obtido = mapa.lerCaminhoMaisCurtoEntre("A", "C");
		
		assertThat(obtido.toString(), is(equalTo("[A, C]")));
		assertThat(obtido.lerExtensao(), is(equalTo(3)));
	}
	
	@Test
	public void adicionar3TrechosFormandoUmTrianguloEntreElesSendoQueOCaminhoMaisCurtoPassaPorDoisTrechos(){
		mapa.adicionarTrecho("A", "B", 5);
		mapa.adicionarTrecho("B", "C", 6);
		mapa.adicionarTrecho("A", "C", 20);
		
		Rota obtido = mapa.lerCaminhoMaisCurtoEntre("A", "C");
		
		assertThat(obtido.toString(), is(equalTo("[A, B, C]")));
		assertThat(obtido.lerExtensao(), is(equalTo(11)));
	}
	
	/**
	 * Parte do exemplo existente na wikipedia (https://pt.wikipedia.org/wiki/Algoritmo_de_Dijkstra)
	 */
	@Test
	public void adicionar5TrechosFormandoUmQuadradoComUmaDiagonalEOCaminhoMaisCurtoSeraPassandoPelaDiagonal(){
		mapa.adicionarTrecho("1", "2", 7);
		mapa.adicionarTrecho("2", "3", 10);
		mapa.adicionarTrecho("3", "6", 2);
		mapa.adicionarTrecho("6", "1", 14);
		mapa.adicionarTrecho("1", "3", 9);
		
		Rota obtido = mapa.lerCaminhoMaisCurtoEntre("1", "6");
		
		assertThat(obtido.toString(), is(equalTo("[1, 3, 6]")));
		assertThat(obtido.lerExtensao(), is(equalTo(11)));
	}
	
	/**
	 * Obtido em http://www.deinf.ufma.br/~portela/ed211_Dijkstra.pdf
	 */
	@Test
	public void adicionar9TrechosComCaminhoContendoVariosDessesTrechos(){
		mapa.adicionarTrecho("A", "B", 4);
		mapa.adicionarTrecho("A", "C", 2);
		mapa.adicionarTrecho("B", "C", 1);
		mapa.adicionarTrecho("B", "D", 5);
		mapa.adicionarTrecho("C", "D", 8);
		mapa.adicionarTrecho("C", "E", 10);
		mapa.adicionarTrecho("D", "E", 2);
		mapa.adicionarTrecho("D", "F", 6);
		mapa.adicionarTrecho("E", "F", 2);
		
		Rota obtido = mapa.lerCaminhoMaisCurtoEntre("A", "F");
		
		assertThat(obtido.toString(), is(equalTo("[A, C, B, D, E, F]")));
		assertThat(obtido.lerExtensao(), is(equalTo(12)));
	}
}
