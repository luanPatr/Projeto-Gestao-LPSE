
package br.com.gestaolpse.view;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import java.awt.Font;

public class FormLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	// Sistema de Login Criptografado com SHA2-256
	private static JTextField txtUser;
	private static JPasswordField passwordField;
	static String Client = "04F8996DA763B7A969B1028EE3007569EAF3A635486DDAB211D512C85B9DF8FB";
	static String Corp = "8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918";
	static String senhaout;
	static String senha;
	static MessageDigest algorithm = null;
	static byte messageDigest[] = null;
	static StringBuilder hexString;
	static FormLogin frame;
	private JLabel lblUsuario;
	private JLabel lblSenha;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new FormLogin();
					
					// Centraliza a janela na tela
	                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	                int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	                int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	                frame.setLocation(x, y);
	                
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
	public FormLogin() {
		setBackground(Color.DARK_GRAY);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormLogin.class.getResource("/br/com/gestaolpse/icons/logoos.png")));
    	setTitle("Gestão LPSE - Login");
		setDefaultCloseOperation(FormLogin.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUser = new JTextField();
		txtUser.setBounds(144, 96, 151, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(144, 146, 151, 20);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("login");
		btnLogin.setIcon(new ImageIcon("C:\\Users\\Luan Souza\\Desktop\\Verssões projeto lp2\\GestaoLPSE_v3\\GestaoLPSE\\src\\br\\com\\gestaolpse\\icons\\salvar24px.png"));
		btnLogin.setBounds(144, 189, 151, 33);
		contentPane.add(btnLogin);
		
		lblUsuario = new JLabel("Usuário");
		lblUsuario.setFont(new Font("Inter", Font.BOLD, 12));
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setBounds(144, 71, 46, 14);
		contentPane.add(lblUsuario);
		
		lblSenha = new JLabel("Senha");
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setFont(new Font("Inter", Font.BOLD, 12));
		lblSenha.setBounds(144, 127, 46, 14);
		contentPane.add(lblSenha);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FormLogin.class.getResource("/br/com/gestaolpse/icons/fundopx.png")));
		lblNewLabel.setBounds(0, 0, 434, 261);
		contentPane.add(lblNewLabel);
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				if (txtUser.getText().equals("")) {
					System.out.println("Digite o usuário!");
				}
				if (passwordField.equals("")) {
					System.out.println("Digite a senha!");
				} else {
					ChecarLogin();
				}
			}
		});}
		
		public static void ChecarLogin() {

			senha = new String(passwordField.getPassword());
			try {
				algorithm = MessageDigest.getInstance("SHA-256");
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			}
			try {
				messageDigest = algorithm.digest(senha.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			senhaout = hexString.toString();

			if (txtUser.getText().equals("user") && (senhaout.equals(Client))) {
				FormPrincipal principal = new FormPrincipal();
				principal.setVisible(true);
				FormPrincipal.mnCadastro.setVisible(true);
				FormPrincipal.mnRelatorio.setVisible(true);
				FormPrincipal.mnBackup.setVisible(false);
				fecharJanelaLogin();
			}

			if (txtUser.getText().equals("admin") && (senhaout.equals(Corp))) {
				FormPrincipal principal = new FormPrincipal();
				principal.setVisible(true);
				FormPrincipal.mnCadastro.setVisible(true);
				FormPrincipal.mnRelatorio.setVisible(true);
				FormPrincipal.mnBackup.setVisible(true);
				principal.requestFocus();
				principal.hasFocus();
				fecharJanelaLogin();
				
			} else {
				JOptionPane.showInternalMessageDialog(null, "Usuario ou Senha Errados");
				txtUser.setText("");
				passwordField.setText("");
			}
		}
		
		public static void fecharJanelaLogin() {
			frame.dispose();
		}
	}

