package br.com.rodnet.walmart.wswalmart.dominio;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class Mapa implements Iterable<Local>{
	
	private final List<Local> locais;
	
	public Mapa(List<Local> locais){
		this.locais = Collections.unmodifiableList(locais);
	}

	public List<Local> getLocais() {
		return this.locais;
	}
	
	public int quantidadeDeLocais(){
		return this.locais.size();
	}

	//Método que retorna o vértice cuja descrição é igual à procurada.
	public Local encontrarLocal(String nome){
		/*Vertice ret = null;
		for(Vertice vertice : this){
			if (nome.equalsIgnoreCase(vertice.getDescricao())){
				ret = vertice;
			}
		}*/
		Optional<Local> ret = locais.stream().filter(a -> a.ehChamadoDe(nome)).findFirst(); 
		return ret.get();

	}

	@Override
	public Iterator<Local> iterator() {
		return locais.iterator();
	}

}
