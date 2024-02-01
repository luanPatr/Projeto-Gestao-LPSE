package br.com.gestaolpse.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import br.com.gestaolpse.dao.OrcamentoDao;
import br.com.gestaolpse.model.ModeloTabelaOrcamento;
import br.com.gestaolpse.model.OrcamentoModel;
import br.com.gestaolpse.model.modeloTabela;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;

public class FormCadastroOrcamento extends JFrame {
	

	private JPanel contentPane;
	private JTextField txtStatus_orcamento;
	private JTextField txtData_orcamento;
	private JTextField txtQuantidade_orcamento;
	private JTextField txtValor_orcamento;
	private JTextField txtTipoServico_orcamento;
	private JTable table;
	
	private JButton btnSair;
	private JButton btnAlterar;
	private JButton btnSalvar;
	private JButton btnExcluir;
	private JButton btnCancelar;
	private JButton btnNovo;
	private JButton btnLimpar;
	//private JButton btnSalvarAlt;
	private ArrayList<OrcamentoModel> orcamentos;
	
	private OrcamentoDao dao;
	private OrcamentoModel obj;
	private JTextArea textAreaDescricao;
	
	private JTable table_1;
	private JButton btnAtualizar;

	//private TableModel modeloTabelaOrcamento;
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCadastroOrcamento frame = new FormCadastroOrcamento();
				
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
	public FormCadastroOrcamento() {
		
		OrcamentoDao dao = new OrcamentoDao();
		try {
			orcamentos = dao.listarOrcamento();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1151, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormCadastroCliente.class.getResource("/br/com/gestaolpse/icons/logoos.png")));
    	setTitle("Gestão LPSE - Orçamento");

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cliente:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(48, 27, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQuantidade.setBounds(10, 62, 84, 14);
		contentPane.add(lblQuantidade);
		
		JLabel lblValorTotal = new JLabel("Valor Total:");
		lblValorTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorTotal.setBounds(10, 95, 84, 14);
		contentPane.add(lblValorTotal);
		
		JLabel lblDescrio = new JLabel("Descrição:");
		lblDescrio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescrio.setBounds(10, 143, 78, 14);
		contentPane.add(lblDescrio);
		
		JLabel lblTipoServio = new JLabel("Tipo Serviço:");
		lblTipoServio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoServio.setBounds(301, 95, 72, 14);
		contentPane.add(lblTipoServio);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setHorizontalAlignment(SwingConstants.RIGHT);
		lblData.setBounds(295, 27, 78, 14);
		contentPane.add(lblData);
		
		txtStatus_orcamento = new JTextField();
		txtStatus_orcamento.setBounds(104, 24, 187, 20);
		txtStatus_orcamento.setEnabled(false);
		contentPane.add(txtStatus_orcamento);
		txtStatus_orcamento.setColumns(10);
		
		txtData_orcamento = new JTextField();
		txtData_orcamento.setBounds(383, 24, 274, 20);
		txtData_orcamento.setEnabled(false);
		contentPane.add(txtData_orcamento);
		txtData_orcamento.setColumns(10);
		
		txtQuantidade_orcamento = new JTextField();
		txtQuantidade_orcamento.setBounds(104, 59, 187, 20);
		txtQuantidade_orcamento.setEnabled(false);
		contentPane.add(txtQuantidade_orcamento);
		txtQuantidade_orcamento.setColumns(10);
		
		txtValor_orcamento = new JTextField();
		txtValor_orcamento.setBounds(104, 92, 187, 20);
		txtValor_orcamento.setEnabled(false);
		contentPane.add(txtValor_orcamento);
		txtValor_orcamento.setColumns(10);
		
		txtTipoServico_orcamento = new JTextField();
		txtTipoServico_orcamento.setBounds(383, 92, 274, 20);
		txtTipoServico_orcamento.setEnabled(false);
		contentPane.add(txtTipoServico_orcamento);
		txtTipoServico_orcamento.setColumns(10);
		
		
		btnSair = new JButton("Sair");
		btnSair.setIcon(new ImageIcon("C:\\Users\\Luan Souza\\Desktop\\Verssões projeto lp2\\GestaoLPSE_v3\\GestaoLPSE\\src\\br\\com\\gestaolpse\\icons\\voltar24px.png"));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        
		        // Fechar a tela atual
		        dispose();
			}
		});
		btnSair.setBounds(571, 347, 91, 36);
		contentPane.add(btnSair);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(683, 21, 437, 317);
		contentPane.add(scrollPane);
		
		ModeloTabelaOrcamento modeloTabelaOrcamento = new ModeloTabelaOrcamento(orcamentos);
		
		table_1 = new JTable();
		table_1.setModel(modeloTabelaOrcamento);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linhaSelecionada = table_1.getSelectedRow();
				
				if(linhaSelecionada >= 0) {
					ModeloTabelaOrcamento modelo = (ModeloTabelaOrcamento) table_1.getModel();
					
					OrcamentoModel orcamentoSelecionado = modelo.getOrcamento(linhaSelecionada);
					
					preencherCampos(orcamentoSelecionado);
					
					 btnAlterar.setEnabled(true);
				 	 btnSalvar.setEnabled(false);
				  	 btnExcluir.setEnabled(true);
				  	 btnCancelar.setEnabled(true);
				  	 btnNovo.setEnabled(false);
				  	 btnLimpar.setEnabled(true);
				  	 
				  	 liberaCampos();
				}
		
			}
		});
		scrollPane.setViewportView(table_1);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setIcon(new ImageIcon("C:\\Users\\Luan Souza\\Desktop\\Verssões projeto lp2\\GestaoLPSE_v3\\GestaoLPSE\\src\\br\\com\\gestaolpse\\icons\\editar24px.png"));
		btnAlterar.setEnabled(false);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(btnAlterar, "Deseja Alterar Orçamento? ")==JOptionPane.YES_NO_OPTION) {  
					OrcamentoDao dao = new OrcamentoDao();
					//textPaneDescricao_orcamento = new JTextPane();
					OrcamentoModel orcamento = new OrcamentoModel(null, txtStatus_orcamento.getText(), txtQuantidade_orcamento.getText(), txtValor_orcamento.getText(),
							textAreaDescricao.getText(), txtTipoServico_orcamento.getText(), txtData_orcamento.getText());
					
					int linhaSelecionada = table_1.getSelectedRow();
	
					if (linhaSelecionada >= 0) {
			            ModeloTabelaOrcamento modelo = (ModeloTabelaOrcamento) table_1.getModel();
	
			            // Obtém o cliente selecionado
			            OrcamentoModel orcamentoSelecionado = modelo.getOrcamento(linhaSelecionada);
			            
			            dao.alterarOrcamento(orcamentoSelecionado.getId_orcamento(), orcamento);
			            
			             btnAlterar.setEnabled(false);
					 	 btnSalvar.setEnabled(false);
					  	 btnExcluir.setEnabled(false);
					  	 btnCancelar.setEnabled(false);
					  	 btnNovo.setEnabled(true);
					  	 btnLimpar.setEnabled(false);
					  	 
					  	try {
				            // Atualiza a lista de clientes
				            orcamentos = dao.listarOrcamento();

				            // Atualiza o modelo da tabela com os novos dados
				            modeloTabelaOrcamento.setModelo(orcamentos);
				            
				        } catch (Exception ex) {
				            ex.printStackTrace();
				        }
					  	
					  	 limparCampos();
					  
			            
			            modelo.fireTableDataChanged();
			        } 
				}
			}
		});
		btnAlterar.setBounds(434, 347, 106, 36);
		contentPane.add(btnAlterar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon("C:\\Users\\Luan Souza\\Desktop\\Verssões projeto lp2\\GestaoLPSE_v3\\GestaoLPSE\\src\\br\\com\\gestaolpse\\icons\\lixeira24px.png"));
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(btnExcluir, "Deseja Excluir Orçamento? ")==JOptionPane.YES_NO_OPTION) {  
					OrcamentoDao dao = new OrcamentoDao();
					//textPaneDescricao_orcamento = new JTextPane();
					int linhaSelecionada = table_1.getSelectedRow();
	
					if (linhaSelecionada >= 0) {
			            ModeloTabelaOrcamento modelo = (ModeloTabelaOrcamento) table_1.getModel();
			            // Obtém o cliente selecionado
			            OrcamentoModel orcamentoSelecionado = modelo.getOrcamento(linhaSelecionada);
	
			            dao.excluirOrcamento(orcamentoSelecionado.getId_orcamento());
			            
			             btnAlterar.setEnabled(false);
					 	 btnSalvar.setEnabled(false);
					  	 btnExcluir.setEnabled(false);
					  	 btnCancelar.setEnabled(false);
					  	 btnNovo.setEnabled(true);
					  	 btnLimpar.setEnabled(false);
					  	 
					  	try {
				            // Atualiza a lista de clientes
				            orcamentos = dao.listarOrcamento();

				            // Atualiza o modelo da tabela com os novos dados
				            modeloTabelaOrcamento.setModelo(orcamentos);
				            
				        } catch (Exception ex) {
				            ex.printStackTrace();
				        }
					  	
					  	 limparCampos();
			            
			            modelo.fireTableDataChanged();
					}
				}
			}
		});
		btnExcluir.setBounds(324, 285, 106, 33);
		contentPane.add(btnExcluir);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon("C:\\Users\\Luan Souza\\Desktop\\Verssões projeto lp2\\GestaoLPSE_v3\\GestaoLPSE\\src\\br\\com\\gestaolpse\\icons\\salvar24px.png"));
		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrcamentoDao dao = new OrcamentoDao();
				//textPaneDescricao_orcamento = new JTextPane();
				//String id_orcamento, String status_orcamento, String quantidade_orcamento,
				//String valor_orcamento, String descricaoServ_orcamento, String tipoServ_orcamento, Date data_orcamento
				dao.cadastrarOrcamento(new OrcamentoModel( null, txtStatus_orcamento.getText(), txtQuantidade_orcamento.getText(), txtValor_orcamento.getText(),
										textAreaDescricao.getText(), txtTipoServico_orcamento.getText(), txtData_orcamento.getText()));
				
				 btnAlterar.setEnabled(false);
			 	 btnSalvar.setEnabled(false);
			  	 btnExcluir.setEnabled(false);
			  	 btnCancelar.setEnabled(false);
			  	 btnNovo.setEnabled(true);
			  	 btnLimpar.setEnabled(false);
			  	 
			  	try {
		            // Atualiza a lista de clientes
		            orcamentos = dao.listarOrcamento();

		            // Atualiza o modelo da tabela com os novos dados
		            modeloTabelaOrcamento.setModelo(orcamentos);
		            
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
			  	
			  	 limparCampos();
			  	 
			}
		});
		btnSalvar.setBounds(186, 285, 116, 33);
		contentPane.add(btnSalvar);
		
		btnNovo = new JButton("Novo");
		btnNovo.setIcon(new ImageIcon("C:\\Users\\Luan Souza\\Desktop\\Verssões projeto lp2\\GestaoLPSE_v3\\GestaoLPSE\\src\\br\\com\\gestaolpse\\icons\\adicionar24px.png"));
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtStatus_orcamento.setEnabled(true);
				txtQuantidade_orcamento.setEnabled(true);
				txtValor_orcamento.setEnabled(true);
				textAreaDescricao.setEnabled(true);
				txtData_orcamento.setEnabled(true);
				txtTipoServico_orcamento.setEnabled(true);

				 btnAlterar.setEnabled(false);
			 	 btnSalvar.setEnabled(true);
			  	 btnExcluir.setEnabled(false);
			  	 btnCancelar.setEnabled(true);
			  	 btnNovo.setEnabled(false);
			  	 btnLimpar.setEnabled(false);
			}
		});
		btnNovo.setBounds(48, 285, 117, 33);
		contentPane.add(btnNovo);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setIcon(new ImageIcon("C:\\Users\\Luan Souza\\Desktop\\Verssões projeto lp2\\GestaoLPSE_v3\\GestaoLPSE\\src\\br\\com\\gestaolpse\\icons\\apagar24px.png"));
		btnLimpar.setEnabled(false);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 btnAlterar.setEnabled(false);
			 	 btnSalvar.setEnabled(false);
			  	 btnExcluir.setEnabled(false);
			  	 btnCancelar.setEnabled(false);
			  	 btnNovo.setEnabled(true);
			  	 btnLimpar.setEnabled(false);
			  	 
				limparCampos();
			}
		});
		btnLimpar.setBounds(301, 347, 108, 36);
		contentPane.add(btnLimpar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon("C:\\Users\\Luan Souza\\Desktop\\Verssões projeto lp2\\GestaoLPSE_v3\\GestaoLPSE\\src\\br\\com\\gestaolpse\\icons\\cancelar24px.png"));
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 btnAlterar.setEnabled(false);
			 	 btnSalvar.setEnabled(false);
			  	 btnExcluir.setEnabled(false);
			  	 btnCancelar.setEnabled(false);
			  	 btnNovo.setEnabled(true);
			  	 btnLimpar.setEnabled(false);
			  	 
				limparCampos();
			}
		});
		btnCancelar.setBounds(164, 347, 106, 36);
		contentPane.add(btnCancelar);
		
		textAreaDescricao = new JTextArea();
		textAreaDescricao.setBounds(98, 138, 559, 135);
		textAreaDescricao.setEnabled(false);
		contentPane.add(textAreaDescricao);
		
	}
	
	protected Object obterOrcamentoDaLinha(Locale linhaSelecionada) {
		return null;
	
	}

	private OrcamentoModel preencherCampos(OrcamentoModel orcamentoSelecionado) {
		txtStatus_orcamento.setText(orcamentoSelecionado.getStatus_orcamento());
		txtQuantidade_orcamento.setText(orcamentoSelecionado.getQuantidade_orcamento());
		txtValor_orcamento.setText(orcamentoSelecionado.getValor_orcamento());
		textAreaDescricao.setText(orcamentoSelecionado.getDescricaoServ_orcamento());
		txtTipoServico_orcamento.setText(orcamentoSelecionado.getTipoServ_orcamento());
		txtData_orcamento.setText(orcamentoSelecionado.getData_orcamento());
		return orcamentoSelecionado;
	}
	
	public void bloquearCampos() {
    	txtStatus_orcamento.setEnabled(false);
		txtQuantidade_orcamento.setEnabled(false);
		txtValor_orcamento.setEnabled(false);
		textAreaDescricao.setEnabled(false);
		txtData_orcamento.setEnabled(false);
		txtTipoServico_orcamento.setEnabled(false);
		btnNovo.setEnabled(true);
		btnLimpar.setEnabled(false);
		btnSalvar.setEnabled(false);
		//btnSalvarAlt.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnAlterar.setEnabled(false);
    }
	
	private void liberaCampos() {
		txtStatus_orcamento.setEnabled(true);
		txtQuantidade_orcamento.setEnabled(true);
		txtValor_orcamento.setEnabled(true);
		textAreaDescricao.setEnabled(true);
		txtData_orcamento.setEnabled(true);
		txtTipoServico_orcamento.setEnabled(true);
	}
	
	 private void limparCampos() {
		 	txtStatus_orcamento.setText("");
			txtQuantidade_orcamento.setText("");
			txtValor_orcamento.setText("");
			textAreaDescricao.setText("");
			txtData_orcamento.setText("");
			txtTipoServico_orcamento.setText("");
	 }
	 
}
