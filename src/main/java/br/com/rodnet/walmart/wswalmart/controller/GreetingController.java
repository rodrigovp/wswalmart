package br.com.rodnet.walmart.wswalmart.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.rodnet.walmart.wswalmart.dominio.Greeting;
import br.com.rodnet.walmart.wswalmart.dominio.RepositorioGreetings;

//@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    @Autowired
    private RepositorioGreetings repositorio;

    @RequestMapping(method=RequestMethod.GET, name=	"/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        Greeting ret = new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
        
        repositorio.save(ret);
        
        return ret;
    }
}