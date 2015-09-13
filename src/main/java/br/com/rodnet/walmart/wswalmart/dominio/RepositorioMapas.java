package br.com.rodnet.walmart.wswalmart.dominio;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioMapas extends MongoRepository<Mapa, String>{

	Mapa findByNome(String nome);

}
