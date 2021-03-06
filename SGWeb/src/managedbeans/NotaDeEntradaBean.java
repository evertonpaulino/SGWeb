package managedbeans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import model.entities.NotaDeEntrada;
import model.repositories.ContaRepository;
import model.repositories.NotaDeEntradaRepository;

@ManagedBean
public class NotaDeEntradaBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private NotaDeEntrada nota = new NotaDeEntrada();
	private List<NotaDeEntrada> notas;
	
	public void adicionar(){
		System.out.println("� para adicionar");
		EntityManager manager = this.getManager();
		NotaDeEntradaRepository repositoryNota = new NotaDeEntradaRepository(manager);
		
		if(this.nota.getCodigo() == null){
			repositoryNota.adicionar(nota);
		}else{
			repositoryNota.atualizar(nota);
		}
		
		this.nota = new NotaDeEntrada();
		this.notas = null;
	}
	
	public void preparaAlteracao(){
		System.out.println("passou");
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Long codigo = Long.parseLong(params.get("codigo"));
		NotaDeEntradaRepository repository = new NotaDeEntradaRepository(this.getManager());
		this.nota = repository.procura(codigo);
	}
	
	public void remove(){
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Long codigo = Long.parseLong(params.get("codigo"));
		NotaDeEntradaRepository repository = new NotaDeEntradaRepository(this.getManager());
		repository.remove(codigo);
		
		this.notas = null;
	}
	
	public void buscaConta(){
		System.out.println("buscaConta - NotaDeEntradaBean");
		EntityManager manager = this.getManager();
		ContaRepository repository = new ContaRepository(manager);
		this.nota.setConta(repository.procura(this.nota.getConta().getCodigoReduzido()));
	}

	
	private EntityManager getManager() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("EntityManager");
	}

	public NotaDeEntrada getNota() {
		return nota;
	}

	public void setNota(NotaDeEntrada nota) {
		this.nota = nota;
	}

	public List<NotaDeEntrada> getNotas() {
		if(this.notas == null){
			NotaDeEntradaRepository repository = new NotaDeEntradaRepository(this.getManager());
			this.notas = repository.getLista();
		}
		
		return this.notas;
	}

	public void setNotas(List<NotaDeEntrada> notas) {
		this.notas = notas;
	}

}
