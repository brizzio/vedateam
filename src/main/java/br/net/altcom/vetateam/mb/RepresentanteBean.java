package br.net.altcom.vetateam.mb;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.net.altcom.vetateam.dao.LancamentoDao;
import br.net.altcom.vetateam.modelo.Lancamento;

@Named
@SessionScoped
public class RepresentanteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String data;

	@Inject
	private LancamentoDao lancamentoDao;
	@Inject
	private UsuarioLogadoBean usuarioLogado;
	
	private List<Lancamento> lancamentos = new ArrayList<>();
	
	public String buscaLancamento() {
		YearMonth yearMonth = YearMonth.parse(data);
		lancamentos = lancamentoDao.buscaLancamentoPorMesEAno(usuarioLogado.getUsuario().getRepresentante(), yearMonth);
		return "";
	}

	public void imprimir(){		
		System.out.println(lancamentos.size());
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
}
