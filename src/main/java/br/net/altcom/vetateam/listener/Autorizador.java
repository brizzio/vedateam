package br.net.altcom.vetateam.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import br.net.altcom.vetateam.mb.UsuarioLogadoBean;

public class Autorizador implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioLogadoBean usuarioLogado;
	
	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		
		if (facesContext.getViewRoot().getViewId().equals("login.xhtml"))
			return;
		
		if (!usuarioLogado.isLogado()) {
			NavigationHandler handler = facesContext.getApplication().getNavigationHandler();
			handler.handleNavigation(facesContext, null, "login?faces-redirect=true");
			facesContext.renderResponse();
		}
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
}
