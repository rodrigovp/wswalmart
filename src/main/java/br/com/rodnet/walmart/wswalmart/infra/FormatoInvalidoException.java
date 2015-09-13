package br.com.rodnet.walmart.wswalmart.infra;

public class FormatoInvalidoException extends Exception{

	private static final long serialVersionUID = -8478862349031706536L;

	public FormatoInvalidoException(String mensagem) {
		super(mensagem);
	}

}
