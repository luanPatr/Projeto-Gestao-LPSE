package br.com.gestaolpse.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class modeloTabelaAgendamento extends AbstractTableModel {
	
	
	private static final String[] colunas ={
			"Nome", "Hora", "Data"
	};
	

	private ArrayList<AgendamentoModel>agendamentos;
	
	public modeloTabelaAgendamento(ArrayList<AgendamentoModel> agendamentos) {
		super();
		this.agendamentos = agendamentos;
	}

	@Override
	public int getRowCount() {
		return agendamentos.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		AgendamentoModel agendamento = agendamentos.get(rowIndex);
		if (columnIndex == 0) {
			return agendamento.getNome_agendamento();
		} else if (columnIndex == 1) {
			return agendamento.getHora_agendamento();
		} else if (columnIndex == 2) {
			return agendamento.getData_agendamento();
		} else {
			return null;
		}
	}
	
	@Override
	public String getColumnName(int column) {

		return colunas[column];
	}
	
	public AgendamentoModel getAgendamento(int rowIndex) {
        return agendamentos.get(rowIndex);
    }
	
	public void setModelo(ArrayList<AgendamentoModel> agendamentos) {
        this.agendamentos = agendamentos;
        fireTableDataChanged();
    }

}