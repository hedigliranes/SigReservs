package br.ufrn.imd.reservs.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufrn.imd.reservs.dominio.Sala;
import br.ufrn.imd.reservs.repositorios.SalaRepositorio;


@Named()
@SessionScoped
public class SalaMBean implements Serializable{
	

	private Sala sala;
	
	private DataModel<Sala> materiaisModel;
	
	@Inject
	private UsuarioMBean usuarioMBean;
	
	@Inject
	private SalaRepositorio salaRepositorio;
	
	public SalaMBean() {
		sala = new Sala();
	}
	
	public String novoSala() {
		sala = new Sala();
		return "/pages/sala/form.jsf";
	}
	
	public String listarMateriais() {
		materiaisModel = new ListDataModel<Sala>
		(salaRepositorio.listarMateriais());
		return "/pages/sala/list.jsf";
	}
	
	public String cadastrarSala() {
		salaRepositorio.adicionar(sala);
		sala = new Sala();
		return "/pages/sala/form.jsf";
	}
	
	public String removerSala() {
		Sala salaRemovido = materiaisModel.getRowData();
		salaRepositorio.remover(salaRemovido);
		materiaisModel = new ListDataModel<Sala>
		(salaRepositorio.listarMateriais());
		return "/pages/sala/list.jsf";
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public DataModel<Sala> getMateriaisModel() {
		return materiaisModel;
	}

	public void setMateriaisModel(DataModel<Sala> materiaisModel) {
		this.materiaisModel = materiaisModel;
	}

	public UsuarioMBean getUsuarioMBean() {
		return usuarioMBean;
	}

	public void setUsuarioMBean(UsuarioMBean usuarioMBean) {
		this.usuarioMBean = usuarioMBean;
	}

}
