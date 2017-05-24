package br.net.altcom.vetateam.mb;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.net.altcom.vetateam.modelo.Usuario;

@Named @SessionScoped
public class UsuarioLogadoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	
	public void logado(Usuario usuario){
		this.usuario = usuario;
	}
	
	public void deslogar(){
		this.usuario = null;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
}
