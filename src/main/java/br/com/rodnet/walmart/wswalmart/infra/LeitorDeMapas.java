package br.com.rodnet.walmart.wswalmart.infra;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.rodnet.walmart.wswalmart.dominio.Mapa;

@Component
public class LeitorDeMapas {

	private static final String PULO_DE_LINHA = "\n";
	private static final String ESPACO = " ";
	private static final String PONTO = ".";
	
	public Mapa lerNovoMapa(String nomeDoMapa, String conteudoDoArquivo) throws FormatoInvalidoException {
		List<String> linhasDoArquivo = Arrays.asList(conteudoDoArquivo.split(PULO_DE_LINHA));
		Mapa ret = new Mapa(calcularNomeDoMapa(nomeDoMapa));
		
		if(!conteudoDoArquivo.isEmpty()){
			for(String linhaDoArquivo : linhasDoArquivo){
				List<String> camposDaLinha = Collections.emptyList();
				if(ehValida(linhaDoArquivo)){
					camposDaLinha = Arrays.asList(linhaDoArquivo.split(ESPACO));
					String nomeInicio = camposDaLinha.get(0);
					String nomeFim = camposDaLinha.get(1);
					int distancia = Integer.parseInt(camposDaLinha.get(2));
					ret.adicionarTrecho(nomeInicio, nomeFim, distancia);
				}
				else{
					throw new FormatoInvalidoException("Uma das linhas do arquivo é inválida");
				}
				
			}			
		}
		return ret;
	}
	
	private String calcularNomeDoMapa(String nomeArquivo){
		String ret = nomeArquivo.contains(PONTO) ? nomeArquivo.substring(0, nomeArquivo.lastIndexOf(".")) : nomeArquivo;
		return ret;
	}

	private boolean ehValida(String linhaDoArquivo) {
		String[] camposDaLinha = linhaDoArquivo.split(ESPACO);
		return camposDaLinha.length == 3
				&& ehString(camposDaLinha[0].trim())
				&& ehString(camposDaLinha[1].trim())
				&& ehNumero(camposDaLinha[2].trim());
	}
	
	private boolean ehString(String s){
		return !ehNumero(s);
	}
	
	private boolean ehNumero(String s){
		boolean ret = true;
		try{
			Integer.parseInt(s);
		}catch(NumberFormatException e){
			ret = false;
		}
		return ret;
	}
}
