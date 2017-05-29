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
	
	@Inject
	private UsuarioLogadoBean usuarioLogado;

	public String login() {

		if (!usuarioDao.isExiste(usuario))
			return "login?faces-redirect=true";

		Usuario usuarioBanco = usuarioDao.buscaPorEmail(usuario);
		
		if (usuarioBanco.getSenha().equals(usuario.getSenha())) {
			usuarioLogado.logado(usuarioBanco);
			return "representante?faces-redirect=true";
		} else {
			return "login?faces-redirect=true";
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}
}
