package br.com.b2w.desafio.services;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.com.b2w.desafio.models.Planeta;
import br.com.b2w.desafio.repositorio.PlanetaRepositorio;

public class PlanetaServiceTest {

	
	private PlanetaService servico;
	
	@Mock
	private PlanetaRepositorio repositorio;
	
	@Before
	public void setUp() {
		repositorio = Mockito.mock(PlanetaRepositorio.class); 
		servico = new PlanetaService(repositorio);

	}
	
	@Test
	public void criarPlaneta() {
		Planeta planeta = new Planeta("Bluf","frozen","tundra");
		planeta.setId("Bluf");
		when(repositorio.save(planeta)).thenReturn(planeta);
		
		Planeta planetaRetorno = servico.salvarPlaneta(planeta);
		Assert.assertEquals(planetaRetorno.getNome(), planeta.getNome());
	}
	
	@Test
	public void listarTodos() {
		Planeta planeta1 = new Planeta("Teste1","Teste", "Teste");
		Planeta planeta2 = new Planeta("Teste2","Teste", "Teste");
		Planeta planeta3 = new Planeta("Teste3","Teste", "Teste");
		Planeta planeta4 = new Planeta("Teste4","Teste", "Teste");
		List<Planeta>  planetas = new ArrayList<Planeta>();
		planetas.add(planeta1);
		planetas.add(planeta2);
		planetas.add(planeta3);
		planetas.add(planeta4);
		
		when(repositorio.findAll()).thenReturn(planetas);
		
		List<Planeta> planetasRetorno = servico.listarTodos();
		Assert.assertEquals(planetasRetorno.get(0).getNome(), planeta1.getNome());
	}
	
	@Test
	public void procurarPlanetaPorId() {
		Planeta planeta1 = new Planeta("TesteNovo","Teste", "Teste");
		Optional<Planeta> planetaOpt = Optional.of(planeta1);
		planeta1.setId("Teste");
		when(repositorio.findById(planeta1.getId())).thenReturn(planetaOpt);
		
		Planeta planetasRetorno = servico.procurarPorID(planeta1.getId());
		Assert.assertEquals(planetaOpt.get(), planetasRetorno);
	}
	
	@Test
	public void ProcurarPlanetaPorIdInexistente() {
		try {
			servico.procurarPorID("");
		}catch(Exception e) {
			Assert.assertEquals("Planeta não encontrado!", e.getMessage() );
		}
	}
	
	@Test
	public void listarPlanetaPorNome() {
		Planeta planeta1 = new Planeta("Star Destroyer 1","Teste", "Teste");
		Planeta planeta2 = new Planeta("Star Destroyer 2","Teste", "Teste");
		Planeta planeta3 = new Planeta("Star Destroyer 3","Teste", "Teste");
		Planeta planeta4 = new Planeta("Star Destroyer 4","Teste", "Teste");
		List<Planeta>  planetas = new ArrayList<Planeta>();
		planetas.add(planeta1);
		planetas.add(planeta2);
		planetas.add(planeta3);
		planetas.add(planeta4);
		
		when(repositorio.findByNomeContaining("Teste")).thenReturn(planetas);
		
		List<Planeta> planetasRetorno = servico.procurarPorNome("Teste");
		Assert.assertEquals(planetasRetorno.get(0).getNome(), planeta1.getNome());
	}
}
