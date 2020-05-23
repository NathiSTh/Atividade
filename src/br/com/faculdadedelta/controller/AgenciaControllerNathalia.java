package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.AgenciaDaoNathalia;
import br.com.faculdadedelta.modelo.AgenciaNathalia;
import br.com.faculdadedelta.modelo.BancoNathalia;

@ManagedBean
@SessionScoped
public class AgenciaControllerNathalia {

	private AgenciaNathalia agencia = new AgenciaNathalia();
	private AgenciaDaoNathalia dao = new AgenciaDaoNathalia();
	private BancoNathalia bancoSelecionado = new BancoNathalia();

	public AgenciaNathalia getAgencia() {
		return agencia;
	}

	public void setAgencia(AgenciaNathalia agencia) {
		this.agencia = agencia;
	}

	public BancoNathalia getBancoSelecionado() {
		return bancoSelecionado;
	}

	public void setBancoSelecionado(BancoNathalia bancoSelecionado) {
		this.bancoSelecionado = bancoSelecionado;
	}
	
	public void limparCampos() {
		agencia = new AgenciaNathalia();
		bancoSelecionado = new BancoNathalia();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		try {
		if(agencia.getId()==null) {
			agencia.setBanco(bancoSelecionado);
				dao.incluir(agencia);
				limparCampos();
				exibirMensagem("Inclusao realizada com sucesso.");
			} else {
				dao.alterar(agencia);
				limparCampos();
				exibirMensagem("Alteracao realizada com sucesso.");
			}
			}catch (Exception e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "CadastroAgencia.xhtml";
	}
	
	public String editar() {
		bancoSelecionado = agencia.getBanco();
		return "CadastroAgencia.xhtml";
		
	}
	
	public String excluir() {
		
		try {
			dao.excluir(agencia);
			limparCampos();
			exibirMensagem("Exclusao realizada com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return "ListaAgencia.xhtml";
		
	}
	
	public List<AgenciaNathalia> getLista(){
		List<AgenciaNathalia>listaRetorno = new ArrayList<>();
		
		try {
			listaRetorno= dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		
		
		return listaRetorno;
		
	}
	
}
