package br.com.b2w.desafio.clienteRest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.b2w.desafio.Iniciar;
import br.com.b2w.desafio.exception.ServicoIndisponivelException;
import br.com.b2w.desafio.models.swapi.PlanetaSWAPISearch;

@Service
public class ClienteRestSWAPI {

	private final String URL = "https://swapi.co/api/planets/";

	protected static final Logger LOGGER = LoggerFactory.getLogger(Iniciar.class);

	public ResponseEntity<PlanetaSWAPISearch> obterPlanetas() {
		try {
			RestTemplate restTemplate = new RestTemplate();
			
			return restTemplate.exchange(URL, HttpMethod.OPTIONS, criarHeader(), PlanetaSWAPISearch.class);
		
		} catch (Exception e) {
			throw new ServicoIndisponivelException("sem conexão");
		}
	}

	
	public HttpEntity<String> criarHeader() {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		headers.add("Content-Type", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		return entity;
	}
}
