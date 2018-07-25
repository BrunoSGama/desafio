package br.com.b2w.desafio.models.swapi;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonPropertyOrder({ "count", "next", "previous", "results" })
public class PlanetaSWAPISearch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6776553301298849775L;

	//@JsonInclude(JsonInclude.Include.NON_NULL)
	//@JsonProperty("results")
	private List<PlanetaSWAPI> results;
	
	//@JsonInclude(JsonInclude.Include.NON_NULL)
	//@JsonProperty("count")
	private Integer count;
	
	//@JsonInclude(JsonInclude.Include.NON_NULL)
	//@JsonProperty("next")
	private String next;
	
	//@JsonInclude(JsonInclude.Include.NON_NULL)
	//@JsonProperty("previous")
	private String previous;

	public PlanetaSWAPISearch() {
		super();
	}

	public PlanetaSWAPISearch(List<PlanetaSWAPI> results, Integer count, String next, String previous) {
		super();
		this.results = results;
		this.count = count;
		this.next = next;
		this.previous = previous;
	}

	public List<PlanetaSWAPI> getResults() {
		return results;
	}

	public void setResults(List<PlanetaSWAPI> results) {
		this.results = results;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

}
