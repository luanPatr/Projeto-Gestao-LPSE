package br.com.gestaolpse.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTabelaOrcamento extends AbstractTableModel {
	private ArrayList<OrcamentoModel>orcamentos;
	
	private static final String[] colunas ={
			"ID", "Cliente", "Data", "Valor"
	};
	
	
	
	public ModeloTabelaOrcamento(ArrayList<OrcamentoModel> orcamentos) {
		super();
		this.orcamentos = orcamentos;
	}

	@Override
	public int getRowCount() {
		return orcamentos.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		OrcamentoModel orcamento = orcamentos.get(rowIndex);
		if (columnIndex == 0) {
			return orcamento.getId_orcamento();
		} else if (columnIndex == 1) {
			return orcamento.getStatus_orcamento();
		} else if (columnIndex == 2) {
			return orcamento.getData_orcamento();
		} else if (columnIndex == 3) {
			return orcamento.getValor_orcamento();
		} else {
			return null;
		}
	}
	
	@Override
	public String getColumnName(int column) {

		return colunas[column];
	}
	
	public OrcamentoModel getOrcamento(int rowIndex) {
        return orcamentos.get(rowIndex);
    }
	
	public void setModelo(ArrayList<OrcamentoModel> orcamentos) {
        this.orcamentos = orcamentos;
        fireTableDataChanged();
    }

}
