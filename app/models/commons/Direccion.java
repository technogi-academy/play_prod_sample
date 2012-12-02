package models.commons;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.modules.datasec.Encrypted;

import models.catalogos.demograficos.Estado;
import models.datasec.SecureModel;

@Entity
public class Direccion extends SecureModel{

	@Encrypted
	public String calle;
	
	@Encrypted
	public String numero;
	
	@Encrypted
	public String colonia;
	
	@ManyToOne
	public Estado estado;
	
	public static enum Tipo{
		Casa,Oficina,Postal,Otra
	}
}
