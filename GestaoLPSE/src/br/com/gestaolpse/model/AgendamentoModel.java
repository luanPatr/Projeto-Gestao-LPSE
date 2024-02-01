package br.com.gestaolpse.model;

public class AgendamentoModel {
	
	protected String id_agendamento;
	protected String data_agendamento;
	protected String endereço_agendamento;
	protected String nome_agendamento;
	protected String hora_agendamento;
	
	public AgendamentoModel() {
		super();
	}

	public AgendamentoModel(String id_agendamento, String data_agendamento, String endereço_agendamento,
			String nome_agendamento, String hora_agendamento) {
		super();
		this.id_agendamento = id_agendamento;
		this.data_agendamento = data_agendamento;
		this.endereço_agendamento = endereço_agendamento;
		this.nome_agendamento = nome_agendamento;
		this.hora_agendamento = hora_agendamento;
	}

	public String getId_agendamento() {
		return id_agendamento;
	}

	public void setId_agendamento(String id_agendamento) {
		this.id_agendamento = id_agendamento;
	}

	public String getData_agendamento() {
		return data_agendamento;
	}

	public void setData_agendamento(String data_agendamento) {
		this.data_agendamento = data_agendamento;
	}

	public String getEndereço_agendamento() {
		return endereço_agendamento;
	}

	public void setEndereço_agendamento(String endereço_agendamento) {
		this.endereço_agendamento = endereço_agendamento;
	}

	public String getNome_agendamento() {
		return nome_agendamento;
	}

	public void setNome_agendamento(String nome_agendamento) {
		this.nome_agendamento = nome_agendamento;
	}

	public String getHora_agendamento() {
		return hora_agendamento;
	}

	public void setHora_agendamento(String hora_agendamento) {
		this.hora_agendamento = hora_agendamento;
	}

	@Override
	public String toString() {
		return "AgendamentoModel [id_agendamento=" + id_agendamento + ", data_agendamento=" + data_agendamento
				+ ", endereço_agendamento=" + endereço_agendamento + ", nome_agendamento=" + nome_agendamento
				+ ", hora_agendamento=" + hora_agendamento + "]";
	}
	
	

}
