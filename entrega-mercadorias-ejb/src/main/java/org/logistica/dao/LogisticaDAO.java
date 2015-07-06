package org.logistica.dao;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.logistica.estrutura.Aresta;
import org.logistica.estrutura.Vertice;

public class LogisticaDAO {

	private String ENTITY_MANAGER_NAME = "LOGISTICA";

	public void insereAresta(Aresta aresta, EntityManager em) {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory(ENTITY_MANAGER_NAME);
//		EntityManager em = emf.createEntityManager();

		try {
			em.persist(aresta);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

	public void insereVertice(Vertice vertice, EntityManager em) {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory(ENTITY_MANAGER_NAME);
//		EntityManager em = emf.createEntityManager();

		try {
			em.persist(vertice);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Collection<Vertice> buscaTodosVertices() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory(ENTITY_MANAGER_NAME);
		EntityManager em = emf.createEntityManager();
		Collection<Vertice> vertices = new ArrayList<Vertice>();

		try {
			Query query = em.createQuery("SELECT e FROM Vertice e");
			vertices = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			emf.close();
		}

		return vertices;
	}

	public Vertice buscaVertice(String nomeVertice) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(ENTITY_MANAGER_NAME);
		EntityManager em = emf.createEntityManager();

		Vertice vertice = null;
		try {
			Query query = em.createQuery("FROM Vertice e where e.descricao ='" + nomeVertice + "'");
			vertice = (Vertice) query.getSingleResult();
		} catch (NoResultException e){
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			emf.close();
		}

		return vertice;
	}
}
