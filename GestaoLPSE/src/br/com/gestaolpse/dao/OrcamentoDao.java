package br.com.gestaolpse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import br.com.gestaolpse.controller.Conexao;
import br.com.gestaolpse.model.OrcamentoModel;

public class OrcamentoDao {

	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	private static String DRIVER = "org.sqlite.jdbc";
	private static String BD = "jdbc:sqlite:resources/bdcgestaolpse.db";
	
	
	private static String CADASTRAR_ORCAMENTO = "INSERT INTO ORCAMENTO "
	        + "(id_orcamento, status_orcamento, quantidade_orcamento, valor_orcamento, descricaoServ_orcamento, tipoServ_orcamento, data_orcamento)"
	        + " VALUES (NULL, ?, ?, ?, ?, ?, ?)";
	
	private static String CONSULTAR_ORCAMENTO = "SELECT * FROM ORCAMENTO "
			 + "WHERE id_orcamento = ? ";
	
	private static String ALTERAR_ORCAMENTO = "UPDATE ORCAMENTO "
		       + "SET status_orcamento = ?, quantidade_orcamento = ?, valor_orcamento = ?, "
		       + "descricaoServ_orcamento = ?, tipoServ_orcamento = ?, data_orcamento = ? "
		       + "WHERE id_orcamento = ? ";

	
	private static String EXCLUIR_ORCAMENTO = "DELETE FROM ORCAMENTO "
			+ "WHERE id_orcamento = ? ";
	
	private static String LISTAR_ORCAMENTO = "SELECT * FROM ORCAMENTO "
	        + "WHERE 1=1 ";

	
	public OrcamentoDao() {
		
	}
	
	public void cadastrarOrcamento(OrcamentoModel orcamento) {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		String query = CADASTRAR_ORCAMENTO;
		try {
			preparedStatement = connection.prepareStatement(query);
			
			int i = 1;
			
			//String id_orcamento, String status_orcamento, String quantidade_orcamento, String valor_orcamento, String descricaoServ_orcamento, String tipoServ_orcamento, Date data_orcamento
			preparedStatement.setString(i++, orcamento.getStatus_orcamento());
			preparedStatement.setString(i++, orcamento.getQuantidade_orcamento());
			preparedStatement.setString(i++, orcamento.getValor_orcamento());
			preparedStatement.setString(i++, orcamento.getDescricaoServ_orcamento());
			preparedStatement.setString(i++, orcamento.getTipoServ_orcamento());
			preparedStatement.setString(i++, orcamento.getData_orcamento());


			preparedStatement.execute();
			connection.commit();
			
			JOptionPane.showMessageDialog(null, "Orçamento cadastrado com Sucesso!!!");
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
	}//fecha metodo cadastrarOrcamento
	
	
	
	public OrcamentoModel consultarOrcamento(String id_orcamento) throws Exception {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		OrcamentoModel orcamento = null;
		
		String query = CONSULTAR_ORCAMENTO;
		try {
			preparedStatement = connection.prepareStatement(query);
			
			int i = 1;
			
			// id_orcamento
			preparedStatement.setString(i++, id_orcamento);
			
			resultSet = preparedStatement.executeQuery();
			
			//String id_orcamento, String status_orcamento, String quantidade_orcamento, String valor_orcamento, String descricaoServ_orcamento, String tipoServ_orcamento, Date data_orcamento
			while (resultSet.next()) {
				orcamento = new OrcamentoModel(resultSet.getString("id_orcamento"),
										   resultSet.getString("status_orcamento"),
										   resultSet.getString("quantidade_orcamento"),
										   resultSet.getString("valor_orcamento"),
										   resultSet.getString("descricaoServ_orcamento"),
										   resultSet.getString("tipoServ_orcamento"),
										   resultSet.getString("data_orcamento"));
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
		if(orcamento == null) {
			JOptionPane.showMessageDialog(null, "Orcamento não localizado!!!", "", JOptionPane.WARNING_MESSAGE);
			throw new Exception("Orcamento não localizado!!!");
		}
		return orcamento;
	}//fecha metodo consultarOrcamento
	
	
	
	public void alterarOrcamento(String id_orcamento, OrcamentoModel orcamento) {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		String query = ALTERAR_ORCAMENTO;
		try {
			preparedStatement = connection.prepareStatement(query);
			
			int i = 1;
			
			//String id_orcamento, String status_orcamento, String quantidade_orcamento, String valor_orcamento, String descricaoServ_orcamento, String tipoServ_orcamento, Date data_orcamento
			preparedStatement.setString(i++, orcamento.getStatus_orcamento());
			preparedStatement.setString(i++, orcamento.getQuantidade_orcamento());
			preparedStatement.setString(i++, orcamento.getValor_orcamento());
			preparedStatement.setString(i++, orcamento.getDescricaoServ_orcamento());
			preparedStatement.setString(i++, orcamento.getTipoServ_orcamento());
			preparedStatement.setString(i++, orcamento.getData_orcamento());
			preparedStatement.setString(i++, id_orcamento);
			
			preparedStatement.execute();
			connection.commit();
			
			JOptionPane.showMessageDialog(null, "Orcamento alterado com Sucesso!!!");
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
	}//fecha metodo alterarOrcamento

	
	
	public void excluirOrcamento(String id_orcamento) {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		String query = EXCLUIR_ORCAMENTO;
		try {
			preparedStatement = connection.prepareStatement(query);
			
			int i = 1;
			
			preparedStatement.setString(i++, id_orcamento);
			
			preparedStatement.execute();
			connection.commit();
			
			JOptionPane.showMessageDialog(null, "Orcamento excluido com Sucesso!!!");
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
	}//fecha metodo excluirOrcamento

	
	
	public ArrayList <OrcamentoModel>listarOrcamento() throws Exception {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		ArrayList<OrcamentoModel> orcamentos = new ArrayList<>();
		
		
		String query = LISTAR_ORCAMENTO;
		try {
			preparedStatement = connection.prepareStatement(query);

			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				orcamentos.add(new OrcamentoModel(resultSet.getString("id_orcamento"),
												   resultSet.getString("status_orcamento"),
												   resultSet.getString("quantidade_orcamento"),
												   resultSet.getString("valor_orcamento"),
												   resultSet.getString("descricaoServ_orcamento"),
												   resultSet.getString("tipoServ_orcamento"),
												   resultSet.getString("data_orcamento")));
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
		if(orcamentos.size() < 0) {
			JOptionPane.showMessageDialog(null, "Não há orçementos cadastrados!!!", "", JOptionPane.WARNING_MESSAGE);
			throw new Exception("Não há orçamentos cadastrados!!!");
		}
		return orcamentos;
	}//fecha metodo listarOrcamento
	
	
	
	private void fecharConexao() {
		try {
			if(resultSet!=null) {
				resultSet.close();
			}
			if(preparedStatement!=null) {
				preparedStatement.close();
			}
			Conexao.getInstancia().fecharConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}//fecha metodo fecharConexão

}
