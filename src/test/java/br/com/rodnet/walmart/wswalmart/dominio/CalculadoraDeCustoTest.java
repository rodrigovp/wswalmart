package br.com.rodnet.walmart.wswalmart.dominio;

import static java.math.BigDecimal.TEN;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;


public class CalculadoraDeCustoTest {
	
	private final CalculadoraDeCusto calculadora = new CalculadoraDeCusto();
	
	@Test
	public void calcular(){
		Autonomia autonomia = new Autonomia(10);
		Combustivel combustivel = new Combustivel(TEN);
		Rota rota = mock(Rota.class);
		when(rota.lerExtensao()).thenReturn(10);
		
		assertThat(calculadora.calcular(autonomia, rota, combustivel), is(equalTo(TEN)));
	}
}
