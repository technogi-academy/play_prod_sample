package models;

import static play.Logger.debug;

import java.lang.reflect.Field;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import play.data.validation.Password;
import play.data.validation.Required;
import play.libs.Crypto;
import play.modules.datasec.Encrypted;
import play.modules.datasec.SecurePassword;
import models.datasec.SecureModel;

@Entity
public class Usuario extends SecureModel{

	@Required
	public String username;
	@Password
	private String password;
	
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String password){
		this.password = Crypto.passwordHash(password);
	}
	
}
