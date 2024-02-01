package br.com.gestaolpse.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import br.com.gestaolpse.dao.AgendamentoDao;
import br.com.gestaolpse.model.AgendamentoModel;
import br.com.gestaolpse.model.modeloTabelaAgendamento;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class FormPrincipal extends JFrame {

	//private static final long serialVersionUID = 1L;
	private ArrayList<AgendamentoModel> agendamentos;
	
	static JMenu mnCadastro;
	static JMenu mnRelatorio;
	static JMenu mnBackup;
	private JMenu mnSair;
	private JFrame frame;
	private JMenuItem mntmCliente;
	private JMenuItem mntmOrcamento;
	private JMenuItem mntmBackup;
	private JMenuItem mntmGerarRelatorio;
	private JMenu mnAgendamento;
	private JMenuItem mntmAgendamento;
	private JTable table2;
	private JTable table;
	private JScrollPane scrollPane;
	
	private AgendamentoDao dao;
	private AgendamentoModel obj;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormPrincipal window = new FormPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public FormPrincipal() {
		initialize();
	}
	
	

	private void initialize() {
		
		AgendamentoDao dao = new AgendamentoDao();
		try {
			agendamentos = dao.listarAgendamento();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		frame = new JFrame();
		frame.setBounds(new Rectangle(0, 0, 1080, 920));
		frame.setBounds(100, 100, 1080, 920);
		frame.getContentPane();
		frame.setExtendedState(MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(frame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(true);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(FormPrincipal.class.getResource("/br/com/gestaolpse/icons/logoLP.png")));
		frame.setTitle("Gestão LPSE - Inicio");
		frame.setVisible(true);
		
			JPanel panel = new JPanel();
			panel.setVisible(true);
			panel.setBounds(0, 33, 2560, 933);
			frame.getContentPane().add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Servicos Agendados:");
			lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setBackground(Color.WHITE);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
			lblNewLabel.setBounds(456, 162, 245, 55);
			panel.add(lblNewLabel);
			
			
			
			scrollPane = new JScrollPane();
			scrollPane.setBounds(456, 248, 431, 255);
			panel.add(scrollPane);
			
			modeloTabelaAgendamento ModeloTabelaAgendamento = new modeloTabelaAgendamento(agendamentos);
			
			table = new JTable();
			table.setModel(ModeloTabelaAgendamento);
			scrollPane.setViewportView(table);
			
			JButton btnNewButton = new JButton("Atualizar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
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
			btnNewButton.setIcon(new ImageIcon(FormPrincipal.class.getResource("/br/com/gestaolpse/icons/icons8-atualizar-24.png")));
			btnNewButton.setBounds(721, 191, 166, 34);
			panel.add(btnNewButton);
		
			
			JLabel lblfundoprincipal = new JLabel("");
			lblfundoprincipal.setBounds(0, 33, 2560, 933);
			panel.add(lblfundoprincipal);
			lblfundoprincipal.setIcon(new ImageIcon(FormPrincipal.class.getResource("/br/com/gestaolpse/icons/fundoprincipal.png")));

			
			JMenuBar menuBar = new JMenuBar();
			menuBar.setBounds(0, 0, 2560, 35);
			frame.getContentPane().add(menuBar);
			
			mnCadastro = new JMenu("Cadastro");
			mnCadastro.setIcon(new ImageIcon("C:\\Users\\Luan Souza\\Desktop\\Verssões projeto lp2\\GestaoLPSE_v3\\GestaoLPSE\\src\\br\\com\\gestaolpse\\icons\\usuarios24px.png"));
			menuBar.add(mnCadastro);
			
			mntmCliente = new JMenuItem("Cadastro Cliente");
			mntmCliente.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        FormCadastroCliente cadastroCliente = new FormCadastroCliente();
			        
			        cadastroCliente.setLocationRelativeTo(frame);
			        
			        cadastroCliente.setVisible(true);
			    }

				public void actionPerformedd(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			mnCadastro.add(mntmCliente);
			
			mntmOrcamento = new JMenuItem("Cadastro Orçamento");
			mntmOrcamento.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        FormCadastroOrcamento cadastroOrcamento = new FormCadastroOrcamento();
			        
			        cadastroOrcamento.setLocationRelativeTo(frame);
			        
			        cadastroOrcamento.setVisible(true);
			    }

				public void actionPerformedd(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			mnCadastro.add(mntmOrcamento);
			
			mnRelatorio = new JMenu("Relatório");
			mnRelatorio.setIcon(new ImageIcon("C:\\Users\\Luan Souza\\Desktop\\Verssões projeto lp2\\GestaoLPSE_v3\\GestaoLPSE\\src\\br\\com\\gestaolpse\\icons\\arquivos24px.png"));
			menuBar.add(mnRelatorio);
			
			mntmGerarRelatorio = new JMenuItem("Gerar Relatorio Orcamento");
			mntmGerarRelatorio.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        Relatorio gerarRelatorio = new Relatorio();
			        
			        gerarRelatorio.setLocationRelativeTo(frame);
			        
			        gerarRelatorio.setVisible(true);
			    }
			});
			mnRelatorio.add(mntmGerarRelatorio);
			
			mnAgendamento = new JMenu("Agendamento/Hora");
			mnAgendamento.setIcon(new ImageIcon(FormPrincipal.class.getResource("/br/com/gestaolpse/icons/aula24px.png")));
			mnAgendamento.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
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
			menuBar.add(mnAgendamento);
			
			mntmAgendamento = new JMenuItem("Cadastrar Agendamento");
			mntmAgendamento.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        FormCadastroAgendamento agendamento = new FormCadastroAgendamento();
			        
			        agendamento.setLocationRelativeTo(frame);
			        
			        agendamento.setVisible(true);
			        
			        try {
			            // Atualiza a lista de clientes
			            agendamentos = dao.listarAgendamento();

			            // Atualiza o modelo da tabela com os novos dados
			            ModeloTabelaAgendamento.setModelo(agendamentos);

			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    }

				public void actionPerformedd(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				
			});
			mnAgendamento.add(mntmAgendamento);
			
			
			
			mnBackup = new JMenu("Backup");
			mnBackup.setIcon(new ImageIcon("C:\\Users\\Luan Souza\\Desktop\\Verssões projeto lp2\\GestaoLPSE_v3\\GestaoLPSE\\src\\br\\com\\gestaolpse\\icons\\backup24px.png"));
			menuBar.add(mnBackup);
			
			mntmBackup = new JMenuItem("Backup/Restaurar");
			mntmBackup.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        jBackup jBackup = new jBackup();
			        
			        jBackup.setLocationRelativeTo(frame);
			        
			        jBackup.setVisible(true);
			    }

				public void actionPerformedd(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			mnBackup.add(mntmBackup);
			
			
			
			mnSair = new JMenu("Sair");
			mnSair.setIcon(new ImageIcon("C:\\Users\\Luan Souza\\Desktop\\Verssões projeto lp2\\GestaoLPSE_v3\\GestaoLPSE\\src\\br\\com\\gestaolpse\\icons\\voltar24px.png"));
			mnSair.setForeground(Color.RED);
			mnSair.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					System.exit(EXIT_ON_CLOSE);
				}
			});
			menuBar.add(mnSair);
	}
}
