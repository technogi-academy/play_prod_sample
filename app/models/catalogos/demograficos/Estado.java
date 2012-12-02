package models.catalogos.demograficos;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import models.catalogos.Catalogo;

@Entity
public class Estado extends Catalogo{

	@ManyToOne
	public Pais pais;
	
}
