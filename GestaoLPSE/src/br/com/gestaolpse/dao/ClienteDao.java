package br.com.gestaolpse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.gestaolpse.controller.Conexao;
import br.com.gestaolpse.model.ClienteModel;

public class ClienteDao {

	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	private static String DRIVER = "org.sqlite.jdbc";
	private static String BD = "jdbc:sqlite:resources/bdcgestaolpse.db";
	
	
	private static String CADASTRAR_CLIENTE = "INSERT INTO CLIENTE "
			+ "(id_cliente, nome_cliente, telefone_cliente, cpf_cliente, endereco_cliente)"
			+ "VALUES (NULL, ?, ?, ?, ?)";
	
	private static String CONSULTAR_CLIENTE = "SELECT * FROM CLIENTE "
			 + "WHERE id_cliente = ? ";
	
	private static String ALTERAR_CLIENTE = "UPDATE CLIENTE "
	        + "SET nome_cliente = ?, telefone_cliente = ?, cpf_cliente = ?, endereco_cliente = ? "
	        + "WHERE id_cliente = ? ";
	
	private static String EXCLUIR_CLIENTE = "DELETE FROM CLIENTE "
			+ "WHERE id_cliente = ? ";
	
	private static String LISTAR_CLIENTE = "SELECT * FROM CLIENTE "
			+ "WHERE 1=1 ";
	
	public ClienteDao() {
		
	}
	
	public static boolean cadastrarCliente(ClienteModel cliente) {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		String query = CADASTRAR_CLIENTE;
		try {
			preparedStatement = connection.prepareStatement(query);
			
			int i = 1;
			
			//id_cliente, nome_cliente, telefone_cliente, cpf_cliente, endereco_cliente
			preparedStatement.setString(i++, cliente.getNome_cliente());
			preparedStatement.setString(i++, cliente.getTelefone_cliente());
			preparedStatement.setString(i++, cliente.getCpf_cliente());
			preparedStatement.setString(i++, cliente.getEndereco_cliente());
			
			preparedStatement.execute();
			connection.commit();
			
			JOptionPane.showMessageDialog(null, "Cliente cadastrado com Sucesso!!!");
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
		return false;
	}//fecha metodo cadastrarCliente
	
	
	public ClienteModel consultarCliente(String id_cliente) throws Exception {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		ClienteModel cliente = null;
		
		String query = CONSULTAR_CLIENTE;
		try {
			preparedStatement = connection.prepareStatement(query);
			
			int i = 1;
			
			// nome_cliente
			preparedStatement.setString(i++, id_cliente);
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				cliente = new ClienteModel(resultSet.getString("id_cliente"),
										   resultSet.getString("nome_cliente"),
										   resultSet.getString("telefone_cliente"),
										   resultSet.getString("cpf_cliente"),
										   resultSet.getString("endereco_cliente"));
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
		if(cliente == null) {
			JOptionPane.showMessageDialog(null, "Cliente não localizado!!!", "", JOptionPane.WARNING_MESSAGE);
			throw new Exception("Cliente não localizado!!!");
		}
		return cliente;
	}//fecha metodo consultarCliente
	
	
	public void alterarCliente(String id_cliente, ClienteModel cliente) {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		String query = ALTERAR_CLIENTE;
		try {
			preparedStatement = connection.prepareStatement(query);
			
			int i = 1;
			
			//id_cliente, nome_cliente, telefone_cliente, cpf_cliente, endereco_cliente
			preparedStatement.setString(i++, cliente.getNome_cliente());
			preparedStatement.setString(i++, cliente.getTelefone_cliente());
			preparedStatement.setString(i++, cliente.getCpf_cliente());
			preparedStatement.setString(i++, cliente.getEndereco_cliente());
			preparedStatement.setString(i++, id_cliente);
			
			preparedStatement.execute();
			connection.commit();
			
			JOptionPane.showMessageDialog(null, "Cliente alterado com Sucesso!!!");
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
	}//fecha metodo alterarCliente
	
	
	
	public void excluirCliente(String id_cliente) {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		String query = EXCLUIR_CLIENTE;
		try {
			preparedStatement = connection.prepareStatement(query);
			
			int i = 1;
			
			int idCliente = Integer.parseInt(id_cliente);
			
			preparedStatement.setString(i++, id_cliente);
			
			preparedStatement.execute();
			connection.commit();
			
			JOptionPane.showMessageDialog(null, "Cliente excluido com Sucesso!!!");
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
	}//fecha metodo excluirCliente
	
	
	
	
	public ArrayList <ClienteModel>listarCliente() throws Exception {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		ArrayList<ClienteModel> clientes = new ArrayList<>();
		
		
		String query = LISTAR_CLIENTE;
		try {
			preparedStatement = connection.prepareStatement(query);

			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				clientes.add(new ClienteModel(resultSet.getString("id_cliente"),
											  resultSet.getString("nome_cliente"),
											  resultSet.getString("telefone_cliente"),
											  resultSet.getString("cpf_cliente"),
											  resultSet.getString("endereco_cliente")));
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
		if(clientes.size() < 0) {
			JOptionPane.showMessageDialog(null, "Não há clientes cadastrados!!!", "", JOptionPane.WARNING_MESSAGE);
			throw new Exception("Não há clientes cadastrados!!!");
		}
		return clientes;
	}//fecha metodo listarCliente
	
	
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

	public void atualizarCliente(ClienteModel clienteSelecionado) {
		// TODO Auto-generated method stub
		
	}

	public void excluirCliente(ClienteModel clienteSelecionado) {
		// TODO Auto-generated method stub
		
	}


	
}//fecha classe ClienteDao
