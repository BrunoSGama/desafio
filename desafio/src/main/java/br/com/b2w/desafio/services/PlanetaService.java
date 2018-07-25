package br.com.b2w.desafio.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.b2w.desafio.exception.NotFoundException;
import br.com.b2w.desafio.exception.RequisicaoInvalidaException;
import br.com.b2w.desafio.models.Planeta;
import br.com.b2w.desafio.repositorio.PlanetaRepositorio;

@Service
public class PlanetaService implements PlanetaServiceLocal {

	private PlanetaRepositorio repositorio;

	@Autowired
	public PlanetaService(PlanetaRepositorio repositorio) {
		this.repositorio = repositorio;
	}

	public Planeta salvarPlaneta(Planeta planeta) {
		validarPlaneta(planeta);
		return repositorio.save(planeta);
	}

	public List<Planeta> listarTodos() {
		return repositorio.findAll();
	}

	public Planeta procurarPorID(String id) {
		Optional<Planeta> obj = repositorio.findById(id);
		return obj.orElseThrow(() -> new NotFoundException("Planeta não encontrado!"));
	}

	public List<Planeta> procurarPorNome(String nome) {
		return repositorio.findByNomeContaining(nome);
	}

	public void removerPlaneta(String id) {
		repositorio.delete(procurarPorID(id));
	}

	public Planeta geraId(Planeta planeta) {
		Planeta id = repositorio.save(new Planeta());
		planeta.setId(id.getId());
		return planeta;
	}

	public Planeta validarPlaneta(Planeta obj) {
		try {
			if (obj.getNome().isEmpty() || obj.getNome().equals(null)) {
				throw new RequisicaoInvalidaException("Campo nome não pode estar vazio.");
			}
			if (obj.getClima().isEmpty() || obj.getClima().equals(null)) {
				throw new RequisicaoInvalidaException("Campo clima não pode estar vazio.");
			}
			if (obj.getTerreno().isEmpty() || obj.getTerreno().equals(null)) {
				throw new RequisicaoInvalidaException("Campo terreno não pode estar vazio.");
			}
		} catch (Exception e) {
			throw new RequisicaoInvalidaException("ERROR: Planeta Invalido!");
		}
		return obj;

	}
}
