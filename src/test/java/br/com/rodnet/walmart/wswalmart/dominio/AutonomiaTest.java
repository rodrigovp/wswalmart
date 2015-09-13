package br.com.rodnet.walmart.wswalmart.dominio;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

public class AutonomiaTest {

	private final Autonomia autonomia = new Autonomia(10);

	@Test
	public void calcularQuantidadeDeLitrosParaViagemDe0Quiiometro(){
		assertThat(autonomia.calcularQuantidadeDeLitrosParaViagemDe(0), is(equalTo(ZERO)));
	}	

	@Test
	public void calcularQuantidadeDeLitrosParaViagemDe10Quilometros(){
		assertThat(autonomia.calcularQuantidadeDeLitrosParaViagemDe(10), is(equalTo(ONE)));
	}

	@Test
	public void calcularQuantidadeDeLitrosParaViagemDe20Quilometros(){
		assertThat(autonomia.calcularQuantidadeDeLitrosParaViagemDe(20), is(equalTo(new BigDecimal("2"))));
	}
}
