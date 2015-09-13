package br.com.rodnet.walmart.wswalmart.controller;

public enum ResultadoREST {
	
	SUCESSO(0, "Upload realizado com sucesso."),
	ERRO(-1, "Erro no upload de malha. Verificar se o arquivo existe ou se o mesmo está formatado corretamente.");
	
	private String descricao;
	private int codigo;
	
	private ResultadoREST(int codigo, String descricao){
		this.codigo = 0;
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	
}
