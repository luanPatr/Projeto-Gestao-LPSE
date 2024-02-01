package br.com.gestaolpse.model;

import java.sql.SQLException;


import br.com.gestaolpse.dao.ClienteDao;
import br.com.gestaolpse.view.FormCadastroCliente;

public class ClienteModel {

	protected String id_cliente;
	protected String nome_cliente;
	protected String telefone_cliente;
	protected String cpf_cliente;
	protected String endereco_cliente;
	
	public ClienteModel() {
		
	}

	public ClienteModel(String id_cliente, String nome_cliente, String telefone_cliente, String cpf_cliente,
			String endereco_cliente) {
		super();
		this.id_cliente = id_cliente;
		this.nome_cliente = nome_cliente;
		this.telefone_cliente = telefone_cliente;
		this.cpf_cliente = cpf_cliente;
		this.endereco_cliente = endereco_cliente;
	}

	public String getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getNome_cliente() {
		return nome_cliente;
	}

	public void setNome_cliente(String nome_cliente) {
		this.nome_cliente = nome_cliente;
	}

	public String getTelefone_cliente() {
		return telefone_cliente;
	}

	public void setTelefone_cliente(String telefone_cliente) {
		this.telefone_cliente = telefone_cliente;
	}

	public String getCpf_cliente() {
		return cpf_cliente;
	}

	public void setCpf_cliente(String cpf_cliente) {
		this.cpf_cliente = cpf_cliente;
	}

	public String getEndereco_cliente() {
		return endereco_cliente;
	}

	public void setEndereco_cliente(String endereco_cliente) {
		this.endereco_cliente = endereco_cliente;
	}

	@Override
	public String toString() {
		return "ClienteModel [id_cliente=" + id_cliente + ", nome_cliente=" + nome_cliente + ", telefone_cliente="
				+ telefone_cliente + ", cpf_cliente=" + cpf_cliente + ", endereco_cliente=" + endereco_cliente + "]";
	}
	
	

}