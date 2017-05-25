package gerenciamentoMercado.bancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BancoDeDados {
    private Connection conn;

    public BancoDeDados() throws SQLException{
        String driver = "jdbc:derby:./Data/database;create=true"; //Conecta no banco local da maquina
        conn = DriverManager.getConnection(driver);

        PreparedStatement stmt;

        //Se o banco de dados estiver vazio, cria-se as tabelas do programa
        try{
            String sql = "CREATE TABLE Funcionarios (cpf VARCHAR(11) NOT NULL, nome VARCHAR(32), cargo VARCHAR(32) NOT NULL, salario FLOAT, primary key (cpf) )";

            stmt = conn.prepareStatement(sql); //Se não existir, cria a tabela Funcionarios
            stmt.execute();
            stmt.close();
        } catch(SQLException se){
            //Se deu exceção, a tabela já existe no banco
        }

        try{
            String sql = "CREATE TABLE Clientes (cpf VARCHAR(11) NOT NULL, nome VARCHAR(32), tipo VARCHAR(32) NOT NULL, primary key (cpf) )";

            stmt = conn.prepareStatement(sql); //Se não existir, cria a tabela clientes
            stmt.execute();
            stmt.close();
        } catch(SQLException se){
            //Se deu exceção, a tabela já existe no banco
        }


        //Se o banco de dados estiver vazio, cria-se as tabelas do programa
        try{
            String sql = "CREATE TABLE Produtos (codigo BIGINT NOT NULL, descricao VARCHAR(255), valorunit FLOAT, quantidade INT, primary key (codigo) )";

            stmt = conn.prepareStatement(sql);//Se não existir, cria a tabela produtos
            stmt.execute();
            stmt.close();
        } catch(SQLException se){
            //Se deu exceção, a tabela já existe no banco
        }
    }
}
