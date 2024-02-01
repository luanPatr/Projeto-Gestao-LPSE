package br.com.gestaolpse.model;



//import br.com.gestaolpse.view.FormCadastroOrcamento;

public class OrcamentoModel {

	protected String id_orcamento;
	protected String status_orcamento;
	protected String quantidade_orcamento;
	protected String valor_orcamento;
	protected String descricaoServ_orcamento;
	protected String tipoServ_orcamento;
	protected String data_orcamento;
	
	public OrcamentoModel() {
		
	}

	public OrcamentoModel(String id_orcamento, String status_orcamento, String quantidade_orcamento,
			String valor_orcamento, String descricaoServ_orcamento, String tipoServ_orcamento, String data_orcamento) {
		super();
		this.id_orcamento = id_orcamento;
		this.status_orcamento = status_orcamento;
		this.quantidade_orcamento = quantidade_orcamento;
		this.valor_orcamento = valor_orcamento;
		this.descricaoServ_orcamento = descricaoServ_orcamento;
		this.tipoServ_orcamento = tipoServ_orcamento;
		this.data_orcamento = data_orcamento;
	}

	public String getId_orcamento() {
		return id_orcamento;
	}

	public void setId_orcamento(String id_orcamento) {
		this.id_orcamento = id_orcamento;
	}

	public String getStatus_orcamento() {
		return status_orcamento;
	}

	public void setStatus_orcamento(String status_orcamento) {
		this.status_orcamento = status_orcamento;
	}

	public String getQuantidade_orcamento() {
		return quantidade_orcamento;
	}

	public void setQuantidade_orcamento(String quantidade_orcamento) {
		this.quantidade_orcamento = quantidade_orcamento;
	}

	public String getValor_orcamento() {
		return valor_orcamento;
	}

	public void setValor_orcamento(String valor_orcamento) {
		this.valor_orcamento = valor_orcamento;
	}

	public String getDescricaoServ_orcamento() {
		return descricaoServ_orcamento;
	}

	public void setDescricaoServ_orcamento(String descricaoServ_orcamento) {
		this.descricaoServ_orcamento = descricaoServ_orcamento;
	}

	public String getTipoServ_orcamento() {
		return tipoServ_orcamento;
	}

	public void setTipoServ_orcamento(String tipoServ_orcamento) {
		this.tipoServ_orcamento = tipoServ_orcamento;
	}

	public String getData_orcamento() {
		return data_orcamento;
	}

	public void setData_orcamento(String data_orcamento) {
		this.data_orcamento = data_orcamento;
	}

	@Override
	public String toString() {
		return "OrcamentoModel [id_orcamento=" + id_orcamento + ", status_orcamento=" + status_orcamento
				+ ", quantidade_orcamento=" + quantidade_orcamento + ", valor_orcamento=" + valor_orcamento
				+ ", descricaoServ_orcamento=" + descricaoServ_orcamento + ", tipoServ_orcamento=" + tipoServ_orcamento
				+ ", data_orcamento=" + data_orcamento + "]";
	}

	
}
