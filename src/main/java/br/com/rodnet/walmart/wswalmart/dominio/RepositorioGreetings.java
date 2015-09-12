package br.com.rodnet.walmart.wswalmart.dominio;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioGreetings extends MongoRepository<Greeting, String>{

}
