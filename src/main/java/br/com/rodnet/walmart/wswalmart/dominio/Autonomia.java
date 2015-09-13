package br.com.rodnet.walmart.wswalmart.dominio;

import java.math.BigDecimal;

public class Autonomia {
	
	private final Integer quilometrosPorLitro;

	public Autonomia(Integer quilometrosPorLitro){
		this.quilometrosPorLitro = quilometrosPorLitro;
	}

	BigDecimal calcularQuantidadeDeLitrosParaViagemDe(Integer quilometros){
		return BigDecimal.valueOf(quilometros).divide(BigDecimal.valueOf(quilometrosPorLitro));
	}
}
