package models.datasec;

import static play.Logger.debug;

import java.lang.reflect.Field;

import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import play.db.jpa.Model;
import play.libs.Crypto;
import play.modules.datasec.Encrypted;
import play.modules.datasec.SecurePassword;

@MappedSuperclass
public class SecureModel extends Model{

	@PrePersist
	@PreUpdate
	public void encrypt(){
		debug("encrypting fields");
		
		Field[] fields = this.getClass().getFields();
		
		for(Field field:fields){
			if(field.getAnnotation(Encrypted.class)!=null){
				String content;
				try {
					content = (String)field.get(this);
				} catch (IllegalArgumentException e) {
					throw new RuntimeException(e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
				if(content!=null && content instanceof String){
					debug("encrypting field %s of %s",field.getName(),this.getClass().getName());
					content = Crypto.encryptAES(content);
					try {
						field.set(this, content);
					} catch (IllegalArgumentException e) {
						throw new RuntimeException(e);
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					}
				}
				
			}
			
			if(field.getAnnotation(SecurePassword.class)!=null){
				String content;
				try {
					content = (String)field.get(this);
				} catch (IllegalArgumentException e) {
					throw new RuntimeException(e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
				if(content!=null && content instanceof String){
					debug("hashing field %s of %s",field.getName(),this.getClass().getName());
					content = Crypto.passwordHash(content);
					try {
						field.set(this, content);
					} catch (IllegalArgumentException e) {
						throw new RuntimeException(e);
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					}
				}
			}
			
		}
	}
	
	@PostLoad
	public void decrypt(){
		debug("decrypting fields");
		
		Field[] fields = this.getClass().getFields();
		
		for(Field field:fields){
			if(field.getAnnotation(Encrypted.class)!=null){
				String content;
				try {
					content = (String)field.get(this);
				} catch (IllegalArgumentException e) {
					throw new RuntimeException(e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
				if(content!=null && content instanceof String){
					debug("decrypting field %s of %s",field.getName(),this.getClass().getName());
					content = Crypto.decryptAES(content);
					try {
						field.set(this, content);
					} catch (IllegalArgumentException e) {
						throw new RuntimeException(e);
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					}
				}
			}
			
		}
	}
	
}
