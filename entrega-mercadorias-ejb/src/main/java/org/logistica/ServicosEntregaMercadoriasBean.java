package org.logistica;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ServicosEntregaMercadoriasBean implements ServicosEntregaMercadorias{

    @PersistenceContext(name="LOGISTICA")
    private EntityManager entityManager;

	public void adicionaMapa(String origem, String destino, Integer distancia) throws MapaCadastradoException{

        LogisticaDAO dao = new LogisticaDAO();
        // Verifica a existencia do ponto de origem informado
        Vertice verticeOrigem = dao.buscaVertice(origem);

        // Verifica a existencia dos pontos origem e destino e caso já exista,
        // retorna um erro.
        if (verticeOrigem != null) {
            for (Aresta aresta : verticeOrigem.getArestas()) {
                if (aresta.getDestino().equals(destino)) {
                    throw new MapaCadastradoException("Mapa com origem em " + origem + " e destino em " + destino
                            + " já cadastrado.");
                }
            }
        } else {
            verticeOrigem = new Vertice(origem);
            dao.insereVertice(verticeOrigem, this.entityManager);
        }

        Vertice verticeDestino = dao.buscaVertice(destino);
        if (verticeDestino == null) {
            verticeDestino = new Vertice(destino);
            dao.insereVertice(verticeDestino, this.entityManager);
        }

        Aresta aresta = new Aresta(verticeOrigem, verticeDestino);
        aresta.setDistancia(distancia);
        dao.insereAresta(aresta, this.entityManager);



	}

	public MenorCaminho buscaCaminho(String origem, String destino, BigDecimal autonomia, BigDecimal valorCombustivel) throws VerticeNotFoundExcetion{
		LogisticaDAO dao = new LogisticaDAO();
		Vertice verticeOrigem = dao.buscaVertice(origem);
		if(verticeOrigem == null){
			throw new VerticeNotFoundExcetion("Origem não encontrada: " + origem);
		}

		Vertice verticeDestino = dao.buscaVertice(destino);
		if(verticeDestino == null){
			throw new VerticeNotFoundExcetion("Destino não encontrado: " + destino);
		}

        Collection<Vertice> vertices = dao.buscaTodosVertices();
        Graph<Vertice, Aresta> g = new SparseMultigraph<Vertice, Aresta>();

        for (Vertice vertice : vertices) {
            for(Aresta aresta: vertice.getArestas()){
                if(aresta.getOrigem().getDescricao().equals(origem)){
                    verticeOrigem = aresta.getOrigem();
                }else if(aresta.getDestino().getDescricao().equals(destino)){
                    verticeDestino = aresta.getDestino();
                }

                g.addEdge(aresta, aresta.getOrigem(), aresta.getDestino());
            }
        }

		Transformer<Aresta, Integer> wtTransformer = new Transformer<Aresta, Integer>() {
			public Integer transform(Aresta link) {
				return link.getDistancia();
			}
		};
		DijkstraShortestPath<Vertice, Aresta> alg = new DijkstraShortestPath<Vertice, Aresta>(g, wtTransformer);
		List<Aresta> l = alg.getPath(verticeOrigem, verticeDestino);
		Number distanciaTotal = alg.getDistance(verticeOrigem, verticeDestino);

		BigDecimal custoTotal = BigDecimal.valueOf(distanciaTotal.intValue()).divide(autonomia).setScale(2,BigDecimal.ROUND_HALF_DOWN);
		custoTotal = custoTotal.multiply(valorCombustivel);

		MenorCaminho caminho = new MenorCaminho();
		caminho.setMenorCaminho(l.toString());
		caminho.setDistanciaRota(distanciaTotal);
		caminho.setCustoRota(custoTotal);
		return caminho;
	}
}
