package br.com.apexensino.projeto_crud.principal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.apexensino.projeto_crud.conexao.Conexao;

public class Principal {

	public static void main(String[] args) throws SQLException {

	}

	public static void createStatement() {
		// criar um objeto da classe de conexão
		Conexao conexao = new Conexao();
		// comando SQL
		String sql = "SELECT * FROM tabela";
		try {
			// objeto contendoa ação de seleção
			Statement createStatement = conexao.conectar().createStatement();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}

	}

	public static void createPreparedStatement() {
		Conexao conexao = new Conexao();
		String sql = "SELECT*FROM tabela WHERE coluna = ?";
		try {
			PreparedStatement preparedStatement = conexao.conectar().prepareStatement(sql);
			int parametro = 1;
			preparedStatement.setInt(1, parametro);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void createPreparedStatementWithResultSet() {
		Conexao conexao = new Conexao();
		String sql = "SELECT*FROM tabela WHERE coluna = ?";
		try {
			PreparedStatement preparedStatement = conexao.conectar().prepareStatement(sql);
			int parametro = 1;
			preparedStatement.setInt(1, parametro);
			ResultSet executeQuery = preparedStatement.executeQuery();
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
