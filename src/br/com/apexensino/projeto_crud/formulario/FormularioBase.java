package br.com.apexensino.projeto_crud.formulario;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.apexensino.projeto_crud.conexao.Conexao;

public class FormularioBase extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTable tabela;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioBase frame = new FormularioBase();
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
	public FormularioBase() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 435, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel labelCodigo = new JLabel("Código");
		
		JLabel labelNome = new JLabel("Nome");
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10); 
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		JButton botaoCadastrar = new JButton("Cadastrar");
		botaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = txtNome.getText();
				
				try {
					Conexao conexao = new Conexao();
					String sql = "INSERT INTO usuarios (nomeUsuario) VALUES (?)";
					PreparedStatement pstmt = conexao.conectar().prepareStatement(sql);
					pstmt.setString (1, nome);
					pstmt.execute();
					System.out.println("cadastro realizado com sucesso");
				} catch (Exception e2) {
					System.out.println("falha ao cadastrar" + e2);
					
				}
				tabela.setModel(listarDados());
				txtNome.setText("");
				botaoCadastrar.setEnabled(true);
			}
		});
		JButton botaoCancelar = new JButton("Cancelar");
		botaoCancelar.setEnabled(false);
		
		JButton botaoExcluir = new JButton("Excluir");
		botaoExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int codigoUsuario = Integer.parseInt(txtCodigo.getText());
				try {
					Conexao conexao = new Conexao();
					String sql = "DELETE FROM usuarios WHERE idUsuario = ?";
					PreparedStatement pstmt = conexao.conectar().prepareStatement(sql);
					pstmt.setInt(1, codigoUsuario);
					pstmt.execute(sql);
				} catch (Exception e2) {
					System.out.println("falha ao concluir" + e2.getMessage());
				}
				tabela.setModel(listarDados());
				txtCodigo.setText("");
				txtNome.setText("");
				botaoCadastrar.setEnabled(true);
				botaoExcluir.setEnabled(false);
				botaoCancelar.setEnabled(false);
			}
		});
		botaoExcluir.setEnabled(false);
		
		JButton botaoAlterar = new JButton("Alterar");
		botaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int codigoUsuario = Integer.parseInt(txtCodigo.getText());
				String nomeUsuario = txtNome.getText();
				try {
					Conexao conexao = new Conexao();
					String sql = "UPDATE SET nomeUsuario = ? WHERE idUsuario = ?";
					PreparedStatement pstmt = conexao.conectar().prepareStatement(sql);				
					pstmt.setString(1, nomeUsuario);
					pstmt.setInt(2, codigoUsuario);
					
					pstmt.execute(sql);
					} catch (Exception e2) {
						System.out.println("falha ao alterar " + e2.getMessage());
					}
				tabela.setModel(listarDados());
				txtCodigo.setText("");
				txtNome.setText("");
				botaoCadastrar.setEnabled(true);
				botaoAlterar.setEnabled(false);
				botaoExcluir.setEnabled(false);
				botaoCancelar.setEnabled(false);
					
				}
		
		});

		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(labelCodigo, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtCodigo, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(labelNome, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(11)
							.addComponent(botaoCadastrar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(botaoAlterar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(botaoExcluir, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(botaoCancelar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
					.addGap(37))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(labelCodigo)
						.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelNome)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(botaoCadastrar)
						.addComponent(botaoAlterar)
						.addComponent(botaoExcluir)
						.addComponent(botaoCancelar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
		);
		tabela = new JTable();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				botaoCadastrar.setEnabled(false);
				
				botaoAlterar.setEnabled(true);
				botaoExcluir.setEnabled(true);
				botaoCancelar.setEnabled(true);
				
				int linhaSelecionada = tabela.getSelectedRow();
				txtCodigo.setText(tabela.getValueAt(linhaSelecionada, 0).toString());
				txtNome.setText(tabela.getValueAt(linhaSelecionada,1).toString());
			}
		});
		
		tabela.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nome"
			}
		));
		tabela.getColumnModel().getColumn(0).setPreferredWidth(150);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(150);
		scrollPane.setViewportView(tabela);
		contentPane.setLayout(gl_contentPane);
	}
	private static DefaultTableModel listarDados() {
		DefaultTableModel dados = new DefaultTableModel();
		
		dados.addColumn("Codigo");
		dados.addColumn("Nome");
		
		try {
			Conexao conexao = new Conexao();
			String sql = "SELECT * FROM usuarios";
			Statement stmt = conexao.conectar().createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				dados.addRow(new Object[] {rs.getInt (1), rs.getShort(2) });
			}
		} catch (Exception e) {
		System.out.println("falha ao selecionar os usuarios "+ e);
		}
		return dados;
	}
}
