package managedbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import model.entities.Conta;
import model.repositories.ContaRepository;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean
public class ContaBean {
	
	private Conta conta = new Conta();
	private List<Conta> contas;
	private TreeNode root;
	private TreeNode selectedNode;
	
	public ContaBean(){
		Conta conta = new Conta();
		conta.setDescricao("PLano de Conta");
		this.root = new DefaultTreeNode(conta, null);
		createTree();
	}
	
	public void adiciona(){
		EntityManager manager = this.getManager();
		ContaRepository repository = new ContaRepository(manager);
		
		if(this.conta.getCodigoReduzido() == null)
			repository.adiciona(this.conta);
		else
			repository.atualiza(this.conta);
		
		this.conta = new Conta();
		this.contas = null;
	}

	public void preparaAlteracao(){
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Long codigoReduzido = Long.parseLong(params.get("codigoReduzido"));
		EntityManager manager = this.getManager();
		ContaRepository repository = new ContaRepository(manager);
		this.conta = repository.procura(codigoReduzido);
	}
	
	public void remove(){
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Long codigoReduzido = Long.parseLong(params.get("codigoReduzido"));
		EntityManager manager = this.getManager();
		ContaRepository repository = new ContaRepository(manager);
		repository.remove(codigoReduzido);
		this.contas = null;
	}
	
	private EntityManager getManager() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return (EntityManager) request.getAttribute("EntityManager");
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public List<Conta> getContas() {
		if(this.contas == null){
			EntityManager manager = this.getManager();
			ContaRepository repository = new ContaRepository(manager);
			this.contas = repository.getLista();
		}
		return this.contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	private void createTree() {
		List<Conta> listaAux = new ArrayList<Conta>();
		listaAux.addAll(getContas());
		
		List<TreeNode> listContaPai = new ArrayList<TreeNode>();

		TreeNode paiAux = null;

		int i = 0;

		while (!listaAux.isEmpty()) {

			String auxContaPaiValue = listaAux.get(i).getContaPai();
			
			if (auxContaPaiValue == null) {
				Conta conta = listaAux.get(i);
				
				TreeNode node = new DefaultTreeNode(conta, root);
				
				listContaPai.add(node);
				
				listaAux.remove(listaAux.get(i));
				i--;
			} else if (paiAux != null && auxContaPaiValue.equals(((Conta)paiAux.getData()).getContaContabil())) {
				Conta conta = listaAux.get(i);

				TreeNode node = new DefaultTreeNode(conta, paiAux);
				
				listContaPai.add(node);
				
				listaAux.remove(listaAux.get(i));
				i--;
			}
			
			i++;
			
			if (listaAux.size() == i) {
				i = 0;

				if (!listContaPai.isEmpty()) {
					paiAux = listContaPai.get(i);
					listContaPai.remove(i);
				}
			}
		}
	}
	
	public void seleccionaConta(){
		this.conta = (Conta) this.selectedNode.getData();
	}
	
	public void criaNovaConta(){
		this.conta = new Conta();
		this.conta.setContaPai(((Conta) this.selectedNode.getData()).getContaContabil());
	}
}
