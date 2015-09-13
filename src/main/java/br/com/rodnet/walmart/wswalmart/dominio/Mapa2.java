package br.com.rodnet.walmart.wswalmart.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.data.annotation.Id;

public class Mapa2 {
	
	@Id
	private String idDoMongo;
	
	private final String nome;
	private final Map<String, List<Trecho2>> vertices;
	
	public Mapa2(String nome) {
		this.nome = nome;
		this.vertices = new LinkedHashMap<String, List<Trecho2>>(); 
	}
	
	public void addVertex(String String, List<Trecho2> vertex) {
		this.vertices.put(String, vertex);
	}
	
	public List<String> lerCaminhoMaisCurtoEntre(String start, String finish) {
		Map<String, Integer> distances = new LinkedHashMap<String, Integer>();
		PriorityQueue<Trecho2> nodes = new PriorityQueue<Trecho2>();
		Map<String, Trecho2> previous = new LinkedHashMap<String, Trecho2>();
		List<String> path = new LinkedList<String>();
		
		for(String vertex : vertices.keySet()) {
			if (vertex.equals(start)) {
				distances.put(vertex, 0);
				nodes.add(new Trecho2(vertex, 0));
			} else {
				distances.put(vertex, Integer.MAX_VALUE);
				nodes.add(new Trecho2(vertex, Integer.MAX_VALUE));
			}
			previous.put(vertex, null);
		}
		
		while (!nodes.isEmpty()) {
			Trecho2 smallest = nodes.poll();
			if (smallest.getId().equals(finish)) {
				path = new LinkedList<String>();
				while (previous.get(smallest.getId()) != null) {
					path.add(smallest.getId());
					smallest = previous.get(smallest.getId());
				}
				Collections.reverse(path);
				List<String> ret = new ArrayList<String>();
				ret.add(start);
				ret.addAll(path);
				return ret;
			}

			if (distances.get(smallest.getId()).equals(Integer.MAX_VALUE)) {
				break;
			}
						
			for (Trecho2 neighbor : vertices.get(smallest.getId())) {
				Integer alt = distances.get(smallest.getId()) + neighbor.getDistance();
				if (alt < distances.get(neighbor.getId())) {
					distances.put(neighbor.getId(), alt);
					previous.put(neighbor.getId(), smallest);
					
					forloop:
					for(Trecho2 n : nodes) {
						if (n.getId().equals(neighbor.getId())) {
							n.setDistance(alt);
							break forloop;
						}
					}
				}
			}
		}
		
		return new ArrayList<String>(distances.keySet());
	}

	public void adicionarTrecho(String nomeOrigem, String nomeDestino, Integer distancia) {
		if(!vertices.containsKey(nomeOrigem)){
			vertices.put(nomeOrigem, new ArrayList<Trecho2>());
		}
		vertices.get(nomeOrigem).add(new Trecho2(nomeDestino, distancia));
		if(!vertices.containsKey(nomeDestino)){
			vertices.put(nomeDestino, new ArrayList<Trecho2>());
		}
		vertices.get(nomeDestino).add(new Trecho2(nomeOrigem, distancia));
	}

	public String lerNome() {
		return nome;
	}
	
}