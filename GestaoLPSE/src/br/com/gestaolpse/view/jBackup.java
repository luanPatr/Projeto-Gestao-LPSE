package br.com.gestaolpse.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.gestaolpse.controller.Backup;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class jBackup extends JFrame {

	private JPanel contentPane;
	private ArrayList<String> arquivosBackup;
	private Backup backup;
	private String[] nomesBackup;
	private String itemSelecionado;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				abrirJBackup();
			}
		});

	}

	public static void abrirJBackup() {
		try {
			jBackup frame = new jBackup();
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public jBackup() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(jBackup.class.getResource("/br/com/gestaolpse/icons/logoos.png")));
    	setTitle("Gestão LPSE - Backup");
    	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 776, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 11, 696, 203);
		contentPane.add(scrollPane);
		
		backup = new Backup();
		arquivosBackup = new ArrayList<>();
		arquivosBackup = backup.listarAquivos();
		nomesBackup = arquivosBackup.toArray(new String[arquivosBackup.size()]);
		
		JList list = new JList();
		list.setListData(nomesBackup);
		
		scrollPane.setViewportView(list);
		
		JButton btnNewButton_1 = new JButton("Gerar Backup");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\Luan Souza\\Desktop\\Verssões projeto lp2\\GestaoLPSE_v3\\GestaoLPSE\\src\\br\\com\\gestaolpse\\icons\\backup24px.png"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(btnNewButton_1, "Deseja gerar Backup? ")==JOptionPane.YES_NO_OPTION) {
					backup.gerarBackup();
					arquivosBackup = backup.listarAquivos();
					nomesBackup = arquivosBackup.toArray(new String[arquivosBackup.size()]);
					list.setListData(nomesBackup);
					revalidate();
					repaint();
				}
			}
			
		});
		btnNewButton_1.setBounds(40, 243, 125, 40);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Restaurar Backup");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Luan Souza\\Desktop\\Verssões projeto lp2\\GestaoLPSE_v3\\GestaoLPSE\\src\\br\\com\\gestaolpse\\icons\\restore24px.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(btnNewButton, "Deseja restaurar o backup? ")==JOptionPane.YES_NO_OPTION) {
					try {
						backup.restaurarBackup(itemSelecionado);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnNewButton.setBounds(198, 243, 150, 40);
		contentPane.add(btnNewButton);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setIcon(new ImageIcon("C:\\Users\\Luan Souza\\Desktop\\Verssões projeto lp2\\GestaoLPSE_v3\\GestaoLPSE\\src\\br\\com\\gestaolpse\\icons\\voltar24px.png"));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setBounds(375, 243, 88, 40);
		contentPane.add(btnSair);
		
		list.addListSelectionListener(new ListSelectionListener() {
					
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if(!e.getValueIsAdjusting()) {
						if(list.getSelectedIndex() == -1) {
							list.setSelectedIndex(e.getFirstIndex());						
						}
						itemSelecionado = ((JList<String>)e.getSource()).getSelectedValue();
						if(itemSelecionado != null) {
							btnNewButton_1.setEnabled(true);
						}
					}
						
				}

			});
	}
}
