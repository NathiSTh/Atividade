package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.BancoDaoNathalia;
import br.com.faculdadedelta.modelo.BancoNathalia;

@ManagedBean
@SessionScoped
public class BancoControllerNathalia {

	private BancoNathalia banco = new BancoNathalia();
	private BancoDaoNathalia dao= new BancoDaoNathalia();
	

	public BancoNathalia getBanco() {
		return banco;
	}

	public void setBanco(BancoNathalia banco) {
		this.banco = banco;
	}
	
	public void limparCampo() {
		banco = new BancoNathalia();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		try {
		if(banco.getId()==null) {
				dao.incluir(banco);
				limparCampo();
				exibirMensagem("Inclusao realizada com sucesso.");
			}else {
				dao.alterar(banco);
				limparCampo();
				exibirMensagem("Alteracao realizada com sucesso.");
			}
			} catch (Exception e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return "CadastroBanco.xhtml";
		
	}
	public String editar() {
		return "CadastroBanco.xhtml";
		
	}
	
	public String excluir() {
		
		try {
			dao.excluir(banco);
			limparCampo();
			exibirMensagem("Exclusao realizada com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "ListaBanco.xhtml";
		
	}
	
	public List<BancoNathalia> getLista(){
		List<BancoNathalia> listaRetorno = new ArrayList<BancoNathalia>();
		try {
			listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return listaRetorno;
		
	}
	
	
}
