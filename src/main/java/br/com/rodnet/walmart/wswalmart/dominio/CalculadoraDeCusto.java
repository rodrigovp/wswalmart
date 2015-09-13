package br.com.rodnet.walmart.wswalmart.dominio;

import java.math.BigDecimal;

public class CalculadoraDeCusto {
	
	public BigDecimal calcular(Autonomia autonomia, Rota rota, Combustivel combustivel){
		Integer xQuilometros = rota.lerExtensao();
		BigDecimal xLitros = autonomia.calcularQuantidadeDeLitrosParaViagemDe(xQuilometros);;
		return combustivel.calcularValorDaCompraDe(xLitros);
	}
}
