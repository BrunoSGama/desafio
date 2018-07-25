package br.com.b2w.desafio.controladores;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.b2w.desafio.clienteRest.ClienteRestSWAPI;
import br.com.b2w.desafio.models.Planeta;
import br.com.b2w.desafio.models.response.PlanetaSW;
import br.com.b2w.desafio.models.swapi.PlanetaSWAPI;
import br.com.b2w.desafio.services.PlanetaService;
import br.com.b2w.desafio.util.URL;

@RestController
@RequestMapping(value = "/planetas")
public class PlanetaControloador {

	@Autowired
	private ClienteRestSWAPI clienteRest;

	@Autowired
	private PlanetaService service;

	@PostMapping
	public ResponseEntity<Void> salvarPlaneta(@RequestBody Planeta planeta) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(service.salvarPlaneta(planeta).getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping
	public ResponseEntity<List<PlanetaSW>> listarTodos() {

		List<Planeta> planetas = service.listarTodos();
		List<PlanetaSW> planetasSw = new ArrayList<>();
		List<PlanetaSWAPI> planetasSwapi = clienteRest.obterPlanetas().getBody().getResults();

		planetas.stream().forEach(planeta -> {
			planetasSw.add(
					new PlanetaSW(planeta.getId(), planeta.getNome(), planeta.getClima(), planeta.getTerreno(), encontrarAparicoes(planetasSwapi, planeta)));
		});

		return ResponseEntity.ok().body(planetasSw);
	}

	@GetMapping(value = "/procurarPorID")
	public ResponseEntity<PlanetaSW> procurarPorID(@RequestParam(value = "id", defaultValue = "") String id) {
		Planeta planeta = service.procurarPorID(id);
		List<PlanetaSWAPI> planetas = clienteRest.obterPlanetas().getBody().getResults();

		return ResponseEntity.ok()
				.body(new PlanetaSW(planeta.getId(), planeta.getNome(), planeta.getClima(), planeta.getTerreno(), encontrarAparicoes(planetas, planeta)));
	}

	@GetMapping(value = "/procurarPorNome")
	public ResponseEntity<List<PlanetaSW>> procurarPorNome(
			@RequestParam(value = "nome", defaultValue = "") String nome) {
		List<PlanetaSW> resposta = new ArrayList<>();
		List<Planeta> listaPlanetas = service.procurarPorNome(URL.decodeParam(nome));
		List<PlanetaSWAPI> planetas = clienteRest.obterPlanetas().getBody().getResults();

		listaPlanetas.stream().forEach(planeta -> {
			resposta.add(
					new PlanetaSW(planeta.getId(), planeta.getNome(), planeta.getClima(), planeta.getTerreno(), encontrarAparicoes(planetas, planeta)));
		});

		return ResponseEntity.ok().body(resposta);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> removerPlaneta(@PathVariable String id) {
		service.removerPlaneta(id);
		return ResponseEntity.noContent().build();
	}

	private int encontrarAparicoes(List<PlanetaSWAPI> planetas, Planeta planeta) {
		if (planetas == null) {
			return 0;
		}
		return planetas.stream().filter(p -> p.getName().equalsIgnoreCase(planeta.getNome())).findFirst().get()
				.getAparicoes();
	}
}
