package br.com.b2w.desafio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import br.com.b2w.desafio.models.Planeta;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ChamadasRestTests {

	
	protected final String BASE_PATH = "http://localhost:";

	@LocalServerPort
	private int port;
	
	RestTemplate rest;
		
	@Before
	public void setUp() {
		rest = new RestTemplate();
	}
	
	@Test
	public void criarPlaneta() {
		
		Planeta planeta = new Planeta("Tatooine","Quente","Deserto");
		ResponseEntity<String> response = rest.postForEntity(BASE_PATH + port +"/planetas/",planeta,String.class);
		Assert.assertEquals(201, response.getStatusCodeValue());
		rest.delete(response.getHeaders().getLocation());

	}
	
	@Test
	public void criarPlanetaNomeVazio() {
		Planeta planeta = new Planeta("","Quente","Deserto");
		try {
			rest.postForEntity(BASE_PATH + port +"/planetas/",planeta,String.class);
		}catch (Exception e) {
			 Assert.assertEquals("500 null", e.getMessage());
		}
	}
	@Test
	public void criarPlanetaClimaVazio() {
			Planeta planeta = new Planeta("Estrela da Morte","","metal");
		try {
			rest.postForEntity(BASE_PATH + port +"/planetas/",planeta,String.class);
		}catch (Exception e) {
			 Assert.assertEquals("500 null", e.getMessage());
		}
	}
	
	@Test
	public void criarPlanetaTerrenoVazio() {
		Planeta planeta = new Planeta("Estrela da morte","negro","");
		try {
			rest.postForEntity(BASE_PATH + port +"/planetas/",planeta,String.class);
		}catch (Exception e) {
			 Assert.assertEquals("500 null", e.getMessage());
		}
	}
	
	@Test
	public void criarPlanetaNomeNulo() {
		Planeta planeta = new Planeta(null, "Quente", "Deserto");
		try {
			rest.postForEntity(BASE_PATH + port +"/planetas/",planeta,String.class);
		}catch (Exception e) {
			 Assert.assertEquals("500 null", e.getMessage());
		}
	}
	
	@Test
	public void criarPlanetaClimaNulo() {
		Planeta planeta = new Planeta("Tatooine", null, "Deserto");
		try {
			rest.postForEntity(BASE_PATH + port +"/planetas/",planeta,String.class);
		}catch (Exception e) {
			 Assert.assertEquals("500 null", e.getMessage());
		}
	}
	
	@Test
	public void criarPlanetaTerrenoNulo() {
		Planeta planeta = new Planeta("Tatooine", "Quente", null);
		try {
			rest.postForEntity(BASE_PATH + port +"/planetas/",planeta,String.class);
		}catch (Exception e) {
			 Assert.assertEquals("500 null", e.getMessage());
		}
	}
		
	@Test
	public void buscarPorId() {
		Planeta planeta = new Planeta("Naboo","Umido", "Selvagem");
		ResponseEntity<String>  response = rest.postForEntity(BASE_PATH + port +"/planetas/", planeta,String.class);

		ResponseEntity<String>  respostaBusca = rest.getForEntity(BASE_PATH + port +"/planetas/procurarPorID?id=5b469ce533cf19690829d61a", String.class);
		Assert.assertEquals(200, respostaBusca.getStatusCodeValue());
		
		rest.delete(response.getHeaders().getLocation());
	}
	
	@Test
	public void buscarPorIdNaoExiste() {
		try {
			rest.getForEntity(BASE_PATH + port +"/planetas/procurarPorID?id=5b469ce533cf19690829d61", String.class);
		}catch(Exception e) {
			Assert.assertEquals("500 null", e.getMessage());
		}
	}
	
	@Test
	public void procurarPorNome() {
		Planeta planeta = new Planeta("Naboo","Umido", "Selvagem");
		
		ResponseEntity<String>  response = rest.postForEntity(BASE_PATH + port +"/planetas/",planeta,String.class);
		
		ResponseEntity<String>  respostaBusca = rest.getForEntity(BASE_PATH + port +"/planetas/procurarPorNome?nome=Tatooine", String.class);
		Assert.assertEquals(200, respostaBusca.getStatusCodeValue());
		
		rest.delete(response.getHeaders().getLocation());

	}
	
	@Test
	public void procurarPorNomeSeNaoExiste() {
		ResponseEntity<String> response = rest.getForEntity(BASE_PATH + port +"/planetas/procurarPorNome?nome=Naboo", String.class);
		Assert.assertEquals(200, response.getStatusCodeValue());
	}
	
	@Test
	public void listarTodos() {
		
		ResponseEntity<String> responseBusca = rest.getForEntity(BASE_PATH + port +"/planetas/", String.class);
		Assert.assertEquals(200, responseBusca.getStatusCodeValue());
		
	}
	
	@Test
	public void removerPlaneta() {
        Planeta planeta = new Planeta("Tatooine","Quente", "deserto");
		ResponseEntity<String>  response = rest.postForEntity(BASE_PATH + port +"/planetas/",planeta,String.class);

		ResponseEntity<String> respostaBusca  = rest.exchange(response.getHeaders().getLocation().toString(), HttpMethod.DELETE, criaHeader() , String.class,planeta);
		Assert.assertEquals(204, respostaBusca.getStatusCodeValue());
	}
	
	@Test
	public void removerPlanetaPorId() {
        try {
			rest.exchange(BASE_PATH + port +"/planetas/"+ "Teste", HttpMethod.DELETE, criaHeader() , String.class);
        }catch(Exception e) {
			Assert.assertEquals("500 null", e.getMessage());
        }
	}
	
	private HttpEntity<String> criaHeader() {
	    HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		return entity;
	}
}
