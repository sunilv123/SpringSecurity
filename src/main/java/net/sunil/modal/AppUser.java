package net.sunil.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnTransformer;

@Entity
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	
	
	//@ColumnTransformer(read = "pgp_sym_decrypt(email, ‘secretKey')", write = "pgp_sym_encrypt(?, ‘secretKey’)")
	/*@ColumnTransformer(
		    read =  "pgp_sym_decrypt(" +
		            "    email, " +
		            "    current_setting('secret.key')" +
		            ")",
		    write = "pgp_sym_encrypt( " +
		            "    ?, " +
		            "    current_setting('secret.key')" +
		            ") ")
	//@Transient
	@Column(columnDefinition="bytea")*/
	private String email;
	
	private String mobile;
	
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
