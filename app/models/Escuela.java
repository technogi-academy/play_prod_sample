package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import models.catalogos.demograficos.Estado;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Escuela extends Model{

	@Required
	public String nombre;
	
	@ManyToOne
	@Required
	public Estado estado;
}
