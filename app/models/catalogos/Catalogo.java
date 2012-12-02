package models.catalogos;

import javax.persistence.Cacheable;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@MappedSuperclass
public abstract class Catalogo extends Model {

	@Required
	@MaxSize(20)
	public String name;
	@MaxSize(200)
	public String descripcion;
	@Override
	public String toString() {
		return "Catalogo [name=" + name + ", descripcion=" + descripcion + "]";
	}
	
	

}
