package br.com.gestaolpse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.gestaolpse.controller.Conexao;
import br.com.gestaolpse.model.AgendamentoModel;
import br.com.gestaolpse.model.ClienteModel;
import br.com.gestaolpse.model.OrcamentoModel;

public class AgendamentoDao {
	
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	private static String DRIVER = "org.sqlite.jdbc";
	private static String BD = "jdbc:sqlite:resources/bdcgestaolpse.db";
	
	private static String CADASTRAR_AGENDAMENTO = "INSERT INTO AGENDAMENTO "
			+ "(id_agendamento, data_agendamento, endereço_agendamento, nome_agendamento, hora_agendamento)"
			+ "VALUES (NULL, ?, ?, ?, ?)";
	
	private static String CONSULTAR_AGENDAMENTO = "SELECT * FROM AGENDAMENTO "
			 + "WHERE id_agendamento = ? ";
	
	private static String ALTERAR_AGENDAMENTO = "UPDATE AGENDAMENTO "
	        + "SET data_agendamento = ?, endereço_agendamento = ?, nome_agendamento = ?, hora_agendamento = ? "
	        + "WHERE id_agendamento = ? ";
	
	private static String EXCLUIR_AGENDAMENTO = "DELETE FROM AGENDAMENTO "
			+ "WHERE id_agendamento = ? ";
	
	private static String LISTAR_AGENDAMENTO = "SELECT * FROM AGENDAMENTO "
			+ "WHERE 1=1 ";
	
	public AgendamentoDao() {
		
	}
	
	public static boolean cadastrarAgendamento(AgendamentoModel agendamento) {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		String query = CADASTRAR_AGENDAMENTO;
		try {
			preparedStatement = connection.prepareStatement(query);
			
			int i = 1;
			
			//id_agendamento, data_agendamento, endereço_agendamento, nome_agendamento, hora_agendamento
			preparedStatement.setString(i++, agendamento.getData_agendamento());
			preparedStatement.setString(i++, agendamento.getEndereço_agendamento());
			preparedStatement.setString(i++, agendamento.getNome_agendamento());
			preparedStatement.setString(i++, agendamento.getHora_agendamento());
			
			preparedStatement.execute();
			connection.commit();
			
			JOptionPane.showMessageDialog(null, "Agendamento cadastrado com Sucesso!!!");
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
		return false;
	}//fecha metodo cadastrarAgendamento
	
	
	public AgendamentoModel consultarAgendamento(String id_agendamento) throws Exception {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		AgendamentoModel agendamento = null;
		
		String query = CONSULTAR_AGENDAMENTO;
		try {
			preparedStatement = connection.prepareStatement(query);
			
			int i = 1;
			
			// id_agendamento
			preparedStatement.setString(i++, id_agendamento);
			
			resultSet = preparedStatement.executeQuery();
			
			//id_agendamento, data_agendamento, endereço_agendamento, nome_agendamento, hora_agendamento
			while (resultSet.next()) {
				agendamento = new AgendamentoModel(resultSet.getString("id_agendamento"),
										   resultSet.getString("data_agendamento"),
										   resultSet.getString("endereço_agendamento"),
										   resultSet.getString("nome_agendamento"),
										   resultSet.getString("hora_agendamento"));
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
		if(agendamento == null) {
			JOptionPane.showMessageDialog(null, "Agendamento não localizado!!!", "", JOptionPane.WARNING_MESSAGE);
			throw new Exception("Agendamento não localizado!!!");
		}
		return agendamento;
	}//fecha metodo consultarAgendamento
	
	
	public void alterarAgendamento (String id_agendamento, AgendamentoModel agendamento) {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		String query = ALTERAR_AGENDAMENTO;
		try {
			preparedStatement = connection.prepareStatement(query);
			
			int i = 1;
			
			//id_agendamento, data_agendamento, endereço_agendamento, nome_agendamento, hora_agendamento
			preparedStatement.setString(i++, agendamento.getData_agendamento());
			preparedStatement.setString(i++, agendamento.getEndereço_agendamento());
			preparedStatement.setString(i++, agendamento.getNome_agendamento());
			preparedStatement.setString(i++, agendamento.getHora_agendamento());
			preparedStatement.setString(i++, id_agendamento);
			
			preparedStatement.execute();
			connection.commit();
			
			JOptionPane.showMessageDialog(null, "Agendamento alterado com Sucesso!!!");
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
	}//fecha metodo alterarAgendamento
	
	
	public void excluirAgendamento(String id_agendamento) {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		String query = EXCLUIR_AGENDAMENTO;
		try {
			preparedStatement = connection.prepareStatement(query);
			
			int i = 1;
			
			preparedStatement.setString(i++, id_agendamento);
			
			preparedStatement.execute();
			connection.commit();
			
			JOptionPane.showMessageDialog(null, "Agendamento excluido com Sucesso!!!");
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
	}//fecha metodo excluirAgendamento
	
	public ArrayList <AgendamentoModel>listarAgendamento() throws Exception {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		ArrayList<AgendamentoModel> agendamento = new ArrayList<>();
		
		
		String query = LISTAR_AGENDAMENTO;
		try {
			preparedStatement = connection.prepareStatement(query);

			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				agendamento.add(new AgendamentoModel(resultSet.getString("id_agendamento"),
						 							 resultSet.getString("data_agendamento"),
						 							 resultSet.getString("endereço_agendamento"),
						 							 resultSet.getString("nome_agendamento"),
						 							 resultSet.getString("hora_agendamento")));
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
		if(agendamento.size() < 0) {
			JOptionPane.showMessageDialog(null, "Não há agendamento cadastrados!!!", "", JOptionPane.WARNING_MESSAGE);
			throw new Exception("Não há agendamento cadastrados!!!");
		}
		return agendamento;
	}//fecha metodo listarOrcamento
	
	
	private static void fecharConexao() {
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
