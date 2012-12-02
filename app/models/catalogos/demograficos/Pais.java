package models.catalogos.demograficos;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NamedQuery;

import models.catalogos.Catalogo;

import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;

@Entity
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class Pais extends Catalogo{

	@Required
	@MinSize(2) @MaxSize(2)
	public String iso2;
	@Required
	@MinSize(3) @MaxSize(3)
	public String iso3;
	
	public static Pais findByIso(final String iso){
		if(iso==null)return null;
		if(iso.trim().length()==2){
			return Pais.find("byIso2", iso).first();
		}else if(iso.trim().length()==3){
			return Pais.find("byIso3", iso).first();
		}
		return null;
	}
}
