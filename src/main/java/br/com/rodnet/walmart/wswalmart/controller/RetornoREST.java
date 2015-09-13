package br.com.rodnet.walmart.wswalmart.controller;

public enum RetornoREST {
	
	SUCESSO(0, "Upload realizado com sucesso."),
	ERRO_CARREGAMENTO_MAPA(-1, "Erro no upload de malha. Verificar se o arquivo existe ou se o mesmo está formatado corretamente.");
	
	public String descricao;
	public int codigo;
	
	private RetornoREST(int codigo, String descricao){
		this.codigo = 0;
		this.descricao = descricao;
	}
}
