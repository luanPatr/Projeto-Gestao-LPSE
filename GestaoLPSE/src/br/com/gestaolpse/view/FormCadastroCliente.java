package br.com.gestaolpse.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import br.com.gestaolpse.dao.ClienteDao;
import br.com.gestaolpse.model.ClienteModel;
import br.com.gestaolpse.model.modeloTabela;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;


public class FormCadastroCliente extends JFrame {
	
	//protected static final ClienteModel clienteSelecionado = null;

	private ArrayList<ClienteModel> clientes;
	
	private JPanel contentPane;
	private JTextField txtNome_cli;
	private JTextField txtTelefone_cli;
	private JTextField txtCpf_cli;
	private JTextField txtEndereco_cli;
	
	
	private JButton btnSair;
	private JButton btnAlterar;
	private JButton btnSalvar;
	private JButton btnExcluir;
	private JButton btnNovo;
	private JButton btnlimpar;
	private JButton btnCancelar;
	
	private ClienteDao dao;
	private ClienteModel obj;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				abrirFormCadastroCliente();
			}
		});

	}

	public static void abrirFormCadastroCliente() {
		try {
			FormCadastroCliente frame = new FormCadastroCliente();

			frame.setLocationRelativeTo(null);
		
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public FormCadastroCliente() {
		ClienteDao dao = new ClienteDao();
		try {
			clientes = dao.listarCliente();
		} catch (Exception e1) {

			e1.printStackTrace();
		}
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormCadastroCliente.class.getResource("/br/com/gestaolpse/icons/logoos.png")));
    	setTitle("Gestão LPSE - Clientes");
    	
    	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1071, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNome_cli = new JTextField();
		txtNome_cli.setBounds(108, 43, 444, 20);
		txtNome_cli.setEnabled(false);
		contentPane.add(txtNome_cli);
		txtNome_cli.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(50, 46, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Telefone:");
		lblNewLabel_1.setBounds(23, 91, 98, 14);
		contentPane.add(lblNewLabel_1);
		
		txtTelefone_cli = new JTextField();
		txtTelefone_cli.setBounds(108, 88, 189, 20);
		txtTelefone_cli.setEnabled(false);
		contentPane.add(txtTelefone_cli);
		txtTelefone_cli.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("CPF:");
		lblNewLabel_2.setBounds(60, 131, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		txtCpf_cli = new JTextField();
		txtCpf_cli.setBounds(108, 128, 189, 20);
		txtCpf_cli.setEnabled(false);
		contentPane.add(txtCpf_cli);
		txtCpf_cli.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Endereço:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(23, 172, 73, 14);
		contentPane.add(lblNewLabel_3);
		
		txtEndereco_cli = new JTextField();
		txtEndereco_cli.setBounds(108, 169, 444, 20);
		txtEndereco_cli.setEnabled(false);
		contentPane.add(txtEndereco_cli);
		txtEndereco_cli.setColumns(10);
		
		//Começo Declaração btnSair
		btnSair = new JButton("Sair");
		btnSair.setIcon(new ImageIcon("C:\\Users\\Luan Souza\\Desktop\\Verssões projeto lp2\\GestaoLPSE_v3\\GestaoLPSE\\src\\br\\com\\gestaolpse\\icons\\voltar24px.png"));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        // Fechar a tela atual
		        dispose();
			}
		});
		btnSair.setBounds(463, 282, 89, 33);
		contentPane.add(btnSair);
		//fim btn Sair
		
		//Inicio Tabela Clientes
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(586, 24, 459, 271);
		contentPane.add(scrollPane);
		
		modeloTabela ModeloTabela = new modeloTabela(clientes);
		
		table = new JTable();
		table.setModel(ModeloTabela);
		//JTable jTable = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linhaSelecionada = table.getSelectedRow();
				
				if(linhaSelecionada >= 0) {
					modeloTabela modelo = (modeloTabela) table.getModel();
					
					ClienteModel clienteSelecionado = modelo.getCliente(linhaSelecionada);
					
					preencherCampos(clienteSelecionado);
					
					btnNovo.setEnabled(false);
					btnSalvar.setEnabled(false);
					btnAlterar.setEnabled(true);
					btnCancelar.setEnabled(true);
					btnExcluir.setEnabled(true);
					btnlimpar.setEnabled(true);
					
					liberaCampos();

				}
		
			}
		});
		scrollPane.setViewportView(table);
		//Fim TabelaClientes
		
		//Comeco btnExcluir
		btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon("C:\\Users\\Luan Souza\\Desktop\\Verssões projeto lp2\\GestaoLPSE_v3\\GestaoLPSE\\src\\br\\com\\gestaolpse\\icons\\lixeira24px.png"));
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(btnExcluir, "Deseja Excluir Cliente? ")==JOptionPane.YES_NO_OPTION) {  
					ClienteDao dao = new ClienteDao();
					
					int linhaSelecionada = table.getSelectedRow();
	
					if (linhaSelecionada >= 0) {
			            modeloTabela modelo = (modeloTabela) table.getModel();
			            // Obtém o cliente selecionado
			            ClienteModel clienteSelecionado = modelo.getCliente(linhaSelecionada);
	
			            dao.excluirCliente(clienteSelecionado.getId_cliente());;
			            
			            btnNovo.setEnabled(true);
						btnSalvar.setEnabled(false);
						btnAlterar.setEnabled(false);
						btnCancelar.setEnabled(false);
						btnExcluir.setEnabled(false);
						btnlimpar.setEnabled(false);
			            
						try {
				            // Atualiza a lista de clientes
				            clientes = dao.listarCliente();

				            // Atualiza o modelo da tabela com os novos dados
				            ModeloTabela.setModelo(clientes);

				        } catch (Exception ex) {
				            ex.printStackTrace();
				        }
						 
						limparCampos();
					}
				}
			}
		});
		btnExcluir.setBounds(229, 282, 107, 33);
		contentPane.add(btnExcluir);
		//Fim btnExcluir
		
		//Comeco btnAlterar
		btnAlterar = new JButton("Alterar");
		btnAlterar.setIcon(new ImageIcon("C:\\Users\\Luan Souza\\Desktop\\Verssões projeto lp2\\GestaoLPSE_v3\\GestaoLPSE\\src\\br\\com\\gestaolpse\\icons\\editar24px.png"));
		btnAlterar.setEnabled(false);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(btnAlterar, "Deseja Alterar Cliente? ")==JOptionPane.YES_NO_OPTION) {  
					ClienteDao dao = new ClienteDao();
					ClienteModel cliente = new ClienteModel(null,txtNome_cli.getText(), txtTelefone_cli.getText(), txtCpf_cli.getText(),
							txtEndereco_cli.getText());
					
					int linhaSelecionada = table.getSelectedRow();
	
					if (linhaSelecionada >= 0) {
			            modeloTabela modelo = (modeloTabela) table.getModel();
	
			            // Obtém o cliente selecionado
			            ClienteModel clienteSelecionado = modelo.getCliente(linhaSelecionada);
			            
			            dao.alterarCliente(clienteSelecionado.getId_cliente(), cliente);
			            
			            btnNovo.setEnabled(true);
						btnSalvar.setEnabled(false);
						btnAlterar.setEnabled(false);
						btnCancelar.setEnabled(false);
						btnExcluir.setEnabled(false);
						btnlimpar.setEnabled(false);
						
						try {
				            // Atualiza a lista de clientes
				            clientes = dao.listarCliente();

				            // Atualiza o modelo da tabela com os novos dados
				            ModeloTabela.setModelo(clientes);

				        } catch (Exception ex) {
				            ex.printStackTrace();
				        }
						 
						limparCampos();
			            
			        } 
				}
			}
		});
		btnAlterar.setBounds(294, 221, 107, 33);
		contentPane.add(btnAlterar);
		//Fim btnAlterar
		
		//Começo btnSalvar
		btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon("C:\\Users\\Luan Souza\\Desktop\\Verssões projeto lp2\\GestaoLPSE_v3\\GestaoLPSE\\src\\br\\com\\gestaolpse\\icons\\salvar24px.png"));
		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteDao dao = new ClienteDao();
				dao.cadastrarCliente(new ClienteModel(null,txtNome_cli.getText(), txtTelefone_cli.getText(), txtCpf_cli.getText(),
						txtEndereco_cli.getText()));
				
				btnNovo.setEnabled(true);
				btnSalvar.setEnabled(false);
				btnAlterar.setEnabled(false);
				btnCancelar.setEnabled(false);
				btnExcluir.setEnabled(false);
				btnlimpar.setEnabled(false);
				
				try {
		            // Atualiza a lista de clientes
		            clientes = dao.listarCliente();

		            // Atualiza o modelo da tabela com os novos dados
		            ModeloTabela.setModelo(clientes);

		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
				
				limparCampos();
				
			}
		});
		btnSalvar.setBounds(157, 221, 120, 33);
		contentPane.add(btnSalvar);
		//fim btnSalvar
		
		//Começo btnNovo
		btnNovo = new JButton("Novo");
		btnNovo.setIcon(new ImageIcon("C:\\Users\\Luan Souza\\Desktop\\Verssões projeto lp2\\GestaoLPSE_v3\\GestaoLPSE\\src\\br\\com\\gestaolpse\\icons\\adicionar24px.png"));
		btnNovo.setBounds(32, 221, 107, 33);
		contentPane.add(btnNovo);
		btnNovo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		txtNome_cli.setEnabled(true);
        		txtTelefone_cli.setEnabled(true);
        		txtCpf_cli.setEnabled(true);
        		txtEndereco_cli.setEnabled(true);
        		btnNovo.setEnabled(false);
        		btnSalvar.setEnabled(true);
        		btnAlterar.setEnabled(false);
        		btnExcluir.setEnabled(false);
        		btnCancelar.setEnabled(true);
        		btnlimpar.setEnabled(false);
        	}
		});
		//Fim btnNovo
		
		
		//Comeco btnLimpar
		btnlimpar = new JButton("Limpar");
		btnlimpar.setIcon(new ImageIcon("C:\\Users\\Luan Souza\\Desktop\\Verssões projeto lp2\\GestaoLPSE_v3\\GestaoLPSE\\src\\br\\com\\gestaolpse\\icons\\apagar24px.png"));
		btnlimpar.setEnabled(false);
		btnlimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnNovo.setEnabled(true);
				btnSalvar.setEnabled(false);
				btnAlterar.setEnabled(false);
				btnCancelar.setEnabled(false);
				btnExcluir.setEnabled(false);
				btnlimpar.setEnabled(false);
				
				limparCampos();
			}
		});
		btnlimpar.setBounds(346, 282, 107, 33);
		contentPane.add(btnlimpar);
		//Fim btnLimpar
		
		//Começo btnCancelar
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon("C:\\Users\\Luan Souza\\Desktop\\Verssões projeto lp2\\GestaoLPSE_v3\\GestaoLPSE\\src\\br\\com\\gestaolpse\\icons\\cancelar24px.png"));
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnNovo.setEnabled(true);
				btnSalvar.setEnabled(false);
				btnAlterar.setEnabled(false);
				btnCancelar.setEnabled(false);
				btnExcluir.setEnabled(false);
				btnlimpar.setEnabled(false);
				
				limparCampos();
			}
		});
		btnCancelar.setBounds(111, 282, 107, 33);
		contentPane.add(btnCancelar);
		//Fim btnCancelar		
	}
	
	//MEtodo para obter a inha selecionada Do cliente
	protected Object obterClienteDaLinha(Locale linhaSelecionada) {
		return null;
	
	}
	//Fim metodo obterClienteDaLinha

	//Metodo que preenche os campos da tela 
	private ClienteModel preencherCampos(ClienteModel clienteSelecionado) {
		txtNome_cli.setText(clienteSelecionado.getNome_cliente());
		txtTelefone_cli.setText(clienteSelecionado.getTelefone_cliente());
		txtCpf_cli.setText(clienteSelecionado.getCpf_cliente());
		txtEndereco_cli.setText(clienteSelecionado.getEndereco_cliente());
	
		return clienteSelecionado;
	}//Fim preencheCampos
	
	//MEtodo para  Bloquear Os campos
	public void bloquearCampos() {
    	txtNome_cli.setEnabled(false);
		txtTelefone_cli.setEnabled(false);
		txtCpf_cli.setEnabled(false);
		txtEndereco_cli.setEnabled(false);
		btnNovo.setEnabled(true);
		btnlimpar.setEnabled(false);
		btnSalvar.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnAlterar.setEnabled(false);
    }//Fim Bloqueia Campos
	
	
	private void liberaCampos() {
		txtNome_cli.setEnabled(true);
		txtTelefone_cli.setEnabled(true);
		txtCpf_cli.setEnabled(true);
		txtEndereco_cli.setEnabled(true);
	}
	
	//Metodo para limpar os campos
	 private void limparCampos() {
	        txtNome_cli.setText("");
	        txtTelefone_cli.setText("");
	        txtCpf_cli.setText("");
	        txtEndereco_cli.setText("");
	 }//Fim Metodo limpaCampos
}
