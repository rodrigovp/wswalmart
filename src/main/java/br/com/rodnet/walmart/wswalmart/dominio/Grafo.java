package br.com.rodnet.walmart.wswalmart.dominio;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class Grafo implements Iterable<Vertice>{
	
	private final List<Vertice> vertices;
	
	public Grafo(List<Vertice> vertices){
		this.vertices = Collections.unmodifiableList(vertices);
	}

	public List<Vertice> getVertices() {
		return this.vertices;
	}
	
	public int quantidadeDeVertices(){
		return this.vertices.size();
	}

	//Método que retorna o vértice cuja descrição é igual à procurada.
	public Vertice encontrarVertice(String nome){
		/*Vertice ret = null;
		for(Vertice vertice : this){
			if (nome.equalsIgnoreCase(vertice.getDescricao())){
				ret = vertice;
			}
		}*/
		Optional<Vertice> ret = vertices.stream().filter(a -> a.ehChamadoDe(nome)).findFirst(); 
		return ret.get();

	}

	@Override
	public Iterator<Vertice> iterator() {
		return vertices.iterator();
	}

}
