package managedbeans;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import model.entities.MovimentoDeEntrada;
import model.repositories.MovimentoDeEntradaRepository;

@ManagedBean
public class MovimentoDeEntradaBean {

	private MovimentoDeEntrada entrada = new MovimentoDeEntrada();
	private List<MovimentoDeEntrada> entradas;
	
	public void adicionar(){
		EntityManager manager = this.getManager();
		MovimentoDeEntradaRepository repository = new MovimentoDeEntradaRepository(manager);
		
		if(entrada.getCodigo() == null){
			repository.adicionar(entrada);
		}else{
			repository.atualizar(entrada);
		}
		
		this.entrada = new MovimentoDeEntrada();
		this.entradas = null;
	}
	
	public void preparaAlteracao(){
		System.out.println("movimentação de entrada");
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Long codigo = Long.parseLong(params.get("codigo"));
		EntityManager manager = this.getManager();
		MovimentoDeEntradaRepository repository = new MovimentoDeEntradaRepository(manager);
		this.entrada = repository.procura(codigo);
	}

	private EntityManager getManager() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("EntityManager");
	}

	public MovimentoDeEntrada getEntrada() {
		return entrada;
	}

	public void setEntrada(MovimentoDeEntrada entrada) {
		this.entrada = entrada;
	}

	public List<MovimentoDeEntrada> getEntradas() {
		if(this.entradas == null){
			MovimentoDeEntradaRepository repository = new MovimentoDeEntradaRepository(this.getManager());
			this.entradas = repository.getLista();
		}
		return entradas;
	}

	public void setEntradas(List<MovimentoDeEntrada> entradas) {
		this.entradas = entradas;
	}
}
