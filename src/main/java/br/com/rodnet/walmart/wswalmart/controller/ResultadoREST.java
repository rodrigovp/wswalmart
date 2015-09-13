package br.com.rodnet.walmart.wswalmart.controller;

import static br.com.rodnet.walmart.wswalmart.controller.RetornoREST.ERRO_CARREGAMENTO_MAPA;
import static br.com.rodnet.walmart.wswalmart.controller.RetornoREST.SUCESSO;

import java.math.BigDecimal;

public class ResultadoREST {
	
	private final RetornoREST retornoRest;
	private String rota;
	private BigDecimal custo;
	
	public ResultadoREST(RetornoREST retornoRest){
		this.retornoRest = retornoRest;
	}
	
	public ResultadoREST(){
		this(SUCESSO);
	}
	
	public static ResultadoREST sucesso(){
		return new ResultadoREST(SUCESSO);
	}
	
	public static ResultadoREST sucesso(String rota, BigDecimal custo){
		ResultadoREST ret = sucesso();
		ret.rota = rota;
		ret.custo = custo;
		return ret;
	}
	
	public static ResultadoREST erroNoCarregamentoDeMapa(){
		return new ResultadoREST(ERRO_CARREGAMENTO_MAPA);
	}

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}

	public BigDecimal getCusto() {
		return custo;
	}

	public void setCusto(BigDecimal custo) {
		this.custo = custo;
	}

	public RetornoREST getRetornoRest() {
		return retornoRest;
	}
}
