package org.logistica.estrutura;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Vertice.class)
public abstract class Vertice_ {

	public static volatile SingularAttribute<Vertice, Long> id;
	public static volatile ListAttribute<Vertice, Aresta> arestas;
	public static volatile SingularAttribute<Vertice, String> descricao;

}

