package org.logistica.estrutura;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Classe que representa um vertice no grafo.
 * @author Flavio
 *
 */
@Entity
@Table(name="VERTICE")
public class Vertice  {

	/**
	 * Identificador interno
	 */
	@Id
	@GeneratedValue
	private Long id;
	
	/**
	 * Descricao do vertice
	 */
	@Column(name="DESCRICAO")
	private String descricao;
	
	/**
	 * Arestas do vertice
	 */
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="FK_VERTICE_ORIGEM")
	private List<Aresta> arestas = new ArrayList<Aresta>();

	public Vertice() {

	}

	public Vertice(String descricao) {
		this.setDescricao(descricao);
	}

	public void setDescricao(String nome) {
		this.descricao = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Aresta> getArestas() {
		return arestas;
	}

	public void setArestas(List<Aresta> arestas) {
		this.arestas = arestas;
	}

	@Override
	public String toString() {
		String s = " ";
		s += this.getDescricao();
		return s;
	}

}
