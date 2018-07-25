package br.com.b2w.desafio.repositorio;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.b2w.desafio.models.Planeta;

@Repository
public interface PlanetaRepositorio extends MongoRepository<Planeta, String> {

	List<Planeta> findByNomeContaining(String nome);
	
}
