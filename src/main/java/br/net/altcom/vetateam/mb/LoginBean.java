package br.net.altcom.vetateam.mb;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.net.altcom.vetateam.dao.UsuarioDao;
import br.net.altcom.vetateam.modelo.Usuario;

@Model
public class LoginBean {

	private Usuario usuario = new Usuario();
	@Inject
	private UsuarioDao usuarioDao;
	public void login(){
		if (!usuarioDao.isExiste(usuario))
			return;
		
		Usuario usuarioBanco = usuarioDao.buscaPorEmail(usuario);
		
		if (usuarioBanco.getSenha().equals(usuario.getSenha())) {
			System.out.println("Fez login");
		}
	}
}
