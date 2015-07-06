package org.logistica;

import java.math.BigDecimal;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.collections15.Transformer;
import org.logistica.dao.LogisticaDAO;
import org.logistica.estrutura.Aresta;
import org.logistica.estrutura.Vertice;
import org.logistica.exception.MapaCadastradoException;
import org.logistica.exception.VerticeNotFoundExcetion;

import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;

@Stateless (name = "ejb/ServicosEntregaMercadorias", mappedName = "ejb/ServicosEntregaMercadorias")
public class ServicosEntregaMercadoriasBean implements ServicosEntregaMercadorias{

    private static String ENTITY_MANAGER_NAME = "LOGISTICA";

	public void adicionaMapa(String origem, String destino, Integer distancia) throws MapaCadastradoException{
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory(ENTITY_MANAGER_NAME);
        EntityManager em = emf.createEntityManager();

		try {
		    LogisticaDAO dao = new LogisticaDAO();
		      //Verifica a existencia do ponto de origem informado
	        Vertice verticeOrigem = dao.buscaVertice(origem);

	        //Verifica a existencia dos pontos origem e destino e caso já exista, retorna um erro.
	        if(verticeOrigem != null){
	            for(Aresta aresta : verticeOrigem.getArestas()){
	                if(aresta.getDestino().equals(destino)){
	                    throw new MapaCadastradoException("Mapa com origem em " + origem + " e destino em " + destino + " já cadastrado.");
	                }
	            }
	        } else {
	            verticeOrigem = new Vertice(origem);
	            dao.insereVertice(verticeOrigem, em);
	        }

	        Vertice verticeDestino = dao.buscaVertice(destino);
	        if(verticeDestino == null) {
	            verticeDestino = new Vertice(destino);
	            dao.insereVertice(verticeDestino, em);
	        }

	        Aresta aresta = new Aresta(verticeOrigem, verticeDestino);
	        aresta.setDistancia(distancia);
	        dao.insereAresta(aresta, em);

	        this.montaGrafo();

		} finally {
		    emf.close();
		}

	}

	public BigDecimal buscaCaminho(String origem, String destino, BigDecimal autonomia, BigDecimal valorCombustivel) throws VerticeNotFoundExcetion{
		LogisticaDAO dao = new LogisticaDAO();
		Vertice verticeOrigem = dao.buscaVertice(origem);
		if(verticeOrigem == null){
			throw new VerticeNotFoundExcetion("Origem não encontrada: " + origem);
		}

		Vertice verticeDestino = dao.buscaVertice(destino);
		if(verticeDestino == null){
			throw new VerticeNotFoundExcetion("Destino não encontrado: " + destino);
		}

		Graph<Vertice, Aresta> g = this.montaGrafo();

		Transformer<Aresta, Integer> wtTransformer = new Transformer<Aresta, Integer>() {
			public Integer transform(Aresta link) {
				return link.getDistancia();
			}
		};
		DijkstraShortestPath<Vertice, Aresta> alg = new DijkstraShortestPath<Vertice, Aresta>(g, wtTransformer);
		Number distanciaTotal = alg.getDistance(verticeOrigem, verticeDestino);

		BigDecimal custoTotal = BigDecimal.valueOf(distanciaTotal.intValue()).divide(autonomia).setScale(2,BigDecimal.ROUND_HALF_DOWN);
		custoTotal = custoTotal.multiply(valorCombustivel);

		return custoTotal;
	}

	private Graph<Vertice, Aresta> montaGrafo(){
		LogisticaDAO dao = new LogisticaDAO();

		Collection<Vertice> vertices = dao.buscaTodosVertices();
		Graph<Vertice, Aresta> g = new SparseMultigraph<Vertice, Aresta>();

		for (Vertice vertice : vertices) {
			g.addVertex(vertice);

			for(Aresta aresta: vertice.getArestas()){
				g.addEdge(aresta, aresta.getOrigem(), aresta.getDestino());
			}
		}

		return g;
	}

}
