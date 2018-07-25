package br.com.b2w.desafio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.b2w.desafio.models.Planeta;
import br.com.b2w.desafio.repositorio.PlanetaRepositorio;

@SpringBootApplication
public class Iniciar implements CommandLineRunner {

	@Autowired
	private PlanetaRepositorio repositorio;
	
	public static void main(String[] args) {
		SpringApplication.run(Iniciar.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Planeta planeta;
		if(repositorio.count() == 0) {
			planeta = repositorio.save(new Planeta());
			repositorio.deleteById(planeta.getId());
		}
	
	}

}
