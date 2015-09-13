package br.com.rodnet.walmart.wswalmart.dominio;

import java.util.Collections;
import java.util.List;

public class Rota {

	private final List<String> locais;
	private final int extensao;
	
	public static final Rota NENHUMA = new Rota(Collections.<String>emptyList(), 0);
	
	public Rota(List<String> locais, int extensao) {
		this.locais = locais;
		this.extensao = extensao;
	}
	
	@Override
	public String toString(){
		return locais.toString();
	}
	
	public int lerExtensao(){
		return extensao;
	}
}
