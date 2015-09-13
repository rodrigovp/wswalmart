package br.com.rodnet.walmart.wswalmart.dominio;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

public class CombustivelTest {
	
	private final Combustivel combustivel = new Combustivel(ONE);
	
	@Test
	public void calcularValorDaCompraDeZeroLitros(){
		assertThat(combustivel.calcularValorDaCompraDe(ZERO), is(equalTo(ZERO)));
	}
	
	@Test
	public void calcularValorDaCompraDeUmLitro(){
		assertThat(combustivel.calcularValorDaCompraDe(ONE), is(equalTo(ONE)));
	}
	
	@Test
	public void calcularValorDaCompraDeDoisLitros(){
		BigDecimal DOIS = new BigDecimal("2");
		assertThat(combustivel.calcularValorDaCompraDe(DOIS), is(equalTo(DOIS)));
	}
}
