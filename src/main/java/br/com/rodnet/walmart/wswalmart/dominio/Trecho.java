package br.com.rodnet.walmart.wswalmart.dominio;

public class Trecho implements Comparable<Trecho> {
	
	private String nome;
	private Integer distancia;
	
	public Trecho(String nome, Integer distancia) {
		super();
		this.nome = nome;
		this.distancia = distancia;
	}

	public String getNome() {
		return nome;
	}

	public Integer getDistancia() {
		return distancia;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((distancia == null) ? 0 : distancia.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trecho other = (Trecho) obj;
		if (distancia == null) {
			if (other.distancia != null)
				return false;
		} else if (!distancia.equals(other.distancia))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vertex [id=" + nome + ", distancia=" + distancia + "]";
	}

	@Override
	public int compareTo(Trecho o) {
		return this.distancia < o.distancia ? -1 : this.distancia == o.distancia ? 0 : 1;  
	}
	
}
