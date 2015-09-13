package br.com.rodnet.walmart.wswalmart.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.data.annotation.Id;

public class Mapa {
	
	@Id
	private String idDoMongo;
	
	private final String nome;
	private final Map<String, List<Trecho>> trechos;
	
	public static final Mapa NENHUM = new Mapa(""){
		@Override
		public Rota lerCaminhoMaisCurtoEntre(String inicio, String fim){
			return Rota.NENHUMA;
		}
	};
	
	public Mapa(String nome) {
		this.nome = nome;
		this.trechos = new LinkedHashMap<String, List<Trecho>>(); 
	}
	
	public Rota lerCaminhoMaisCurtoEntre(String inicio, String fim) {
		Map<String, Integer> distances = new LinkedHashMap<String, Integer>();
		PriorityQueue<Trecho> nodes = new PriorityQueue<Trecho>();
		Map<String, Trecho> previous = new LinkedHashMap<String, Trecho>();
		List<String> path = new LinkedList<String>();
		
		for(String vertex : trechos.keySet()) {
			if (vertex.equals(inicio)) {
				distances.put(vertex, 0);
				nodes.add(new Trecho(vertex, 0));
			} else {
				distances.put(vertex, Integer.MAX_VALUE);
				nodes.add(new Trecho(vertex, Integer.MAX_VALUE));
			}
			previous.put(vertex, null);
		}
		
		while (!nodes.isEmpty()) {
			Trecho smallest = nodes.poll();
			if (smallest.getNome().equals(fim)) {
				path = new LinkedList<String>();
				while (previous.get(smallest.getNome()) != null) {
					path.add(smallest.getNome());
					smallest = previous.get(smallest.getNome());
				}
				Collections.reverse(path);
				List<String> ret = new ArrayList<String>();
				ret.add(inicio);
				ret.addAll(path);
				return montarRota(ret, distances);
			}

			if (distances.get(smallest.getNome()).equals(Integer.MAX_VALUE)) {
				break;
			}
						
			for (Trecho neighbor : trechos.get(smallest.getNome())) {
				Integer alt = distances.get(smallest.getNome()) + neighbor.getDistancia();
				if (alt < distances.get(neighbor.getNome())) {
					distances.put(neighbor.getNome(), alt);
					previous.put(neighbor.getNome(), smallest);
					
					forloop:
					for(Trecho n : nodes) {
						if (n.getNome().equals(neighbor.getNome())) {
							n.setDistancia(alt);
							break forloop;
						}
					}
				}
			}
		}
		
		return montarRota(new ArrayList<String>(distances.keySet()), distances);
	}
	
	private Rota montarRota(List<String> locais, Map<String, Integer> distancias){
		int extensao = distancias.isEmpty() ? 0 : distancias.get(locais.get(locais.size() - 1));
		return new Rota(locais, extensao);
	}

	public void adicionarTrecho(String nomeOrigem, String nomeDestino, Integer distancia) {
		if(!trechos.containsKey(nomeOrigem)){
			trechos.put(nomeOrigem, new ArrayList<Trecho>());
		}
		trechos.get(nomeOrigem).add(new Trecho(nomeDestino, distancia));
		if(!trechos.containsKey(nomeDestino)){
			trechos.put(nomeDestino, new ArrayList<Trecho>());
		}
		trechos.get(nomeDestino).add(new Trecho(nomeOrigem, distancia));
	}

	public String lerNome() {
		return nome;
	}
	
}