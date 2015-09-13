package br.com.rodnet.walmart.wswalmart.dominio;

import java.math.BigDecimal;

public class Combustivel {
	
	private final BigDecimal preco;
	
	public Combustivel(BigDecimal preco){
		this.preco = preco;
	}
	
	public BigDecimal calcularValorDaCompraDe(BigDecimal quantidadeDeLitros){
		return preco.multiply(quantidadeDeLitros);
	}
}
