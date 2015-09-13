package br.com.rodnet.walmart.wswalmart.dominio;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioMapas extends MongoRepository<Mapa2, String>{

	Mapa2 findByNome(String nome);

}
