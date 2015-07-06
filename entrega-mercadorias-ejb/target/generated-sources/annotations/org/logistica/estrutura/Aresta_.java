package org.logistica.estrutura;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Aresta.class)
public abstract class Aresta_ {

	public static volatile SingularAttribute<Aresta, Vertice> destino;
	public static volatile SingularAttribute<Aresta, Long> id;
	public static volatile SingularAttribute<Aresta, Integer> distancia;
	public static volatile SingularAttribute<Aresta, Vertice> origem;

}

