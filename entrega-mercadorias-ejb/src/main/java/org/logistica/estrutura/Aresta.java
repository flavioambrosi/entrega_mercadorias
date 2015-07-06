package org.logistica.estrutura;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Classe que representa uma aresta do grafo.
 * @author Flavio
 *
 */
@Entity
@Table(name="ARESTA")
public class Aresta {

	/**
	 * Identificador interno.
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * Distancia entre os vertices.
	 */
	@Column(name="DISTANCIA")
	private int distancia;

	/**
	 * Vertice de origem da aresta
	 */
	@OneToOne(optional = false, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name="FK_VERTICE_ORIGEM")
	private Vertice origem;

	/**
	 * Vertice de destino da aresta.
	 */

	@OneToOne( optional = false, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name="FK_VERTICE_DESTINO")
	private Vertice destino;


    public Aresta() {
    }

	public Aresta(Vertice v1, Vertice v2) {
		this.distancia = 1;
		this.origem = v1;
		this.destino = v2;
	}


	public void setDistancia(int novoPeso) {
		this.distancia = novoPeso;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDestino(Vertice destino) {
		this.destino = destino;
	}

	public Vertice getDestino() {
		return destino;
	}

	public void setOrigem(Vertice origem) {
		this.origem = origem;
	}

	public Vertice getOrigem() {
		return origem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(origem.getDescricao()+destino.getDescricao());
		buffer.append(" ").append(this.distancia);
		return buffer.toString();
	}

	@Override
	public boolean equals(Object obj) {
	    if(obj == null){
            return false;
        }

        if(obj == this){
            return true;
        }

        if(!(obj instanceof Aresta)){
            return false;
        }

        Aresta other = (Aresta) obj;
        return this.id.equals(other.id);
	}
}
