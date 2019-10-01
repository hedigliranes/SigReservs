package br.ufrn.imd.reservs.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufrn.imd.reservs.dominio.Reserva;
import br.ufrn.imd.reservs.repositorios.ReservaRepositorio;

@Named()
@SessionScoped
public class ReservaMBean implements Serializable{
	
	private Reserva reserva;
	
	private DataModel<Reserva> materiaisModel;
	
	@Inject
	private UsuarioMBean usuarioMBean;
	
	@Inject
	private ReservaRepositorio reservaRepositorio;
	
	public ReservaMBean() {
		reserva = new Reserva();
	}
	
	public String novoReserva() {
		reserva = new Reserva();
		return "/pages/reserva/form.jsf";
	}
	
	public String listarMateriais() {
		materiaisModel = new ListDataModel<Reserva>
		(reservaRepositorio.listarMateriais());
		return "/pages/reserva/list.jsf";
	}
	
	public String cadastrarReserva() {
		reserva.setUsuario(usuarioMBean.getUsuarioLogado());
		reservaRepositorio.adicionar(reserva);
		reserva = new Reserva();
		return "/pages/reserva/form.jsf";
	}
	
	public String removerReserva() {
		Reserva reservaRemovido = materiaisModel.getRowData();
		reservaRepositorio.remover(reservaRemovido);
		materiaisModel = new ListDataModel<Reserva>
		(reservaRepositorio.listarMateriais());
		return "/pages/reserva/list.jsf";
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public DataModel<Reserva> getMateriaisModel() {
		return materiaisModel;
	}

	public void setMateriaisModel(DataModel<Reserva> materiaisModel) {
		this.materiaisModel = materiaisModel;
	}

	public UsuarioMBean getUsuarioMBean() {
		return usuarioMBean;
	}

	public void setUsuarioMBean(UsuarioMBean usuarioMBean) {
		this.usuarioMBean = usuarioMBean;
	}

}
