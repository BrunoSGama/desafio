package br.com.b2w.desafio.repositorio;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.b2w.desafio.models.Planeta;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PlanetaRepositorioTest {

	Planeta planeta1 = new Planeta();
	Planeta planeta2 = new Planeta();
	Planeta planeta3 = new Planeta();

	@Autowired
	PlanetaRepositorio repositorio;

	@Before
	public void setUp() {
		planeta1 = repositorio.save(new Planeta("Tatooine", "quente", "deserto"));
		planeta2 = repositorio.save(new Planeta("Naboo", "umido", "selvagem"));
		planeta3 = repositorio.save(new Planeta("Estrela da Morte", "negro", "metal"));
	}

	@After
	public void tearDown() {
		repositorio.delete(planeta1);
		repositorio.delete(planeta2);
		repositorio.delete(planeta3);
	}

	@Test
	public void cirarPlanetaTest() {
		Planeta planeta = repositorio.save(new Planeta("Dagobah", "desconhecido", "pantanoso"));
		Assert.assertFalse(planeta.getId().isEmpty());
		repositorio.delete(planeta);
	}

	@Test
	public void encontrarPorNomeTest() {
		List<Planeta> planeta = repositorio.findByNomeContaining("Naboo");
		Assert.assertFalse(planeta.isEmpty());
	}

	@Test
	public void listarTodosTest() {
		List<Planeta> result = repositorio.findAll();
		Assert.assertFalse(result.isEmpty());
	}

	@Test
	public void removerPlanetaTest() {
		List<Planeta> planeta = repositorio.findByNomeContaining("Morte");
		repositorio.delete(planeta.get(0));
		List<Planeta> response = repositorio.findByNomeContaining("Morte");
		Assert.assertTrue(response.isEmpty());
		Assert.assertFalse(planeta.isEmpty());
	}
}
