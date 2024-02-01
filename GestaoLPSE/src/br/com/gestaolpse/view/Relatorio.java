package br.com.gestaolpse.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.gestaolpse.controller.Conexao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class Relatorio extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomeOrcamento;
	private String nome = null;
	private JButton btnRelatorio;
	private JButton btnSair;
	private JButton btnPesquisar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Relatorio frame = new Relatorio();
					
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
	public Relatorio() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormCadastroCliente.class.getResource("/br/com/gestaolpse/icons/logoos.png")));
    	setTitle("Gestão LPSE - Relatorio Orçamentos");
    	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 451, 268);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pesquisar nome orçamento");
		lblNewLabel.setBounds(24, 42, 220, 14);
		contentPane.add(lblNewLabel);
		
		btnRelatorio = new JButton("Gerar Relatorio");
		btnRelatorio.setEnabled(false);
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gerarRelatorio();
			}
		});
		btnRelatorio.setIcon(new ImageIcon("C:\\Users\\Luan Souza\\Desktop\\Verssões projeto lp2\\GestaoLPSE_v3\\GestaoLPSE\\src\\br\\com\\gestaolpse\\icons\\imprimir24px.png"));
		btnRelatorio.setBounds(42, 150, 167, 33);
		contentPane.add(btnRelatorio);
		
		txtNomeOrcamento = new JTextField();
		txtNomeOrcamento.setBounds(21, 67, 259, 33);
		contentPane.add(txtNomeOrcamento);
		txtNomeOrcamento.setColumns(10);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				nome = txtNomeOrcamento.getText().toString();
				
				btnRelatorio.setEnabled(true);
				
				limpaCampo();
				
			}
		});
		btnPesquisar.setIcon(new ImageIcon("C:\\Users\\Luan Souza\\Desktop\\Verssões projeto lp2\\GestaoLPSE_v3\\GestaoLPSE\\src\\br\\com\\gestaolpse\\icons\\pesquisar24px.png"));
		btnPesquisar.setBounds(304, 67, 120, 33);
		contentPane.add(btnPesquisar);
		
		
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setIcon(new ImageIcon("C:\\Users\\Luan Souza\\Desktop\\Verssões projeto lp2\\GestaoLPSE_v3\\GestaoLPSE\\src\\br\\com\\gestaolpse\\icons\\voltar24px.png"));
		btnSair.setBounds(231, 150, 152, 33);
		contentPane.add(btnSair);
	}
	
	public void gerarRelatorio() {
		
		try {
			
			JasperReport report = JasperCompileManager.compileReport("relatorio\\Blank_A4.jrxml");
			
			String query = null;
			
			if(nome != null) {
				query = "SELECT * FROM ORCAMENTO WHERE status_orcamento = '" + nome + "'";
			}
			
			Map<String, Object> parametros = new HashMap<>();
            parametros.put("QUERY", query);
            
			Connection connection = Conexao.getInstancia().abrirConexao();
			
			JasperPrint print = JasperFillManager.fillReport(report, parametros, connection);
			
			
			JasperViewer.viewReport(print, false);
			

			Conexao.getInstancia().fecharConexao();
			
			
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void limpaCampo() {
		txtNomeOrcamento.setText("");
	}
	
}
