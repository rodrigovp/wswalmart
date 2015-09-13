package br.com.rodnet.walmart.wswalmart.dominio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Local implements Comparable<Local>, Iterable<Trecho>{

	private String descricao;
	private int distancia;
	private boolean visitado = false;
	private Local pai;
	private final List<Trecho> arestas = new ArrayList<Trecho>();
	private final List<Local> vizinhos = new ArrayList<Local>();

	public void setDescricao(String nome){

		this.descricao = nome;
	}
	
	public boolean ehChamadoDe(String nome){
		return descricao.equalsIgnoreCase(nome);
	}

	public String getDescricao(){

		return descricao;

	}

	public void visitar (){

		this.visitado = true;
	}

	public boolean verificarVisita(){

		return visitado;
	}

	public void setDistancia(int distancia){

		this.distancia = distancia;
	}

	public int getDistancia(){

		return this.distancia;
	}

	public void setPai(Local pai){

		this.pai = pai;
	}

	public Local getPai(){

		return this.pai;
	}

	public void setVizinhos(List<Local> vizinhos) {

		this.vizinhos.addAll(vizinhos);

	}

	public List<Local> getVizinhos(){

		return this.vizinhos;
	}

	public void setArestas(List <Trecho> arestas){

		this.arestas.addAll(arestas);

	}

	public List<Trecho> getArestas() {

		return arestas;
	}

	@Override
	public int compareTo(Local vertice) {
		if(this.getDistancia() < vertice.getDistancia()) return -1;
		else if(this.getDistancia() == vertice.getDistancia()) return 0;

		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Local){
			Local vRef = (Local) obj;
			if(this.getDescricao().equals(vRef.getDescricao())) return true;
		}
		return false;
	}

	@Override
	public String toString() {
		String s = " ";
		s+= this.getDescricao();
		return s;
	}

	@Override
	public Iterator<Trecho> iterator() {
		return arestas.iterator();
	}
}
