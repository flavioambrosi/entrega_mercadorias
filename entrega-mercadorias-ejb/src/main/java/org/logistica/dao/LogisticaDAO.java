package org.logistica.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.logistica.estrutura.Aresta;
import org.logistica.estrutura.Vertice;

public class LogisticaDAO {

	private String ENTITY_MANAGER_NAME = "LOGISTICA";

	public void insereAresta(Aresta aresta) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory(ENTITY_MANAGER_NAME);
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(aresta);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			emf.close();
		}
	}

	public void insereVertice(Vertice vertice) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory(ENTITY_MANAGER_NAME);
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(vertice);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			emf.close();
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
			em.getTransaction().rollback();
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
			Query query = em.createQuery("SELECT e FROM Vertice e where e.descricao=" + nomeVertice);
			vertice = (Vertice) query.getSingleResult();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			emf.close();
		}
		
		return vertice;
	}
}
