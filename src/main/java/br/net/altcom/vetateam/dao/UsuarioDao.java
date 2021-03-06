package br.net.altcom.vetateam.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.net.altcom.vetateam.modelo.Usuario;

@Stateless
public class UsuarioDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void adiciona(Usuario usuario){
		manager.persist(usuario);
	}
	
	public boolean isExiste(Usuario usuario){
		return (buscaPorEmail(usuario) != null);
	}
	
	public Usuario buscaPorEmail(Usuario usuario){
		String jpql = "Select u from Usuario u where u.email like :email";
		TypedQuery<Usuario> query = manager.createQuery(jpql, Usuario.class)
				.setParameter("email", usuario.getEmail());
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
