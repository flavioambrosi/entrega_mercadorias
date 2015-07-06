package org.logistica.main;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections15.Transformer;
import org.logistica.estrutura.Aresta;
import org.logistica.estrutura.Vertice;

import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;

public class MainJung {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Graph<Vertice, Aresta> g = new SparseMultigraph<Vertice, Aresta>();

		// Criar vertices
		Vertice a = new Vertice("A");
		a.setId(Long.valueOf(1));
		Vertice b = new Vertice("B");
		b.setId(Long.valueOf(2));
		Vertice c = new Vertice("C");
		c.setId(Long.valueOf(3));
		Vertice d = new Vertice("D");
		d.setId(Long.valueOf(4));
		Vertice e = new Vertice("E");
		e.setId(Long.valueOf(5));

		Aresta ab = new Aresta(a, b);
		ab.setDistancia(10);
		a.getArestas().add(ab);

		Aresta bd = new Aresta(b, d);
		bd.setDistancia(15);
		b.getArestas().add(bd);

		Aresta ac = new Aresta(a, c);
		ac.setDistancia(20);
		a.getArestas().add(ac);

		Aresta cd = new Aresta(c, d);
		cd.setDistancia(30);
		c.getArestas().add(cd);

		Aresta be = new Aresta(b, e);
		be.setDistancia(50);
		b.getArestas().add(be);

		Aresta de = new Aresta(d, e);
		de.setDistancia(30);
		d.getArestas().add(de);

        List<Vertice> vertices = new ArrayList<Vertice>();
        vertices.add(a);
        vertices.add(b);
        vertices.add(c);
        vertices.add(d);
        vertices.add(e);
		for (Vertice vertice : vertices) {
//			g.addVertex(vertice);

			for(Aresta aresta: vertice.getArestas()){
				g.addEdge(aresta, aresta.getOrigem(), aresta.getDestino());
			}
		}

		Transformer<Aresta, Integer> wtTransformer = new Transformer<Aresta, Integer>() {
			public Integer transform(Aresta link) {
				return link.getDistancia();
			}
		};
		DijkstraShortestPath<Vertice, Aresta> alg = new DijkstraShortestPath<Vertice, Aresta>(
				g, wtTransformer);
		List<Aresta> l = alg.getPath(a, d);
		Number dist = alg.getDistance(a, d);
		System.out.println("The shortest path from" + a + " to " + d + " is:");
		System.out.println(l.toString());
		System.out.println("and the length of the path is: " + dist);


	}

}
