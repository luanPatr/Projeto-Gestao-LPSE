package br.com.gestaolpse.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class modeloTabela  extends AbstractTableModel{
	
	private static final String[] colunas ={
			"ID", "Nome", "Telefone", "CPF", "Endereco"
		};
	
	private ArrayList<ClienteModel> clientes;
	@Override
	public int getRowCount() {

		return clientes.size();
	}

	public modeloTabela(ArrayList<ClienteModel> clientes) {
		super();
		this.clientes = clientes;
	}

	@Override
	public int getColumnCount() {

		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ClienteModel cliente = clientes.get(rowIndex);
		if (columnIndex == 0) {
			return cliente.getId_cliente();
		} else if (columnIndex == 1) {
			return cliente.getNome_cliente();
		} else if (columnIndex == 2) {
			return cliente.getTelefone_cliente();
		} else if (columnIndex == 3) {
			return cliente.getCpf_cliente();
		} else if (columnIndex == 4) {
			return cliente.getEndereco_cliente();
		} else {
			return null;
		}
	}
	
	public ClienteModel getCliente(int rowIndex) {
        return clientes.get(rowIndex);
    }
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colunas[column];
	}
	
	public void setModelo(ArrayList<ClienteModel> clientes) {
        this.clientes = clientes;
        fireTableDataChanged();
    }
	/*public ClienteModel getCliente(int linhaSelecionada) {
		// TODO Auto-generated method stub
		return null;
	}*/
	

}
