package br.ufrn.imd.reservs.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Sala {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SALA")
	@SequenceGenerator(name="SEQ_SALA", sequenceName="id_sq_usuario",allocationSize=1)
	private int id;
	
	private boolean status;
	
	private String Nome;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}
	
}
