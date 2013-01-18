package managedbeans;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import model.entities.NotaDeEntrada;
import model.repositories.NotaDeEntradaRepository;

@ManagedBean
public class NotaDeEntradaBean {
	
	private NotaDeEntrada nota = new NotaDeEntrada();
	private List<NotaDeEntrada> notas;
	
	public void adicionar(){
		EntityManager manager = this.getManager();
		NotaDeEntradaRepository repository = new NotaDeEntradaRepository(manager);
		
		if(this.nota.getCodigo() == null)
			repository.adicionar(nota);
		else
			repository.atualizar(nota);
		
		this.nota = new NotaDeEntrada();
		this.notas = null;
	}
	
	public void preparaAlteracao(){
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
