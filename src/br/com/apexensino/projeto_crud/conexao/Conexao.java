package br.com.apexensino.projeto_crud.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

	// método de conexão com o banco de dados
	public Connection conectar() {
		// criando uma variavel do tipo de retorno da classe connection
		Connection retornoConexao = null;
		// tentativa de conexão
		try {

			// referenciar JDBC
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			// dados do servidor
			String dadosServidor = "jdbc:sqlserver://localhost:1433;databaseName=projeto_crud;user=sa;password=12345678;encrypt=false";

			// retorno conexão
			retornoConexao = DriverManager.getConnection(dadosServidor);

			// mensagem
			System.out.println("conectado com Sucesso!");

		} catch (Exception e) {
			System.out.println("falha ao conectar " + e.getMessage());
		}

		// retorno da conexão
		return retornoConexao;
	}
}
