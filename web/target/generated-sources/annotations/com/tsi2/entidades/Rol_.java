package com.tsi2.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Rol.class)
public abstract class Rol_ {

	public static volatile ListAttribute<Rol, Usuario> usuarioList;
	public static volatile SingularAttribute<Rol, String> name;
	public static volatile SingularAttribute<Rol, String> description;
	public static volatile SingularAttribute<Rol, Integer> id;

}

