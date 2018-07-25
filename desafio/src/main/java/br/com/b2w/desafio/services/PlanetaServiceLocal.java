package br.com.b2w.desafio.services;

import java.util.List;

import javax.ejb.Local;

import br.com.b2w.desafio.models.Planeta;

@Local
public interface PlanetaServiceLocal {
	
	public Planeta salvarPlaneta(Planeta planeta);
	
	public List<Planeta> listarTodos();
	
	public Planeta procurarPorID(String id);
	
	public List<Planeta> procurarPorNome(String nome);
	
	public void removerPlaneta(String id);

}
