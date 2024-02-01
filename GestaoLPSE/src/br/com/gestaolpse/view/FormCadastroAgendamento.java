package br.com.gestaolpse.view;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import br.com.gestaolpse.dao.AgendamentoDao;
import br.com.gestaolpse.dao.OrcamentoDao;
import br.com.gestaolpse.model.AgendamentoModel;
import br.com.gestaolpse.model.modeloTabelaAgendamento;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.event.ActionEvent;

public class FormCadastroAgendamento extends JFrame {
	
	
	private ArrayList<AgendamentoModel> agendamentos;
	
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtEndereço;
	private JTextField txtData;
	private JTextField txtHora;
	private JTable table2;
	
	private JButton btnSair;
	private JButton btnAlterar;
	private JButton btnSalvar;
	private JButton btnExcluir;
	private JButton btnNovo;
	private JButton btnlimpar;
	private JButton btnCancelar;
	
	private AgendamentoDao dao;
	private AgendamentoModel obj;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCadastroAgendamento frame = new FormCadastroAgendamento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormCadastroAgendamento() {
		
		AgendamentoDao dao = new AgendamentoDao();
		try {
			agendamentos = dao.listarAgendamento();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1054, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormCadastroCliente.class.getResource("/br/com/gestaolpse/icons/logoos.png")));
    	setTitle("Gestão LPSE - Agendamento");
    	
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(50, 87, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Endereço:");
		lblNewLabel_1.setBounds(34, 130, 118, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Data:");
		lblNewLabel_2.setBounds(50, 175, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Hora:");
		lblNewLabel_3.setBounds(50, 215, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		txtNome = new JTextField();
		txtNome.setBounds(115, 84, 331, 20);
		txtNome.setEnabled(false);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtEndereço = new JTextField();
		txtEndereço.setBounds(115, 127, 331, 20);
		txtEndereço.setEnabled(false);
		contentPane.add(txtEndereço);
		txtEndereço.setColumns(10);
		
		txtData = new JTextField();
		txtData.setBounds(115, 172, 331, 20);
		txtData.setEnabled(false);
		contentPane.add(txtData);
		txtData.setColumns(10);
		
		txtHora = new JTextField();
		txtHora.setText("");
		txtHora.setBounds(115, 212, 331, 20);
		txtHora.setEnabled(false);
		contentPane.add(txtHora);
		txtHora.setColumns(10);
		
		
		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setEnabled(true);
        		txtData.setEnabled(true);
        		txtEndereço.setEnabled(true);
        		txtHora.setEnabled(true);
        		btnNovo.setEnabled(false);
        		btnSalvar.setEnabled(true);
        		btnAlterar.setEnabled(false);
        		btnExcluir.setEnabled(false);
        		btnCancelar.setEnabled(true);
			}
		});
		btnNovo.setIcon(new ImageIcon(FormCadastroAgendamento.class.getResource("/br/com/gestaolpse/icons/adicionar24px.png")));
		btnNovo.setBounds(50, 281, 118, 33);
		contentPane.add(btnNovo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(502, 80, 505, 297);
		contentPane.add(scrollPane);
		
		modeloTabelaAgendamento ModeloTabelaAgendamento = new modeloTabelaAgendamento(agendamentos);
		
		table2 = new JTable();
		table2.setModel(ModeloTabelaAgendamento);
		table2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linhaSelecionada = table2.getSelectedRow();
				
				if(linhaSelecionada >= 0) {
					modeloTabelaAgendamento modelo = (modeloTabelaAgendamento) table2.getModel();
					
					AgendamentoModel agendamentoSelecionado = modelo.getAgendamento(linhaSelecionada);
					
					preencherCampos(agendamentoSelecionado);
					
					btnNovo.setEnabled(false);
					btnSalvar.setEnabled(false);
					btnAlterar.setEnabled(true);
					btnCancelar.setEnabled(true);
					btnExcluir.setEnabled(true);
					
					liberaCampos();

				}
		
			}
		});
		scrollPane.setViewportView(table2);
		
	
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgendamentoDao dao = new AgendamentoDao();
				dao.cadastrarAgendamento(new AgendamentoModel(null,txtData.getText(), txtEndereço.getText(), txtNome.getText(),
						txtHora.getText()));
				
				btnNovo.setEnabled(true);
				btnSalvar.setEnabled(false);
				btnAlterar.setEnabled(false);
				btnCancelar.setEnabled(false);
				btnExcluir.setEnabled(false);
				
				try {
		            // Atualiza a lista de clientes
		            agendamentos = dao.listarAgendamento();

		            // Atualiza o modelo da tabela com os novos dados
		            ModeloTabelaAgendamento.setModelo(agendamentos);

		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
				
				limparCampos();
			}
		});
		btnSalvar.setIcon(new ImageIcon(FormCadastroAgendamento.class.getResource("/br/com/gestaolpse/icons/salvar24px.png")));
		btnSalvar.setBounds(212, 281, 109, 33);
		contentPane.add(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNovo.setEnabled(true);
				btnSalvar.setEnabled(false);
				btnAlterar.setEnabled(false);
				btnCancelar.setEnabled(false);
				btnExcluir.setEnabled(false);
				
				limparCampos();
			}
		});
		btnCancelar.setIcon(new ImageIcon(FormCadastroAgendamento.class.getResource("/br/com/gestaolpse/icons/cancelar24px.png")));
		btnCancelar.setBounds(351, 281, 118, 33);
		contentPane.add(btnCancelar);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Fechar a tela atual
		        dispose();
		        
		        try {
		            // Atualiza a lista de clientes
		            agendamentos = dao.listarAgendamento();

		            // Atualiza o modelo da tabela com os novos dados
		            ModeloTabelaAgendamento.setModelo(agendamentos);

		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
			}
		});
		btnSair.setIcon(new ImageIcon(FormCadastroAgendamento.class.getResource("/br/com/gestaolpse/icons/voltar24px.png")));
		btnSair.setBounds(50, 344, 118, 33);
		contentPane.add(btnSair);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(btnExcluir, "Deseja Excluir Agendamento? ")==JOptionPane.YES_NO_OPTION) {  
					AgendamentoDao dao = new AgendamentoDao();
					
					int linhaSelecionada = table2.getSelectedRow();
	
					if (linhaSelecionada >= 0) {
			            modeloTabelaAgendamento modelo = (modeloTabelaAgendamento) table2.getModel();
			            // Obtém o cliente selecionado
			            AgendamentoModel agendamentoSelecionado = modelo.getAgendamento(linhaSelecionada);
	
			            dao.excluirAgendamento(agendamentoSelecionado.getId_agendamento());;
			            
			            btnNovo.setEnabled(true);
						btnSalvar.setEnabled(false);
						btnAlterar.setEnabled(false);
						btnCancelar.setEnabled(false);
						btnExcluir.setEnabled(false);
			            
						try {
				            // Atualiza a lista de clientes
				            agendamentos = dao.listarAgendamento();

				            // Atualiza o modelo da tabela com os novos dados
				            ModeloTabelaAgendamento.setModelo(agendamentos);

				        } catch (Exception ex) {
				            ex.printStackTrace();
				        }
						 
						limparCampos();
					}
				}
			}
		});
		btnExcluir.setIcon(new ImageIcon(FormCadastroAgendamento.class.getResource("/br/com/gestaolpse/icons/lixeira24px.png")));
		btnExcluir.setBounds(212, 344, 109, 33);
		contentPane.add(btnExcluir);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setEnabled(false);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgendamentoDao dao = new AgendamentoDao();
				AgendamentoModel agendamento = new AgendamentoModel(null,txtData.getText(), txtEndereço.getText(), txtNome.getText(),
						txtHora.getText());
				
				int linhaSelecionada = table2.getSelectedRow();

				if (linhaSelecionada >= 0) {
		            modeloTabelaAgendamento modelo = (modeloTabelaAgendamento) table2.getModel();

		            // Obtém o cliente selecionado
		            AgendamentoModel agendamentoSelecionado = modelo.getAgendamento(linhaSelecionada);
		            
		            dao.alterarAgendamento(agendamentoSelecionado.getId_agendamento(), agendamento);
		            
		            btnNovo.setEnabled(true);
					btnSalvar.setEnabled(false);
					btnAlterar.setEnabled(false);
					btnCancelar.setEnabled(false);
					btnExcluir.setEnabled(false);					
					try {
			            // Atualiza a lista de clientes
			            agendamentos = dao.listarAgendamento();

			            // Atualiza o modelo da tabela com os novos dados
			            ModeloTabelaAgendamento.setModelo(agendamentos);

			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
					 
					limparCampos();
		            
		        } 
			}
		});
		btnAlterar.setIcon(new ImageIcon(FormCadastroAgendamento.class.getResource("/br/com/gestaolpse/icons/editar24px.png")));
		btnAlterar.setBounds(351, 344, 118, 33);
		contentPane.add(btnAlterar);
		
		
	}
	
	//MEtodo para obter a inha selecionada Do cliente
		protected Object obterClienteDaLinha(Locale linhaSelecionada) {
			return null;
		
		}
		//Fim metodo obterClienteDaLinha

		//Metodo que preenche os campos da tela 
		private AgendamentoModel preencherCampos(AgendamentoModel agendamentoSelecionado) {
			txtData.setText(agendamentoSelecionado.getData_agendamento());
			txtEndereço.setText(agendamentoSelecionado.getEndereço_agendamento());
			txtHora.setText(agendamentoSelecionado.getHora_agendamento());
			txtNome.setText(agendamentoSelecionado.getNome_agendamento());
				
			return agendamentoSelecionado;
		}//Fim preencheCampos
		
		//MEtodo para  Bloquear Os campos
		public void bloquearCampos() {
	    	txtData.setEnabled(false);
			txtEndereço.setEnabled(false);
			txtHora.setEnabled(false);
			txtNome.setEnabled(false);
			btnNovo.setEnabled(true);
			btnSalvar.setEnabled(false);
			btnExcluir.setEnabled(false);
			btnAlterar.setEnabled(false);
	    }//Fim Bloqueia Campos
		
		
		private void liberaCampos() {
			txtData.setEnabled(true);
			txtEndereço.setEnabled(true);
			txtHora.setEnabled(true);
			txtNome.setEnabled(true);
		}
		
		//Metodo para limpar os campos
		 private void limparCampos() {
		        txtData.setText("");
		        txtEndereço.setText("");
		        txtHora.setText("");
		        txtNome.setText("");
		 }//Fim Metodo limpaCampos
}
