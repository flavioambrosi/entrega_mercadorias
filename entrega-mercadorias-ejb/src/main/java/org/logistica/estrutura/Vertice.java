package org.logistica.estrutura;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	@Override
	public boolean equals(Object obj) {
	    if(obj == null){
	        return false;
	    }

	    if(obj == this){
	        return true;
	    }

	    if(!(obj instanceof Vertice)){
	        return false;
	    }

	    Vertice other = (Vertice) obj;
	    return this.id.equals(other.id) && this.descricao.equals(other.descricao);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
	    int hashCode = 1;
	    hashCode = 31 * hashCode + id.hashCode();
	    hashCode = 31 * hashCode + descricao.hashCode();
	    return hashCode;
	}
}
