package br.net.altcom.vetateam.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Representante {

	@Id
	private Integer id;
	private String nome;

	@OneToMany(mappedBy = "representante")
	private List<Cliente> cliente;

	@OneToOne
	private Usuario usuario;
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public List<Cliente> getCliente() {
		return cliente;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void adicionaCliente(Cliente cliente) {
		this.cliente.add(cliente);
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
