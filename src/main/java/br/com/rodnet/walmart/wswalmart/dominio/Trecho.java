package br.com.rodnet.walmart.wswalmart.dominio;

public class Trecho {

	private int peso;
	private Local origem;
	private Local destino;

	public Trecho(Local v1, Local v2){
		this.peso = 1;
		this.origem = v1;
		this.destino = v2;
	}

	public void setPeso(int novoPeso){
		this.peso = novoPeso;
	}

	public int getPeso(){
		return peso;
	}


	public void setDestino(Local destino) {
		this.destino = destino;
	}

	public Local getDestino() {
		return destino;
	}

	public void setOrigem(Local origem) {
		this.origem = origem;
	}

	public Local getOrigem() {
		return origem;
	}

}
