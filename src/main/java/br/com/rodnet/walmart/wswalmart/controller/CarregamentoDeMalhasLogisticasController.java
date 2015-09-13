package br.com.rodnet.walmart.wswalmart.controller;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.rodnet.walmart.wswalmart.dominio.Greeting;
import br.com.rodnet.walmart.wswalmart.dominio.Mapa2;
import br.com.rodnet.walmart.wswalmart.dominio.RepositorioMapas;
import br.com.rodnet.walmart.wswalmart.infra.FormatoInvalidoException;
import br.com.rodnet.walmart.wswalmart.infra.LeitorDeMapas;

@RestController
public class CarregamentoDeMalhasLogisticasController {

	@Autowired
	private LeitorDeMapas leitorDeMapas;
	
	@Autowired
	private RepositorioMapas repositorioMapas;
	
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping(value="/carregarMalhaLogistica", method=RequestMethod.POST)
	public @ResponseBody ResultadoREST carregarMalhaLogistica(@RequestParam("mapa")MultipartFile arquivoComMalhaLogistica){
		try {
			String conteudoDoArquivo = new String(arquivoComMalhaLogistica.getBytes());
			String nomeDoMapa = arquivoComMalhaLogistica.getOriginalFilename();
			
			System.out.println("----------------------------------");
			System.out.println("Nome do mapa: " + nomeDoMapa);
			System.out.println("Conteudo do mapa: " + conteudoDoArquivo);
			System.out.println("----------------------------------");
			
			Mapa2 novoMapa = leitorDeMapas.lerNovoMapa(nomeDoMapa, conteudoDoArquivo);
			repositorioMapas.save(novoMapa);
		} catch (IOException | FormatoInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultadoREST.SUCESSO;
	}
	
	@RequestMapping(method=RequestMethod.GET, name=	"/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        Greeting ret = new Greeting(counter.incrementAndGet(),
                            String.format("bla", name));
        
        //repositorio.save(ret);
        
        return ret;
    }
}
